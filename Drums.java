class Drums {
	
	boolean cymbal = true;
	boolean drum = true;

	void playCymbal() {
		System.out.println("brzdęk, brzrzrzdęęk");
	}

	void playDrum() {
		System.out.println("bam, bam, baaa-am-am");
	}
}

class DrumsTester {
	public static void main(String[] args) {	
		Drums d = new Drums();

		d.playDrum();	
		d.drum = false;
		d.playCymbal();

		if (d.drum == true) {
			d.playDrum();
		}
	}
}