class Echo {
	int amount = 0;
	void greet() {
		System.out.println("Heeelooooo...");
	}
}

class EchoTester {
	public static void main(String[] args) {
		Echo e1 = new Echo();
		Echo e2 = new Echo();

		int x = 0;
		
		while (x < 4) {
			e1.greet();
			e1.amount = e1.amount + 1;

			if (x > 0) {
				e2.amount = e2.amount + 1;
			}

			if (x > 1) {
				e2.amount = e2.amount + e1.amount;
			}
			x = x + 1;
		}

		System.out.println(e2.amount);
	}
}