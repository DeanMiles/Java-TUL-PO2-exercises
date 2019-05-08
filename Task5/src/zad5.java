import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.util.Scanner;
/*Napisz program odczytujacy zawartosc pliku tekstowego. Sciezka do pliku powinna byc podawana przez uzytkownika po uruchomieniu programu oraz zapisywana do osobnego pliku.
Po nacisnięciu dowolnego klawisza program pobiera kolejny ciąg znakow o losowej dlugosci (od 1 do 5 znakow) z wczytanego pliku tekstowego i wyswietla go na ekranie.
 */

public class zad5{
    public static void main(String[] args) throws IOException{                                                          //The program has been wrote without listener
                                                                                                                        //continuation of printing chars works with ENTER
        Scanner myObj = new Scanner(System.in);
        String nameOfFile ="";
        do {
            System.out.println("Input file path");
            nameOfFile = myObj.nextLine();
        }while( !Files.isReadable(Paths.get(nameOfFile)));
        String nameOfFile2 = "write.txt";
        boolean a = true;

        writeFile(nameOfFile2,nameOfFile);
        Random r = new Random();
        int howManyRand;
        for (int i = 0; ; i++) {
            howManyRand = (r.nextInt(5) + 1);
            if(readFile(nameOfFile, i, howManyRand)) {
                i += howManyRand - 1;
                Scanner newLine = new Scanner(System.in);
                String x = newLine.nextLine();
            }
            else{
                System.out.println();
                System.out.println("Thats all");
                break;
            }
        }
    }

    public static boolean readFile(String nazwa,int begin, int howManyRand) throws IOException {
        FileReader fileInput = null;
        try {
            fileInput = new FileReader(nazwa);
            int c;
            for (int i = 0; i < howManyRand + begin; i++) {
                if ((c = fileInput.read()) == -1) {
                    return false;
                }
                if (i >= begin) {
                    System.out.print((char) c);
                }
            }
        }
        catch(IOException exception) {
        System.out.println("Wrong input");
        return false;
        }
        finally {
            if (fileInput != null) {
                fileInput.close();
            }
        }
        return true;
    }

    public static void writeFile(String nazwa, String string) throws IOException {
        FileWriter fileOutput = null;
        try {
            fileOutput = new FileWriter(nazwa);
            fileOutput.write(string);
        } finally {
            if (fileOutput != null) {
                fileOutput.close();
            }
        }
    }
}