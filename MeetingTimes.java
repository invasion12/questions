import java.util.*;

import org.junit.*;

import static org.junit.Assert.*;

class Main {
  public static void main(String[] args) {

    List<Interval> intervals = new ArrayList<Interval>();
    intervals.add(new Interval(1,3));
    intervals.add(new Interval(6,7));
    intervals.add(new Interval(2,4));
    intervals.add(new Interval(2,3));
    intervals.add(new Interval(9,12));

    // Sort the interval
    intervals.sort((interval1, interval2) -> {
      if (interval1.start == interval2.start) {
        return interval2.end - interval1.end;
      } 
      return interval1.start - interval2.start;
    });

    // merge the overlapping intervals
    List<Interval> mergedInterval = new ArrayList<Interval>();
    int start = intervals.get(0).start;
    int end = intervals.get(0).end;
    for (Interval interval : intervals) {
      if (interval.start <= end) {
        end = Math.max(end, interval.end);
      } else {
        mergedInterval.add(new Interval(start, end));
        start = interval.start;
        end = interval.end;
      }
    }
    mergedInterval.add(new Interval(start, end));

    // Find the interval which do not merge 
    List<Interval> ans = new ArrayList<Interval>();
    for (int i =1; i < mergedInterval.size(); i++) {
      if (mergedInterval.get(i-1).end < mergedInterval.get(i).start) {
        ans.add(new Interval(mergedInterval.get(i-1).end, mergedInterval.get(i).start));
      }
    }

    // print the answer
    for (Interval interval : ans)
      System.out.println("[" + interval.start + "," + interval.end + "]");
  }

  static class Interval {
    int start;
    int end;
    Interval(int lStart, int lEnd) {
      start = lStart;
      end = lEnd;
    }
  }
}
