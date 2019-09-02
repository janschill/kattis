package countingstars;

public class CountingStars {
	public static void main(String[] args) {
		String[] sampleData = new String[10];
		sampleData[0] = "#################---";
		sampleData[1] = "##-###############--";
		sampleData[2] = "#---################";
		sampleData[3] = "##-#################";
		sampleData[4] = "########---#########";
		sampleData[5] = "#######-----########";
		sampleData[6] = "########---#########";
		sampleData[7] = "##################--";
		sampleData[8] = "#################---";
		sampleData[9] = "##################-#";
		countStars(sampleData);
	}

	private static boolean[][] parseStars(String[] sampleData, int rows, int columns) {
		boolean[][] starField = new boolean[rows][columns];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if (sampleData[i].charAt(j) == '#') {
					starField[i][j] = false;
				} else {
					starField[i][j] = true;
				}
			}
		}
		
		return starField;
	}
	
	private static boolean checkNeighbors(boolean[][] starField, int row, int column) {
		return false;
	}
	
	private static void countStars(String[] sampleData) {
		
	}
}
