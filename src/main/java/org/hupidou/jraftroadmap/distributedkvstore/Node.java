package org.hupidou.jraftroadmap.distributedkvstore;

import com.alipay.sofa.jraft.rhea.LeaderStateListener;
import com.alipay.sofa.jraft.rhea.client.DefaultRheaKVStore;
import com.alipay.sofa.jraft.rhea.client.RheaKVStore;
import com.alipay.sofa.jraft.rhea.options.RheaKVStoreOptions;

import java.util.concurrent.atomic.AtomicLong;

public class Node {
    /*
    rheakv项目结合了RocksDB、HBase和JRaft三个项目的优势，具体表现在以下几个方面：

    1. 高性能的存储引擎：RocksDB是一个高性能的嵌入式键值存储引擎，具有快速的读写性能和低延迟的响应能力，能够满足高并发、高吞吐量的数据存储需求。

    2. 分布式的数据存储：HBase是一个开源的分布式列式存储系统，具有高可用性、可扩展性和容错性等优点，能够实现数据的分布式存储和访问。

    3. 分布式一致性框架：JRaft是一个基于Raft协议实现的分布式一致性框架，能够保证分布式系统中数据的一致性和可靠性。
    综合以上三个项目的优势，rheakv项目能够实现高性能、高可用、分布式的键值存储系统，同时支持分布式事务、分布式锁等高级功能。
     */
    private RheaKVStore rheaKVStore;

    private final RheaKVStoreOptions options;

    private final AtomicLong leaderTerm = new AtomicLong(-1);

    public Node(RheaKVStoreOptions options) {
        this.options = options;
    }

    public boolean isLeader() {
        return leaderTerm.get() > 0;
    }

    public void start() {
        rheaKVStore = new DefaultRheaKVStore();
        rheaKVStore.init(this.options);
        rheaKVStore.addLeaderStateListener(-1, new LeaderStateListener() {
            @Override
            public void onLeaderStart(long l) {
                System.out.println("the endpoint become leader");
                leaderTerm.set(l);
            }

            @Override
            public void onLeaderStop(long l) {
                leaderTerm.set(-1);
            }
        });
    }

    public void stop() {
        rheaKVStore.shutdown();
    }
}
