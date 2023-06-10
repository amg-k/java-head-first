public class SimpleShip {
	private int[] coordinates;
	private int hitCount = 0;

	public void setCoordinates(int[] inCoordinates) {
		coordinates = inCoordinates;
	}

	public String hitAssessment(int aim) {
		String result = "Miss";
		for (int grid : coordinates) {
			if (aim == grid) {
				result = "Hit";
				hitCount++;
				break;
			}
		}
		if (hitCount  == coordinates.length) {
			result = "Sinked!";
		}
		System.out.println(result);
		return result;
	}
}
