package org.hupidou.algroadmap.scheduler;

import java.util.List;

public class TaskNode {

    private List<TaskNode> parents;

    private List<TaskNode> children;

    // 任务名称
    private String name;

    public TaskNode(List<TaskNode> parents, List<TaskNode> children, String name) {
        this.parents = parents;
        this.children = children;
        this.name = name;
    }


    public List<TaskNode> getParents() {
        return parents;
    }

    public List<TaskNode> getChildren() {
        return children;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "TaskNode{" +
                "parents=" + parents +
                ", children=" + children +
                ", name='" + name + '\'' +
                '}';
    }


    // 判断当前node是不是Parent node
    public boolean isParent() {
        return this.parents.isEmpty();
    }

    // 当执行完该node之后，需要把它从child node中的parents列表中移除
    public void removeParent(TaskNode parent) {
        this.parents.remove(parent);
    }
}
