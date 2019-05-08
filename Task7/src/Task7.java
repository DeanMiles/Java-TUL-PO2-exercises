import java.util.*;

/*
Napisz klase NrTelefoniczny, posiadajaca 2 pola: nrkierunkowy i nrTelefonu i implementujaca interfejs Comparable. Nastepnie utworz abstrakcyjna klase Wpis a nastepnie dziedziczace z niej klasy Osoba i Firma.
Klasa Wpis ma abstrakcyjna metode opis, ktora opisuje dany wpis. Byc moze ma rowniez inne metody abstrakcyjne lub nie w miare potrzeb. Klasa Osoba ma zawierac informacje o imieniu, nazwisku, adresie i (w tym nrTelefonu).
Klasa Firma ma miec nazwe i adres( w tym NrTelefonu). Utworz kilka obiektow klasy Osoba i kilka obiektow klasy Firma i umiesc je w kontenerze TreeMap, poslugujac sie jako kluczem numerem telefonicznym.
Nastepnie wypisz utworzona w ten sposob ksiazke telefoniczna za pomoca iteratora.
 */
class Zad7 {

    public static void main(String[] args) {
        String[] names = {"Alan", "Marcin", "Tomasz", "Andrzej", "Katarzyna", "Marek", "Dorota", "Małgorzata",          //arrays for creating examples of people and companies
                "Julian", "Jan", "Filip", "Maria", "Aleksandara", "Karolina", "Robert"};
        String[] surnames = {"Kowalski", "Nowak", "Kwiatkowski", "Wiśniewski", "Malec", "Kowalczyk", "Wójcik",
                "Woźniak", "Mazur", "Zając", "Krawczyk", "Król", "Wróble", "Stępień", "Pawlak"};
        String[] addres = {"Opoczno", "Łodz", "Tomaszow Mazowiecki", "Gorzow Wielkopolski", "Warszawa", "Kraków",
                "Wroclaw", "Gdansk", "Poznan", "Lublin", "Radom", "Sosnowiec", "Plock", "Katowice", "Szczecin"};
        String[] companyName = {"Artmed", "BrukPol", "Asus", "Ericson", "Millenium", "Decathlon", "Tesco", "House",
                "Hugo Boss", "Warka"};
        List<Osoba> osobaLista = new ArrayList<Osoba>(15);
        List<Firma> firmaLista = new ArrayList<>(10);
        TreeMap<NrTelefoniczny,Wpis> sorter = new TreeMap<NrTelefoniczny,Wpis>();                                                         //TreeMap

        for(int i = 0; i < 15; ++i){
            Osoba osoba = new Osoba(getRandom(names),getRandom(surnames),getRandom(addres));
            osobaLista.add(osoba);
            Firma firma = new Firma(getRandom(companyName),getRandom(addres));
            firmaLista.add(firma);
            sorter.put(new NrTelefoniczny(), osoba);
            sorter.put(new NrTelefoniczny(), firma);
        }

        Set<Map.Entry<NrTelefoniczny,Wpis>> entrySet = sorter.entrySet();
        for(Map.Entry<NrTelefoniczny,Wpis> entry: entrySet) {
            System.out.println(entry.getKey().nrTelefonu + " : " + entry.getValue().opis());
        }
        System.out.println("Liczba numerow: "+ sorter.size());

    }

    public static String getRandom(String[] array) {
        int idx = new Random().nextInt(array.length);
        String random = (array[idx]);
        return random;
    }
}





