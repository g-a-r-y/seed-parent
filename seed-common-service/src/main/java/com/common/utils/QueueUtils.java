package com.common.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.MinMaxPriorityQueue;

import java.util.List;

public class QueueUtils {
    public static <E> List<E> toList(MinMaxPriorityQueue<E> queue) {
        List<E> list = Lists.newArrayList();
        while (!queue.isEmpty()) {
            list.add(queue.removeFirst());
        }
        return list;
    }
}
