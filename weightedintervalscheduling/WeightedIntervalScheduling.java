package weightedintervalscheduling;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class WeightedIntervalScheduling {
  static int[][] intervals;
  static Integer[] M;

  public static void main(String[] args) {
    intervals = parseInput();
    sortbyColumn(intervals, 1);
    M = new Integer[intervals.length];
    for (int i = 0; i < intervals.length; i++) {
      M[i] = null;
    }

    System.out.println(M_Compute_Opt(intervals.length - 1));
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

  static int p(int i) {
    int low = 0, high = i - 1;

    while (low <= high) {
      int mid = (low + high) / 2;
      if (intervals[mid][1] <= intervals[i][0]) {
        if (intervals[mid + 1][1] <= intervals[i][0])
          low = mid + 1;
        else
          return mid;
      } else
        high = mid - 1;
    }
    return 0;
  }

  static int M_Compute_Opt(int j) {
    if (j == 0) {
      return 0;
    } else if (M[j] != null) {
      return M[j];
    } else {
      M[j] = Math.max(M_Compute_Opt(j - 1), intervals[j][2] + M_Compute_Opt(p(j)));
      return M[j];
    }
  }

  static int[][] parseInput() {
    File file = new File(
        "/Users/janschill/projects/university/master/1_semester/algorithm_design/kattis/src/weightedintervalscheduling/2.in");
    BufferedReader br = null;
    int[][] intervals = null;

    try {
      // br = new BufferedReader(new InputStreamReader(System.in));
      br = new BufferedReader(new FileReader(file));
      int numberOfIntervals = Integer.parseInt(br.readLine().trim());
      intervals = new int[numberOfIntervals][3];

      for (int i = 0; i < numberOfIntervals; i++) {
        String interval = br.readLine();
        String[] pointInfo;
        pointInfo = interval.trim().split("\\s+");
        intervals[i][0] = Integer.parseInt(pointInfo[0]);
        intervals[i][1] = Integer.parseInt(pointInfo[1]);
        intervals[i][2] = Integer.parseInt(pointInfo[2]);
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