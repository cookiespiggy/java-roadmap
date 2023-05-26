package org.hupidou.algroadmap.statemachine;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;

public class DemoFsm extends Fsm {

    private static final ListMultimap<Integer, Integer> STATUS_MAP = ArrayListMultimap.create();

    public static final DemoFsm INSTANCE = new DemoFsm();

    static {
        STATUS_MAP.put(1, 2);
        STATUS_MAP.put(1, 3);
        STATUS_MAP.put(2, 3);
        STATUS_MAP.put(2, 5);
        STATUS_MAP.put(1, 5);
    }

    @Override
    protected ListMultimap<Integer, Integer> getStatusMap() {
        return STATUS_MAP;
    }
}
