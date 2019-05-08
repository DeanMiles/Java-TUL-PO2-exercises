class Stoper {
    private long start;// czas stopu stopera
    private long stop;// nazwa stopera
    private String nazwa;

    public Stoper() {
        this("");
    }

    public Stoper(String nazwa) {               // przypisujemy do pola nazwa przekazany łańcuch tekstowy
        this.nazwa = nazwa;
    }
// metoda uruchamiana przy starcie stopera

    public void start(){
        start = System.currentTimeMillis();
    }

    public void stop(){
        stop = System.currentTimeMillis();
    }

    public double pobierzWynik(){
        return (stop - start) / 1000.0;
    }

    public String toString(){
        return nazwa + ": " + this.pobierzWynik() + " s.";
    }
}