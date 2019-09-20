package closestpair;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.geom.Point2D;
import java.util.*;

public class ClosestPair {
  private static List<List<Point2D.Double>> parseFile() throws NumberFormatException, IOException {
//		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    String path = "/Users/janschill/projects/university/master/1_semester/algorithm_design/kattis/src/closestpair/closestpair2.in";
    BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
    ArrayList<List<Point2D.Double>> testCases = new ArrayList<List<Point2D.Double>>();

    while (true) {
      int numberOfPoints = Integer.parseInt(bufferedReader.readLine());
      if (numberOfPoints == 0) {
        break;
      }

      List<Point2D.Double> points = new ArrayList<Point2D.Double>();

      for (int i = 0; i < numberOfPoints; i++) {
        String[] pointString = bufferedReader.readLine().split(" ");
        Point2D.Double point = new Point2D.Double(Double.parseDouble(pointString[0]), Double.parseDouble(pointString[1]));
        points.add(point);
      }
      testCases.add(points);
    }
    bufferedReader.close();

    return testCases;
  }

  public static Pair findClosestPairDivedAndConquer(List<Point2D.Double> points) {
    List<Point2D.Double> px = new ArrayList<>(points);
    px.sort(Comparator.comparing(Point2D::getX));
    List<Point2D.Double> py = new ArrayList<>(points);
    py.sort(Comparator.comparing(Point2D::getY));

    return closestPairRec(px, py);
  }

  private static Pair closestPairRec(List<Point2D.Double> px, List<Point2D.Double> py) {
    int pxSize = px.size();

    if (pxSize <= 3) {
      return findClosestPairQuadratic(px);
    }

    int pySize = py.size();
    // left by x
    List<Point2D.Double> qx = new ArrayList<>(px.subList(0, (pxSize) / 2));
    // right by y
    List<Point2D.Double> rx = new ArrayList<>(px.subList((pxSize) / 2, pxSize));
    // left by x
    List<Point2D.Double> qy = new ArrayList<>(py.subList(0, (pySize) / 2));
    // right by y
    List<Point2D.Double> ry = new ArrayList<>(py.subList((pySize) / 2, pySize));

    Pair closestPairQ = closestPairRec(qx, qy); // left
    Pair closestPairR = closestPairRec(rx, ry); // right

    double shortestDistance = Double.min(closestPairQ.p1.distance(closestPairQ.p2),
        closestPairR.p1.distance(closestPairR.p2));
    double maxCoordinateX = findMaxCoordinateX(qx);
    List<Point2D.Double> S = findPointsWithinL(px, shortestDistance, maxCoordinateX);
    S.sort(Comparator.comparing(Point2D::getY));

    Pair closestPairS = null;
    double minimumDistanceS = Double.MAX_VALUE;
    for (int i = 0; i < S.size(); i++) {
      for (int j = i + 1; (j < S.size()) && (S.get(j).y - S.get(i).y < shortestDistance); j++) {
        double distance = S.get(i).distance(S.get(j));
        if (distance < shortestDistance) {
          closestPairS = new Pair(S.get(i), S.get(j));
          if (distance < minimumDistanceS) {
            closestPairS = new Pair(S.get(i), S.get(j));
            minimumDistanceS = distance;
          }
        }
      }
    }

    if (closestPairS != null && minimumDistanceS < shortestDistance) {
      return closestPairS;
    } else if (closestPairQ.p1.distance(closestPairQ.p2) < closestPairR.p1.distance(closestPairR.p2)) {
      return closestPairQ;
    }

    return closestPairR;
  }

  private static List<Point2D.Double> findPointsWithinL(List<Point2D.Double> points, double shortestDistance,
      double maxCoordinateX) {
    List<Point2D.Double> pointsWithinDistance = new ArrayList<>();

    for (Point2D.Double point : points) {
      if (Math.abs(point.x - maxCoordinateX) <= shortestDistance) {
        pointsWithinDistance.add(point);
      }
    }

    return pointsWithinDistance;
  }

  private static double findMaxCoordinateX(List<Point2D.Double> points) {
    return points.get(points.size() - 1).x;
  }

  public static Pair findClosestPairQuadratic(List<Point2D.Double> points) {
    double minDistance = Double.MAX_VALUE;
    Pair closestPair = null;

    for (int i = 0; i < points.size(); i++) {
      for (int j = i + 1; j < points.size(); j++) {
        double distance = points.get(i).distance(points.get(j));
        if (distance < minDistance) {
          minDistance = distance;
          closestPair = new Pair(points.get(i), points.get(j));
        }
      }
    }
    return closestPair;
  }

  private static void printAllPairs(List<List<Point2D.Double>> testCases) {
    System.out.println(testCases.size());
    for (int i = 0; i < testCases.size(); i++) {
      Pair closestPair = findClosestPairDivedAndConquer(testCases.get(i));
      System.out.println(closestPair.p1.x + " " + closestPair.p1.y + " " + closestPair.p2.x + " " + closestPair.p2.y);
    }
  }

  public static void main(String[] args) throws NumberFormatException, IOException {
    List<List<Point2D.Double>> testCases = parseFile();
    printAllPairs(testCases);
  }

}

class Pair {
  Point2D.Double p1;
  Point2D.Double p2;

  public Pair(Point2D.Double p1, Point2D.Double p2) {
    this.p1 = p1;
    this.p2 = p2;
  }
}
