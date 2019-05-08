public class Osoba extends Wpis {

    private String imie = "";
    private String nazwisko = "";
    private String adres = "";

    public Osoba(String name, String surname, String adress){
        //super(new NrTelefoniczny());
        this.imie = name;
        this.nazwisko = surname;
        this.adres  = adress;
    }

    @Override
    public String opis() {
        StringBuilder sb = new StringBuilder();
        sb.append("Osoba: ").append(imie).append(" ").append(nazwisko).append(' ').append(adres).append(' ');
        return sb.toString();
    }
}
