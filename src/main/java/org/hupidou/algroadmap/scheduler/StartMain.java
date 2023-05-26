package org.hupidou.algroadmap.scheduler;

public class StartMain {
    public static void main(String[] args) {
        DAG dag = new DAG();
        // 定义5个任务
        dag.addTask("A");
        dag.addTask("B");
        dag.addTask("C");
        dag.addTask("D");
        dag.addTask("E");
        // 定义依赖关系
        /*

                    B
               C           A

           D

        E
         */
        dag.addDependency("B", "C");
        dag.addDependency("B", "A");
        dag.addDependency("C", "D");
        dag.addDependency("D", "E");

        DAGScheduler scheduler = new DAGScheduler();
        scheduler.schedule(dag);

    }
}
