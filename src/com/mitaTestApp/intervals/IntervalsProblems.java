package com.mitaTestApp.intervals;

import java.util.*;
import java.util.stream.Collectors;

public class IntervalsProblems {
    //Leetcode - 56,452,435,57,252,253,986 (C#->Java)
    public static void main(String[] args) {
    String[] userName = {"joe","joe","joe","james","james","james","james","mary","mary","mary"};
    int[] timestamp = {1,2,3,4,5,6,7,8,9,10} ;
    String[] website ={"home","about","career","home","cart","maps","home","home","about","career"};
    mostVisitedPattern(userName,timestamp,website);
    }

    //[[1,4],[2,3]]
    //Leetcode 56 - Merge Interval
    public static int[][] merge(int[][] intervals) {
        Arrays.sort(intervals,(a, b)-> Integer.compare(a[0],b[0]));
        List<int[]> list = new ArrayList<>();
        list.add(new int[] {intervals[0][0], intervals[0][1]} );
        int len =0;
        for (int i=1;i<intervals.length;i++) {
            if(list.get(len)[1] >= intervals[i][0] && list.get(len)[1] <= intervals[i][1] || list.get(len)[1] >= intervals[i][1]){
                list.get(len)[1] = Math.max(intervals[i][1], list.get(len)[1]);
            }
            else
            {
                list.add(intervals[i]); len++;
            }
        }
       return list.toArray(new int[list.size()][]);
    }

    //Leetcode 57 - Insert Intervals
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> list = new ArrayList<>();
        if(newInterval.length ==0)
            return intervals;
        if(intervals.length == 0){
            list.add(new int[]{newInterval[0], newInterval[1]});
            return list.toArray(new int[list.size()][]);
        }
        for(int[] interval : intervals){
            if(interval[1] < newInterval[0]){
                list.add(interval);
            }
            else if(interval[0] > newInterval[1]){
                list.add(newInterval);
                newInterval = interval;
            }
            else{
                newInterval[0] = Math.min(interval[0], newInterval[0]);
                newInterval[1] = Math.max(interval[1], newInterval[1]);
            }
        }
        list.add(newInterval);
        return list.toArray(new int[list.size()][]);



    }

    //Leetcode 759 - Employee Free time
    class Interval {
        public int start;
        public int end;
        public Interval() {}
        public Interval(int _start, int _end) {
            start = _start;
            end = _end;
        }
    };
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        if (schedule.size() == 0)
            return null;
        List<Interval>list = schedule.stream().flatMap(x->x.stream()).collect(Collectors.toList());
        Collections.sort(list,Comparator.comparingInt(a->a.start));
        List<Interval> lst = new ArrayList<>();
        if (list.size()<= 1) {
            return list;
        }
        int prevEnd = list.get(0).end;
        for(int i =1;i<list.size();i++){
            if(list.get(i).start > prevEnd){
                lst.add(new Interval(prevEnd, list.get(i).start));
            }
            prevEnd = Math.max(prevEnd, list.get(i).end);
        }

        return lst;

    }

    //
    static class Visit{
        String userName;
        int timestamp;
        String website;
        public Visit(String userName, int timestamp, String website){
            this.userName = userName;
            this.timestamp = timestamp;
            this.website = website;
        }
    }
    public static List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        List<Visit> allVisits = new ArrayList<>();
        for(int i = 0; i < username.length; i++){
            allVisits.add(new Visit(username[i], timestamp[i], website[i]));
        }

        Collections.sort(allVisits, (v1, v2)->{
            return v1.timestamp - v2.timestamp;
        });

        Map<List<String>, Integer> counts = getCounts(allVisits);

        List<String> res = new ArrayList<>();
        int max = 0;
        for(Map.Entry<List<String>, Integer> entry: counts.entrySet()){
            if(max == 0 || entry.getValue() > max){
                max = entry.getValue();
                res = entry.getKey();
            } else if(entry.getValue() == max){
                if(entry.getKey().toString().compareTo(res.toString()) < 0)
                    res = entry.getKey();
            }
        }
        return res;
    }

    // helper function to get User-Webs maps
    public static Map<List<String>, Integer> getCounts(List<Visit> allVisits){
        Map<String, List<String>> userWebLists = new HashMap<>();
        for(Visit v: allVisits){
            userWebLists.putIfAbsent(v.userName, new ArrayList<>());
            userWebLists.get(v.userName).add(v.website);
        }

        Map<List<String>, Integer> res = new HashMap<>();
        for(List<String> list: userWebLists.values()){
            if(list.size() < 3) continue;
            Set<List<String>> seqs= generate3Seqs(list);
            for(List<String> seq : seqs){
                res.putIfAbsent(seq, 0);
                res.put(seq, res.get(seq) + 1);
            }
        }
        return res;
    }

    // helper function to generate all valid 3 sequences
    public static Set<List<String>> generate3Seqs(List<String> webs){
        Set<List<String>> res = new HashSet<>();
        for(int i = 0; i < webs.size(); i++){
            for(int j = i + 1; j < webs.size(); j++){
                for(int k = j + 1; k < webs.size(); k++){
                    List<String> list = new ArrayList<>();
                    list.add(webs.get(i));
                    list.add(webs.get(j));
                    list.add(webs.get(k));
                    res.add(list);
                }
            }
        }
        return res;
    }

    //Leetcode 253 - Meeting Rooms II
    public int minMeetingRooms(int[][] intervals) {
        if(intervals.length ==0)
            return 0;
        int max =0;
        int[] start = new int[intervals.length];
        int[] end = new int[intervals.length];
        int i =0;
        for(int[] interval : intervals){
            start[i] = interval[0];
            end[i] = interval[1];
            i++;
        }
        Arrays.sort(start);
        Arrays.sort(end);
        int s =0; int e =0; int count =0;
        while(s < start.length){
            if(start[s] < end[e]){
                count++;
                s++;
            }
            else{
                e++;
                count--;
            }
            max = Math.max(count, max);
        }
        return max;

    }
}
