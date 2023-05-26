package org.hupidou.algroadmap.statemachine;

import com.google.common.collect.ListMultimap;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class Fsm {

    /**
     * 获取可以更改的状态集合
     *
     * @param targetStatus 要更改的状态
     */
    public Set<Integer> allowStatuses(Integer targetStatus) {
        if (targetStatus == null) {
            return Collections.emptySet();
        }
        Set<Integer> allowStatus = new HashSet<>();
        for (Integer status : getStatusMap().keys()) {
            List<Integer> list = getStatusMap().get(status);
            if (list.contains(targetStatus)) {
                allowStatus.add(status);
            }
        }
        return allowStatus;
    }

    protected abstract ListMultimap<Integer, Integer> getStatusMap();
}
