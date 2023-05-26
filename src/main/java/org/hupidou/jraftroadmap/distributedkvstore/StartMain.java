package org.hupidou.jraftroadmap.distributedkvstore;

import com.alipay.sofa.jraft.rhea.options.PlacementDriverOptions;
import com.alipay.sofa.jraft.rhea.options.RheaKVStoreOptions;
import com.alipay.sofa.jraft.rhea.options.StoreEngineOptions;
import com.alipay.sofa.jraft.rhea.options.configured.MemoryDBOptionsConfigured;
import com.alipay.sofa.jraft.rhea.options.configured.PlacementDriverOptionsConfigured;
import com.alipay.sofa.jraft.rhea.options.configured.RheaKVStoreOptionsConfigured;
import com.alipay.sofa.jraft.rhea.options.configured.StoreEngineOptionsConfigured;
import com.alipay.sofa.jraft.rhea.storage.StorageType;
import com.alipay.sofa.jraft.util.Endpoint;

public class StartMain {
    public static void main(String[] args) {

        String server1 = "127.0.0.1:8891";
        String server2 = "127.0.0.1:8892";
        String server3 = "127.0.0.1:8893";
        String serverList = server1.concat(",").concat(server2).concat(",").concat(server3);
        startNode(server1, serverList);
        startNode(server2, serverList);
        startNode(server3, serverList);


    }

    private static void startNode(String server, String serverList) {
        // 在RheaKV中，Region是指一段连续的数据区间，它是数据在集群中的分片单位。每个Region都由一个Leader和多个Follower组成，Leader负责处理客户端请求，Follower负责复制Leader的数据。
        // Region的分配是指将数据划分为若干个Region，并将这些Region分配给不同的节点进行管理，以实现数据的负载均衡和高可用性。

        // 在RheaKV中，Region的分配是由Placement Driver负责的。当一个新的节点加入集群时，Placement Driver会根据当前集群的状态和负载情况，计算出新的Region分配方案，
        // 并将这些Region分配给新的节点进行管理。当一个节点故障或下线时，Placement Driver会重新计算Region的分配方案，并将这些Region重新分配给其他节点进行管理。

        // 在模拟Region的分配时，我们通常会使用虚拟的Placement Driver，以便于测试。
        // 虚拟的Placement Driver会模拟Region的分配过程，并将Region分配给虚拟的节点进行管理。通过模拟Region的分配，我们可以测试集群的负载均衡和高可用性，以及节点的故障恢复等情况。
        final PlacementDriverOptions pdOpts = PlacementDriverOptionsConfigured.newConfigured()
                .withFake(true)
                .config();

        String[] split = server.split(":");

        final StoreEngineOptions storeOpts = StoreEngineOptionsConfigured.newConfigured()
                .withStorageType(StorageType.Memory)
                .withMemoryDBOptions(MemoryDBOptionsConfigured.newConfigured().config())
                .withRaftDataPath("tmp/data/server1")
                .withServerAddress(new Endpoint(split[0], Integer.parseInt(split[1])))
                .config();


        final RheaKVStoreOptions opts = RheaKVStoreOptionsConfigured.newConfigured()
                .withInitialServerList(serverList)
                .withStoreEngineOptions(storeOpts)
                .withPlacementDriverOptions(pdOpts)
                .config();

        Node node = new Node(opts);

        node.start();

        Runtime.getRuntime().addShutdownHook(new Thread(node::stop));

        System.out.println("start seq node success on port : " + split[1]);
    }
}
