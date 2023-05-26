package org.hupidou.algroadmap.scheduler;


import java.util.LinkedList;

public class DAGScheduler {

    public void schedule(DAG dag) {
        // 创建一个队列，用于保存未执行的任务节点
        LinkedList<TaskNode> queue = new LinkedList<TaskNode>();
        // 将没有任何前置任务的任务节点加入队列
        for (TaskNode node : dag.getNodes()) {
            if (node.isParent()) {
                queue.offer(node);
            }
        }

        // 不断从队列中取出任务节点，并执行该任务以及所有它的子孙节点
        while (!queue.isEmpty()) {
            TaskNode node = queue.poll();
            execute(node);

            for (TaskNode child : node.getChildren()) {
                child.removeParent(node);
                if (child.isParent()) {
                    queue.offer(child);
                }
            }
        }

        System.out.println("All task are completed!");
    }

    private void execute(TaskNode node) {
        System.out.println("执行节点:" + node.getName());
    }
}
