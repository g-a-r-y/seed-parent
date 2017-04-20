package com.common.utils;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class VisitLogUtils {
    public static String[] getIndices(String module, int days) {
        Preconditions.checkArgument(days > 0);
        List<String> indexList = Lists.newArrayList();
        int includedDays = 0;
        LocalDateTime current = LocalDateTime.now();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        while (includedDays < days) {
            indexList.add("pv_" + module + "_" + dateFormat.format(current));
            includedDays++;
            current = current.minusDays(1);
        }
        return indexList.toArray(new String[0]);
    }
}

