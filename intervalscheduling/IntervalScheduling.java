package intervalscheduling;

import java.util.Arrays;
import java.util.Comparator;

public class IntervalScheduling {
  public static void main(String[] args) {
    Kattio kattio = new Kattio(System.in);
    int numberOfIntervals;
    int[][] intervals = null;
    int index = 0;
    while (kattio.hasMoreTokens()) {
      if (index == 0) {
        numberOfIntervals = kattio.getInt();
        intervals = new int[numberOfIntervals][3];
      } else {
        intervals[index][0] = kattio.getInt();
        intervals[index][1] = kattio.getInt();
        intervals[index][2] = kattio.getInt();
      }
      index++;
    }
    kattio.close();
    sortbyColumn(intervals, 1);
    System.out.println(solveIntervalSchedulingProblem(intervals));
  }

  static int solveIntervalSchedulingProblem(int[][] intervals) {
    int numberOfIntervals = 1;
    int[] interval = intervals[0];

    for (int i = 1; i < intervals.length; i++) {
      if (intervals[i][0] >= interval[1]) {
        interval = intervals[i];
        numberOfIntervals++;
      }
    }

    return numberOfIntervals;
  }

  static void sortbyColumn(int[][] arr, int col) {
    Arrays.sort(arr, new Comparator<int[]>() {
      public int compare(final int[] entry1, final int[] entry2) {
        if (entry1[col] > entry2[col])
          return 1;
        else
          return -1;
      }
    });
  }
}
