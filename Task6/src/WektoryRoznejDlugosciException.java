public class WektoryRoznejDlugosciException extends Exception{
    public final int firstValue, secondValue;

    public WektoryRoznejDlugosciException(int firstValue, int secondValue) {
        super("Inputs has different length");
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }
}
