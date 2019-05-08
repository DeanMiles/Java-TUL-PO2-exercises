import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Random;
/*Napisz program losujacy 1000 znakow i zapisz je do pliku, a nastepnie odczytaj pliku i wypisz na ekran.
Utworz dwie rozne pary procedur zapisujaco/odczytujacych, raz korzystajac z pakietu java.io a drugi raz z pakietu
java.nio. Porownaj szybkosc zapisu i odczytu, wynik wypisz na ekranie. */
public class Task4 {

    public static void main(String[] args) throws IOException {

        final int NUMBER_OF_CHARACTERS = 1000;
        final int FIRST_ASCII_CHAR = 32;
        final int LAST_ASCII_CHAR = 126;

        String nameOfFile = "tekst.txt";
        String nameOfFile2 = "tekst2.txt";
        if (doesFileExist(nameOfFile)) {
            System.out.println("File " + nameOfFile + " exist");
        } else {
            System.out.println("There is no file " + nameOfFile);
        }

        if (doesFileExist(nameOfFile2)) {
            System.out.println("File " + nameOfFile2 + " exist");
        } else {
            System.out.println("There is no file " + nameOfFile2);
        }

        char[] randSigns = new char[NUMBER_OF_CHARACTERS];
        Random r = new Random();
        for (int i = 0; i < NUMBER_OF_CHARACTERS; i++) {
            randSigns[i] = (char)(r.nextInt(LAST_ASCII_CHAR - FIRST_ASCII_CHAR + 1) + FIRST_ASCII_CHAR);
        }
        String string = new String(randSigns);

        Stoper stoperIo = new Stoper("Test io");
        stoperIo.start();
        writeFile(nameOfFile, string);
        readFile(nameOfFile);
        stoperIo.stop();
        System.out.println(stoperIo);

        Stoper stoperNio = new Stoper("Test nio");
        stoperNio.start();
        writeFileNio(nameOfFile2,string);
        ReadFileNio(nameOfFile2);
        stoperNio.stop();
        System.out.println(stoperIo+" "+stoperNio);

    }

    public static void writeFileNio(String nazwa,String string) throws IOException {
        File file;
        file =new File(nazwa);
        java.nio.file.Files.write(Paths.get(file.toURI()), string.getBytes("utf-8"),
                StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    public static void ReadFileNio(String nazwa){
        try {
            Files.lines(Paths.get(nazwa)).forEach(System.out::println);                                                 //printing each line
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void writeFile(String nazwa, String string) throws IOException {
        FileWriter plikWy = null;
        try {
            plikWy = new FileWriter(nazwa);
            plikWy.write(string);

        } finally {
            if (plikWy != null) {
                plikWy.close();
            }
        }
    }

    public static void readFile(String nazwa) throws IOException {
        FileReader plikWe = null;
        try {
            plikWe = new FileReader(nazwa);
            int c;
            while ((c = plikWe.read()) != -1) {                                                                         // if c = -1 that means the file has ended
                System.out.print((char) c);
            }
        } finally {
            if (plikWe != null) {
                plikWe.close();
            }
        }
    }

    public static boolean doesFileExist(String nazwa) {
        File f = new File(nazwa);
        return f.exists() && f.isFile();
    }
}
