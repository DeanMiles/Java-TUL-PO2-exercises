import java.util.Random;

public class NrTelefoniczny implements Comparable<NrTelefoniczny> {
    public final String nrKierunkowy, nrTelefonu;

    NrTelefoniczny() {
        final Integer numerTelefon;
        final Integer numerKier;
        String[] randTel = {"781041477", "775345123", "874364213", "691182483", "665277251", "507145879",
                "692753789", "572842660", "518842281", "721393277", "519865480", "512166469", "506912200",
                "648893123", "555983421"};
        String[] randKierunkowy = {"48", "44", "22", "56", "34", "66", "45", "34", "36", "38"};
        numerTelefon = new Random().nextInt(randTel.length);
        numerKier = new Random().nextInt(randKierunkowy.length);
        String numerT = (randTel[numerTelefon]);
        String numerK = (randKierunkowy[numerKier]);
        nrKierunkowy = numerK;
        nrTelefonu = numerT;
    }
    public String opis() {
        StringBuilder sb = new StringBuilder();
        sb.append("Tel: ").append(nrKierunkowy).append(" ").append(nrTelefonu);
        return sb.toString();
    }

    //@Override
    public int compareTo(NrTelefoniczny id) {
        if (this.nrKierunkowy.compareTo(id.nrKierunkowy) == 0)
            return this.nrTelefonu.compareTo(id.nrTelefonu);
        else return this.nrKierunkowy.compareTo(id.nrKierunkowy);
    }
}