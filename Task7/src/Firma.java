public class Firma extends Wpis {
    private String nazwa = "";
    private String adres = "";

    public Firma(String name, String adress) {
        this.nazwa = name;
        this.adres = adress;
    }

    @Override
    public String opis() {
        StringBuilder sb = new StringBuilder();
        sb.append("Firma: ").append(nazwa).append(" w ").append(adres).append('.').append(' ');
        return sb.toString();
    }
}