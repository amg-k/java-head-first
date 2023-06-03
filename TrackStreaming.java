class TrackStreaming {
	String title;
	String artist;
	int trackLength;

	void play() {
		System.out.println("Odtwarzam piosenkę");
	}
	
	void showData() {
		System.out.println("To jest utwór " + title + " grany przez " + artist);
	}
}

class TrackStreamingTester {
	public static void main(String[] args) {
		TrackStreaming track = new TrackStreaming();
		track.artist = "The Beatles";
		track.title = "Come Together";
		track.play();
		track.showData();
	}
}