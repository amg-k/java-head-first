public class MysteryPool4 {
	public static void main(String[] args) {
		Value[] values = new Value[6];
		int number = 1;
		int i = 0;
		while (i < 6) {
			values[i] = new Value();
			values[i].valueInt = number;
			number = number * 10;
			i = i + 1;
		}
		
		int result = 0;
		i = 6;
		while (i > 0) {
			i = i - 1;
			result = result + values[i].makeSmth(i);
		}
		System.out.println("Result " + result);
	}
}

class Value {
	int valueInt;
	public int makeSmth(int arg) {
		if (valueInt > 100) {
			return valueInt * arg;
		} else {
			return valueInt * (5 - arg);
		}
	}
}