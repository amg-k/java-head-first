class Episode {
	
	int seasonNum;
	int episodeNum;

	void skipIntro() {
		System.out.println("Pomijam intro...");
	}

	void goToNext() {
		System.out.println("Wczytuję następny odcinek...");
	}

	void getSeasonNum() {
		System.out.println("Numer sezonu: " + seasonNum);
	}
}

class EpisodeTester {
	public static void main(String[] args) {
		Episode episode = new Episode();
		episode.seasonNum = 4;
		episode.skipIntro();
		episode.getSeasonNum();
	}
}