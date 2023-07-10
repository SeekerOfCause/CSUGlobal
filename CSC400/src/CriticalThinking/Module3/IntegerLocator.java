package CriticalThinking.Module3;

import java.util.HashSet;
import java.util.Set;

public class IntegerLocator {
	public static void main(String[] args) {


		Set<Integer> ints = new HashSet<Integer>();

		int n = randomIntGen(0, 25);
		int min = 1;
		
		System.out.println("Nubmer of integers: " + n + "\n");

		for (int i = 0; i < n - 1; i++) {

			int randomInt = randomIntGen(min, n);
			if (ints.contains(randomInt)) {
				i--;
			} else {
				ints.add(randomInt);
			}
		}

		String dispInts = ints.toString();

		System.out.println("Given numbers: " + dispInts + "\n");
		
		for (int i = 1; i <= n; i++) {
			if (!ints.contains(i)) {
				System.out.println("Number missing: " + i);
			}
		}

	}

	private static int randomIntGen(int min, int max) {
		return (int) (Math.random() * (max - min + 1)) + min;
	}
}
