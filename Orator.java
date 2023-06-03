public class Orator{
	public static void main(String[] args) {
		String[] wordList1 = {"architektura wielowarstwowa", "30 000 metrów", "rozwiązanie BtB", "aplikacja kliencka", "interfejs internetowy", "inteligentna karta", "rozwiązanie dynamiczne", "sześć sigma", "przenikliwość"};
		
		String[] wordList2 = {"zwiększa możliwości", "poprawia atrakcyjność", "pomnaża wartość", "opracowana dla", "bazująca na", "rozproszona", "sieciowa", "sześć sigma", "skoncentrowana na", "podniesiona na wyższy poziom", "skierowanej", "udostępniona"};
		
		String[] wordList3 = {"procesu", "punktu wpisywania", "rozwiązania", "strategii", "paradygmatu", "portalu", "witryny", "wersji", "misji"};

		int list1Length = wordList1.length;
		int list2Length = wordList2.length;
		int list3Length = wordList3.length;

		java.util.Random randomNumGenerator = new java.util.Random();
		int rnd1 = randomNumGenerator.nextInt(list1Length);
		int rnd2 = randomNumGenerator.nextInt(list2Length);
		int rnd3 = randomNumGenerator.nextInt(list3Length);

		String sentence = wordList1[rnd1] + " " + wordList2[rnd2] + " " + wordList3[rnd3];

		System.out.println("To jest to, czego nam trzeba: " + sentence);

	}
}