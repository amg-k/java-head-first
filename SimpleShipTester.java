
public class SimpleShipTester {
	public static void main(String[] args) {
		SimpleShip testShip = new SimpleShip();
		int[] testGrids = {2, 3, 4};
		testShip.setCoordinates(testGrids);
		int testAim = 2;
		String result = testShip.hitAssessment(testAim);

		String testResult = "Failed";
		if (result.equals("Hit")) {
			testResult = "Success";
		}
		System.out.println(testResult);
	}
}