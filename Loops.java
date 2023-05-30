public class Loops {
	public static void main(String[] args) {
		int x = 1;
		System.out.println("Before loop");
		while (x < 4) {
			System.out.println("In loop");
			System.out.println("Value x = " + x);
			x = x + 1;
		}
		System.out.println("After loop");
	}
}