package CriticalThinking.Module6;

import java.util.ArrayList;
import java.util.Random;

public class RandomArray {
	private ArrayList<Integer> array = new ArrayList<Integer>();

	public RandomArray(int i) {

		if (i == 0) {
			getUnsorted();
		} else if (i == 1) {
			getSorted();
		} else {
			this.array = null; 
		}
	}

	public ArrayList<Integer> getArray() {
		return this.array;
	}

	private void getUnsorted() {
		Random random = new Random();
		int randomCount = random.nextInt(15 - 10 + 1) + 10;
		for (int i = 0; i < randomCount - 1 ; i++) {
			Integer randomNumber = random.nextInt(100 - 50 + 1) + 50;
			this.array.add(randomNumber);
		}
	}

	private void getSorted() {
		Integer[][] arrays = { { 65, 67, 70, 75, 76, 78, 81, 82, 84, 85, 87, 92 }, { 67, 68, 72, 74, 76, 77, 79, 81, 83, 85, 86, 87, 89, 92 }, { 69, 71, 72, 73, 74, 76, 79, 80, 81, 85, 87, 89, 92 },
				{ 70, 71, 73, 75, 77, 78, 80, 81, 82, 83, 85, 86, 87, 89, 92 }, { 65, 67, 71, 72, 74, 76, 78, 80, 82, 84, 88, 90, 92 },	{ 68, 70, 72, 73, 74, 75, 77, 78, 82, 84, 86, 88, 90, 92 }, 
				{ 66, 68, 70, 72, 74, 75, 77, 78, 80, 82, 84, 86, 88, 90, 92 },	{ 69, 71, 73, 74, 79, 80, 82, 84, 86, 88, 90, 92, 93 },	{ 70, 72, 74, 76, 77, 79, 81, 84, 86, 88, 89, 90, 92, 93 },
				{ 65, 67, 69, 71, 73, 75, 77, 79, 80, 82, 84, 86, 88, 89, 92 }, { 68, 70, 72, 74, 75, 77, 79, 81, 83, 85, 87, 88, 90, 92, 93 },	{ 66, 68, 70, 71, 73, 77, 79, 81, 83, 85, 87, 89, 91, 92 },
				{ 69, 70, 72, 74, 76, 77, 79, 81, 83, 85, 87, 89, 90, 92, 93 },	{ 70, 72, 74, 76, 78, 79, 81, 83, 85, 87, 89, 91, 92, 95 },	{ 67, 69, 71, 73, 75, 76, 80, 82, 84, 86, 87, 89, 92 },
				{ 68, 70, 72, 76, 78, 79, 81, 83, 85, 87, 89, 91, 92, 93 },	{ 66, 68, 70, 72, 74, 76, 77, 79, 81, 85, 87, 89, 91, 93 },	{ 69, 71, 73, 75, 76, 78, 80, 82, 84, 86, 88, 91, 92, 94 },
				{ 70, 72, 74, 76, 78, 80, 82, 84, 86, 88, 89, 91, 94, 95 },	{ 65, 67, 69, 71, 73, 75, 77, 79, 80, 82, 84, 86, 88, 90, 92 } };
		Random random = new Random();
		int randomArray = random.nextInt(20 - 0 + 1) + 0;
		for (int i = 0; i < arrays[randomArray].length - 1; i++) {
			this.array.add(arrays[randomArray][i]);
		}
	}
}
