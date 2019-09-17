package plantingtrees;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

public class PlantingTrees {
	private static Integer getDays() throws NumberFormatException, IOException {
//		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		String path = "/Users/janschill/projects/eclipse-workspace/kattis/src/plantingtrees/trees.1.in";
		BufferedReader bufferedReader = new BufferedReader(new FileReader(path));

		Integer numberOfSeedlings= Integer.parseInt(bufferedReader.readLine());
		String[] stringSeedlings = bufferedReader.readLine().split(" ");
		Integer[] seedlings = new Integer[numberOfSeedlings];
		for (int i = 0; i < stringSeedlings.length; i++) {
			seedlings[i] = Integer.parseInt(stringSeedlings[i]);
		}
		Arrays.sort(seedlings, Collections.reverseOrder());
		for (int i = 1; i <= numberOfSeedlings; i++) {
			seedlings[i-1] = seedlings[i-1] + i;
		}

		Arrays.sort(seedlings, Collections.reverseOrder());
		bufferedReader.close();

		return seedlings[0] + 1;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.out.print(getDays());
	}
}
