package org.hupidou.algroadmap.statemachine;

import java.util.Set;

public class StartMain {
    public static void main(String[] args) {

        // 将状态改成5
        Set<Integer> allowStatus = DemoFsm.INSTANCE.allowStatuses(5);

        System.out.println(allowStatus);

        // [1,2]

    }
}
