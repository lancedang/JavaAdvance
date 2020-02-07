// Copyright (C) 2019 Meituan
// All rights reserved
package test;

import com.alibaba.fastjson.JSON;

import java.util.*;

/**
 * @author qiankai07
 * @version 1.0
 * Created on 11/28/19 8:48 PM
 **/
public class Demo {
    public static void main(String[] args) {
 /*       long id = 1L;
        String regressionId = "2";
        long variableId = 2L;
        boolean onLine = false;

        Range range = new Range();
        range.setStart(10);
        range.setEnd(20);
        range.setCount(10);

        List<Range> ranges = new ArrayList<>();
        ranges.add(range);

        Group group = new Group();
        group.setCategory("boy");
        group.setCount(10);

        Group group1 = new Group();
        group1.setCategory("gril");
        group1.setCount(20);

        List<Group> sex = new ArrayList<>();
        sex.add(group);
        sex.add(group1);

        int total = 100;
        String type = "CONTINUES";
        double max = 120;
        double min = 10;

        VariableDigestStatisticsInfo variableDigestStatisticsInfo = new VariableDigestStatisticsInfo();
        variableDigestStatisticsInfo.setGroups(sex);
        variableDigestStatisticsInfo.setRanges(ranges);
        variableDigestStatisticsInfo.setMax(max);
        variableDigestStatisticsInfo.setMin(min);
        variableDigestStatisticsInfo.setTotal(total);
        variableDigestStatisticsInfo.setType(type);

        VariableDigestStatisticsDo variableDigestStatisticsDo = new VariableDigestStatisticsDo();
        variableDigestStatisticsDo.setId(id);
        variableDigestStatisticsDo.setOnLine(onLine);
        variableDigestStatisticsDo.setVariableId(variableId);
        variableDigestStatisticsDo.setRegressionId(regressionId);
        variableDigestStatisticsDo.setStatisticsInfo(JSON.toJSONString(variableDigestStatisticsInfo));

        System.out.println(JSON.toJSONString(variableDigestStatisticsInfo));*/

        Map<String, String> map = new HashMap<String, String>();
        map.put("executeFrequencyType", "single");
        map.put("cronExpression", "0/10 * * * * ?");
        map.put("recordNum", "1000");

        System.out.println(JSON.toJSONString(map));




    }
}

class VariableDigestStatisticsDo {
    private Long id;
    private String regressionId;
    private Long variableId;
    private String statisticsInfo;
    private boolean onLine;
    private Date gmtCreate;
    private Date gmtModified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegressionId() {
        return regressionId;
    }

    public void setRegressionId(String regressionId) {
        this.regressionId = regressionId;
    }

    public Long getVariableId() {
        return variableId;
    }

    public void setVariableId(Long variableId) {
        this.variableId = variableId;
    }

    public String getStatisticsInfo() {
        return statisticsInfo;
    }

    public void setStatisticsInfo(String statisticsInfo) {
        this.statisticsInfo = statisticsInfo;
    }

    public boolean isOnLine() {
        return onLine;
    }

    public void setOnLine(boolean onLine) {
        this.onLine = onLine;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}

class VariableDigestStatisticsInfo {
    private List<Range> ranges;

    private List<Group> groups;

    private int total;

    private String type;

    private double max;
    private double min;

    public List<Range> getRanges() {
        return ranges;
    }

    public void setRanges(List<Range> ranges) {
        this.ranges = ranges;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }
}

class Range {
    private double start;
    private double end;

    private int count;

    public double getStart() {
        return start;
    }

    public void setStart(double start) {
        this.start = start;
    }

    public double getEnd() {
        return end;
    }

    public void setEnd(double end) {
        this.end = end;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

class Group {
    private String category;

    private long count;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
