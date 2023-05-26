package org.hupidou.algroadmap.scheduler;

import java.util.ArrayList;
import java.util.List;

public class DAG {

    // DAG中所有的节点
    private List<TaskNode> nodes;


    public DAG() {
        this.nodes = new ArrayList<>();
    }

    // 如果想做成服务，这个方法必须提供，只有提供了这个方法，才可用动态增加
    public void addTask(String name) {
        // 创建一个新的任务节点，并添加到节点列表中
        TaskNode node = new TaskNode(new ArrayList<>(), new ArrayList<>(), name);
        nodes.add(node);
    }

    public void addDependency(String parent, String child) {
        // 在节点列表中查找父节点和子节点
        TaskNode parentNode = findNode(parent);
        TaskNode childNode = findNode(child);

        // 添加父子关系
        parentNode.getChildren().add(childNode);
        childNode.getParents().add(parentNode);
    }


    private TaskNode findNode(String name) {
        // 在节点列表中查找指定节点
        for (TaskNode node : nodes) {
            if (node.getName().equals(name)) {
                return node;
            }
        }
        throw new IllegalArgumentException("Node not found:" + name);
    }

    public List<TaskNode> getNodes() {
        return nodes;
    }
}
