package knapsack;

public class Knapsack {
  public static void main(String[] args) {
    int[] values = new int[] { 0, 5, 4, 3, 2 };
    int[] weights = new int[] { 4, 3, 2, 1 };
    solveKnapsack(6, 4, values, weights);
  }

  private static void solveKnapsack(int capacityOfSack, int numberOfItems, int[] values, int[] weights) {
    int[][] valueMatrix = new int[numberOfItems][capacityOfSack];

    for (int i = 1; i < numberOfItems; i++) {
      for (int j = 0; j < capacityOfSack; j++) {
        if (weights[i] > j) {
          valueMatrix[i][j] = valueMatrix[i - 1][j];
        } else {
          valueMatrix[i][j] = Math.max(valueMatrix[i - 1][j], valueMatrix[i - 1][j - weights[i]] + values[i]);
        }
        System.out.println("[" + i + "]" + "[" + j + "] = " + valueMatrix[i][j]);
      }
    }
  }
}
