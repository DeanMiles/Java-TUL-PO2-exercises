import java.util.*;
import java.io.*;
/*Napisz program proszacy o podanie 2 wektorow (wektor to ciag liczb). Koniec wektora oznacza sie za pomoca wcisniecia klawisza enter.
Jezeli podany ciag nie jest liczba, jest ignorowany. Nastepnie nalezy sprobowac dodac wektory, jezeli sa rownej dlugosci.
Jezeli nie, sa, rzucany jest wlasny wyjatek WektoryRoznejDlugosciException, za pomoca ktorego mozna podac, a nastepnie odczytac dlugosci tych wektorow
(nalezy tak skonstruowac wyjatek, aby mozliwe bylo skonstruowanie zdania po jego przechwyceniu : "Dlugosc pierwszego wektora to AA a drugiego to BB).
Jezeli sa rownej dlugosci, wynik dodawania zapisywany jest do pliku. Jezeli nie sa rownej dlugosci, uzytkownik jest proszony o ponowne wprowadzenie tych wektorow.*/
//Program allows add only 2 vectors, by 1 in both line
public class Task6 {

    public static void main(String[] args) {
        try {
            System.out.println("Program allows add two integers with the same length:");
            InputStreamReader inputStream = new InputStreamReader(System.in);
            BufferedReader readText = new BufferedReader(inputStream);
            Vector<Double> result = null;
            Vector<Double> A ;
            Vector<Double> B ;
            boolean again = false;                                                                                      //value which decide about inputting
            do {
                again = false;
                do {
                    System.out.print("Input the first vector: ");
                    A = readVec(readText.readLine());
                }while(A==null);

                do {
                    System.out.print("Input the second vector: ");
                    B = readVec(readText.readLine());
                }while(B==null);

                try {
                    result = addVec(A, B);
                } catch(WektoryRoznejDlugosciException exception) {
                    System.out.println("Error: " + exception.getMessage() + " " + exception.firstValue +
                            " != " + exception.secondValue);
                    System.out.println("Repeat the vectors :C");
                    again = true;
                }
            } while(again);
            saveVec(result, "sum.txt");
            for( int i = 0; i < result.size(); i++) {
                Double value = result.get(i);
                System.out.println(value);
            }
            readText.close();
            inputStream.close();
        } catch(IOException exception) {
            System.out.println("Wrong input" + exception.getLocalizedMessage());
        }
    }

    static Vector<Double> addVec(Vector<Double> A, Vector<Double> B) throws WektoryRoznejDlugosciException {
        if(countVec(A.toString()) != countVec(B.toString()))
            throw new WektoryRoznejDlugosciException(countVec(A.toString()), countVec(B.toString()));
        Vector<Double> C = new Vector<Double>(A.size());
        for(int i = 0; i < A.size(); ++i)
            C.add(A.elementAt(i) + B.elementAt(i));
        return C;
    }

    static Vector<Double> readVec(String l) {
        Scanner scan = new Scanner(l);
        Vector<Double> vector = new Vector<Double>();
        while(scan.hasNext()) {
            if( !scan.hasNextDouble())
                return null;
            if(scan.hasNextDouble())
                vector.add(scan.nextDouble());
            else
                scan.next();
        }
        scan.close();
        return vector;
    }

    static void saveVec(Vector<Double> v, String f) throws IOException {
        OutputStream outPut = new FileOutputStream(f);
        Writer write = new OutputStreamWriter(outPut);
        PrintWriter printer = new PrintWriter(write);
        for(int i = 0; i < v.size(); ++i) {
            printer.print(v.elementAt(i) + " ");
        }
        printer.close();
        write.close();
        outPut.close();
    }

    static int countVec(String l){
        return l.length()-4;                                                                                            //subtraction 4
    }
}
