class Book {

	String title;
	String author;
}

class BookTester {
	public static void main(String[] args) {

		Book[] myBooks = new Book[3];

		int x = 0;
		
		myBooks[0] = new Book();
		myBooks[1] = new Book();
		myBooks[2] = new Book();

		myBooks[0].title = "Czterej koderzy i Java";
		myBooks[1].title = "Java nocy letniej";
		myBooks[2].title = "Java. Receptury";

		myBooks[0].author = "Janek";
		myBooks[1].author = "Wilhelm";
		myBooks[2].author = "Ian";

		while (x < 3) {
			System.out.print(myBooks[x].title);
			System.out.print(", autor ");
			System.out.println(myBooks[x].author);
			x = x + 1;
		}
	}
}