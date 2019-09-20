package intervalscheduling;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class IntervalScheduling {
  public static void main(String[] args) {
    List<int[]> intervals = parseInput();
    sortbyColumn(intervals, 1);
    System.out.println(solveIntervalSchedulingProblem(intervals));
  }

  static int solveIntervalSchedulingProblem(List<int[]> intervals) {
    int numberOfIntervals = 0;

    while (intervals.size() > 0) {
      int finishTime = intervals.get(0)[1];
      numberOfIntervals++;
      removeOverlappingIntervals(intervals, finishTime);
    }

    return numberOfIntervals;
  }

  static void removeOverlappingIntervals(List<int[]> intervals, int finishTime) {
    List<int[]> removeFromList = new ArrayList<>();
    for (int[] interval : intervals) {
      if (!isNotOverlapping(interval[0], interval[1], finishTime)) {
        removeFromList.add(interval);
      }
    }
    intervals.removeAll(removeFromList);
  }

  static boolean isNotOverlapping(int startTime, int endTime, int referenceFinsishTime) {
    return startTime >= referenceFinsishTime;
  }

  static void sortbyColumn(List<int[]> arr, int col) {
    Collections.sort(arr, new Comparator<int[]>() {
      public int compare(final int[] entry1, final int[] entry2) {
        if (entry1[col] > entry2[col])
          return 1;
        else
          return -1;
      }
    });
  }

  static List<int[]> parseInput() {
    File file = new File(
        "/Users/janschill/projects/university/master/1_semester/algorithm_design/kattis/src/intervalscheduling/2.in");
    BufferedReader br = null;
    List<int[]> intervals = new ArrayList<int[]>();

    try {
      // br = new BufferedReader(new InputStreamReader(System.in));
      br = new BufferedReader(new FileReader(file));
      int numberOfIntervals = Integer.parseInt(br.readLine().trim());

      for (int i = 0; i < numberOfIntervals; i++) {
        String interval = br.readLine();
        String[] pointInfo;
        pointInfo = interval.trim().split("\\s+");
        intervals.add(new int[] { Integer.parseInt(pointInfo[0]), Integer.parseInt(pointInfo[1]) });
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (br == null)
        return null;
    }

    return intervals;
  }
}