public class bottleSong {
	public static void main(String[] args) {
		int bottleAmount = 10;
		String word = "bottles";

		while (bottleAmount > 0) {
			
			if (bottleAmount == 2) {
				word = "bottle";
			}
			
			System.out.println(bottleAmount + " green " + word + ", hanging on the wall");
			System.out.println(bottleAmount + " green " + word + ", hanging on the wall");
			System.out.println("And of one green bottle should accidently fall,");
		
			bottleAmount = bottleAmount - 1;

			if (bottleAmount > 0) {
				System.out.println("There'll be " + bottleAmount + " green " + word + ", hanging on the wall");
			
			} else {
				System.out.println("There'll bo no green bottles, hanging on the wall");
			}
		}
	}
}