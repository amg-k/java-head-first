SŁOWA KLUCZOWE

abstract - abstrakcyjna klasa lub metoda
    klasa - stanowi wzorzec konieczny do rozszerzenia przez dziedziczenie, nie można tworzyć obiektów klasy abstrakcyjnej; klasa zawierająca przynamniej jedną metodę akstrakcyjną musi być abstrakcyjna
    metoda - pusta metoda klasy abstrakcyjnej, wzorzec do zaimplementowania w podklasie

final - stała, klasa lub metoda, której nie można przesłonić
    klasa - nie można jej rozszerzać, wszystkie jej metody są finalne, pola nie są finalne,
    metoda - nie można jej przesłonić,
    pole - po utworzeniu nie może być zmienione,

private - cecha dostępna tylko dla metod określonej klasy, podklasy nie mają dostępu
    metoda - mogą ich używać tylko inne metody z danej klasy
    pole - mają do niego dostęp tylko metody z danej klasy

protected - cecha dostępna tylko dla metod określonej klasy, jej potomków i innych klas z tego samego pakietu

public - cecha dostępna dla wszystkich metod z wszystkich klas
    klasa - widoczna dla innych klas, zapisana w osobnym pliku
    metoda - może ją wywołać każda inna metoda z każdej innej klasy
    pole - ma do niego dostęp każda inna metoda z każdej innej klasy

static - cecha właściwa tylko swojej klasie, nie jej obiektom
    pole - nie ma go w obiektach danej klasy, występuje tylko w jednym egzemplarzu (stała = public static final)
    metoda - nie działa na obiekcie a na klasie, ma dostęp do pól statycznych   klasy a nie do pól obiektów


