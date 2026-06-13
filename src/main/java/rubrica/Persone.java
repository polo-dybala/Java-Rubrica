package rubrica;

/**
 * Rappresenta una persona salvata nella rubrica.
 * Il nome della classe e mantenuto uguale al progetto iniziale.
 */
public class Persone {
    private final String cognome;
    private final String nome;
    private final String mail;
    private final String indirizzo;
    private final String numero;

    public Persone(String cognome, String nome, String mail, String indirizzo, String numero) {
        this.cognome = cognome;
        this.nome = nome;
        this.mail = mail;
        this.indirizzo = indirizzo;
        this.numero = numero;
    }

    public void getPerson() {
        System.out.println("Cognome: " + cognome);
        System.out.println("Nome: " + nome);
        System.out.println("Mail: " + mail);
        System.out.println("Telefono: " + numero);
        System.out.println("Indirizzo: " + indirizzo);
    }

    public String cogn() {
        return cognome;
    }

    public String Nome() {
        return nome;
    }
}
