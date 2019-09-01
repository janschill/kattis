package detaileddifferences;

import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		double startTime = System.nanoTime();
//		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		String path = "/Users/janschill/projects/eclipse-workspace/kattis/src/detaileddifferences/sample.in";
		BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
		String result = "";
		
		while (true) {
			int numberOfLines = Integer.parseInt(bufferedReader.readLine());
			
			for (int i = 0; i < numberOfLines; i++) {
				String originalString = bufferedReader.readLine();
				String mutatedString = bufferedReader.readLine();
				result += originalString + "\n";
				result += mutatedString + "\n";
				
				if (originalString.compareTo(mutatedString) == 0) {
					result += ".".repeat(originalString.length());
				} else {
					for (int k = 0; k < originalString.length(); k++) {
						result += originalString.charAt(k) == mutatedString.charAt(k) ? "." : "*";
					}
				}
				result += "\n" + "\n";
			}
			break;
		}
		
		System.out.println(result);
		
		bufferedReader.close();
		double endTime = System.nanoTime();
		double duration = (endTime - startTime) / 1000000 / 1000;
		System.out.println(duration + " s");
	}
}
