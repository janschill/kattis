package closestpair;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClosestPair {
	private static List<Point[]> parseFile() throws NumberFormatException, IOException {
//		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		String path = "/Users/janschill/projects/eclipse-workspace/kattis/src/closestpair/closestpair2.in";
		BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
		ArrayList<Point[]> testCases = new ArrayList<Point[]>();

		while (true) {
			int numberOfPoints = Integer.parseInt(bufferedReader.readLine());
			if (numberOfPoints == 0) {
				break;
			}

			Point[] points = new Point[numberOfPoints];

			for (int i = 0; i < numberOfPoints; i++) {
				String[] pointString = bufferedReader.readLine().split(" ");
				Point point = new Point(Double.parseDouble(pointString[0]), Double.parseDouble(pointString[1]));
				points[i] = point;
			}
			testCases.add(points);
		}
		bufferedReader.close();
		
		return testCases;
	}

	private static double distanceBetweenTwoPoints(double p1x, double p1y, double p2x, double p2y) {
		return Math.sqrt((Math.pow(p1x - p2x, 2)) + (Math.pow(p1y - p2y, 2)));
	}

	private static String findClosestPair(Point[] points) {
		double shortestDistance = Double.MAX_VALUE;
		String result = "";

		for (int i = 0; i < points.length; i++) {
			for (int k = i + 1; k < points.length; k++) {
				double distance = distanceBetweenTwoPoints(points[i].getX(), points[i].getY(), points[k].getX(), points[k].getY());
				
				if (distance < shortestDistance) {
					shortestDistance = distance;
					result = points[k].getX() + " " + points[k].getY() + " " + points[i].getX() + " " + points[i].getY();
				}
			}
		}

		return result;
	}

	private static void printAllPairs(List<Point[]> testCases) {
		for (int i = 0; i < testCases.size(); i++) {
			System.out.println(findClosestPair(testCases.get(i)));
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		double startTime = System.nanoTime();
		List<Point[]> testCases = parseFile();
		printAllPairs(testCases);
		double endTime = System.nanoTime();
		double duration = (endTime - startTime) / 1000000 / 1000;
		System.out.println(duration + " s");
	}

}

class Point {
	private double x;
	private double y;

	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	public String toString() {
		return "(" + x + "," + y + ")";
	}
}