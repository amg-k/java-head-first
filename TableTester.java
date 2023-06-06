class TableTester {
	public static void main(String[] args) {
		int[] index = new int[4];
		index[0] = 1;
		index[1] = 3;
		index[2] = 0;
		index[3] = 2;

		String[] islands = new String[4];
		islands[0] = "Bermudy";
		islands[1] = "Malediwy";
		islands[2] = "Azory";
		islands[3] = "Cozumel";



		int y = 0;

		int ref;

		while (y < 4) {
			ref = index[y];
			System.out.print("wyspa = ");
			System.out.println(islands[ref]);
			y = y + 1;
		}
	}
}