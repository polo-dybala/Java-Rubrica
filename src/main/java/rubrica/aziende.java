package rubrica;

/**
 * Rappresenta un'azienda salvata nella rubrica.
 * Il nome della classe e mantenuto uguale al progetto iniziale.
 */
public class aziende {
    private final String ragione;
    private final String mail;
    private final String indirizzo;
    private final String desc;
    private final String numero;

    public aziende(String ragione, String desc, String mail, String indirizzo, String numero) {
        this.ragione = ragione;
        this.desc = desc;
        this.mail = mail;
        this.indirizzo = indirizzo;
        this.numero = numero;
    }

    public void getazienda() {
        System.out.println("Ragione Sociale: " + ragione);
        System.out.println("Descrizione: " + desc);
        System.out.println("Indirizzo: " + indirizzo);
        System.out.println("Mail: " + mail);
        System.out.println("Telefono: " + numero);
    }

    public String rag() {
        return ragione;
    }

    public String dec() {
        return desc;
    }
}
