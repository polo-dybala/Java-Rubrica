package rubrica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Contiene le operazioni CRUD della rubrica.
 * La struttura riprende il progetto iniziale, ma usa SQLite invece degli ArrayList.
 */
public class funzione {
    private final Scanner obj;

    public funzione(Scanner obj) {
        this.obj = obj;
    }

    public void addperson() {
        System.out.println("Inserisci il cognome");
        String cognome = obj.nextLine();
        System.out.println("Inserisci il nome");
        String nome = obj.nextLine();
        System.out.println("Inserisci la mail");
        String mail = obj.nextLine();
        System.out.println("Inserisci l'indirizzo");
        String indirizzo = obj.nextLine();
        System.out.println("Inserisci il numero di telefono");
        String num = obj.nextLine();

        String sql = """
            INSERT INTO persone (cognome, nome, mail, indirizzo, telefono)
            VALUES (?, ?, ?, ?, ?)
            """;

        try (
            Connection connection = Database.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, cognome);
            statement.setString(2, nome);
            statement.setString(3, mail);
            statement.setString(4, indirizzo);
            statement.setString(5, num);
            statement.executeUpdate();
            System.out.println("Persona aggiunta correttamente.");
        } catch (SQLException error) {
            stampaErroreDatabase(error);
        }
    }

    public void addaziende() {
        System.out.println("Inserisci la ragione sociale");
        String ragione = obj.nextLine();
        System.out.println("Inserisci la descrizione");
        String desc = obj.nextLine();
        System.out.println("Inserisci la mail");
        String mail = obj.nextLine();
        System.out.println("Inserisci l'indirizzo");
        String indirizzo = obj.nextLine();
        System.out.println("Inserisci il numero di telefono");
        String num = obj.nextLine();

        String sql = """
            INSERT INTO aziende (ragione_sociale, descrizione, mail, indirizzo, telefono)
            VALUES (?, ?, ?, ?, ?)
            """;

        try (
            Connection connection = Database.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, ragione);
            statement.setString(2, desc);
            statement.setString(3, mail);
            statement.setString(4, indirizzo);
            statement.setString(5, num);
            statement.executeUpdate();
            System.out.println("Azienda aggiunta correttamente.");
        } catch (SQLException error) {
            stampaErroreDatabase(error);
        }
    }

    public void searchperson() {
        System.out.print("Inserisci il cognome: ");
        String cognome = obj.nextLine();

        String sql = """
            SELECT cognome, nome, mail, indirizzo, telefono
            FROM persone
            WHERE cognome = ? COLLATE NOCASE
            ORDER BY nome
            """;

        cercaPersone(sql, cognome);
    }

    public void searchaziende() {
        System.out.print("Inserisci la ragione sociale: ");
        String ragione = obj.nextLine();

        String sql = """
            SELECT ragione_sociale, descrizione, mail, indirizzo, telefono
            FROM aziende
            WHERE ragione_sociale = ? COLLATE NOCASE
            ORDER BY ragione_sociale
            """;

        try (
            Connection connection = Database.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, ragione);

            try (ResultSet resultSet = statement.executeQuery()) {
                boolean found = false;
                while (resultSet.next()) {
                    found = true;
                    creaAzienda(resultSet).getazienda();
                    System.out.println("--------------------");
                }
                if (!found) {
                    System.out.println("Nessun risultato trovato");
                }
            }
        } catch (SQLException error) {
            stampaErroreDatabase(error);
        }
    }

    public void searchperson1() {
        System.out.print("Inserisci il nome: ");
        String nome = obj.nextLine();

        String sql = """
            SELECT cognome, nome, mail, indirizzo, telefono
            FROM persone
            WHERE nome = ? COLLATE NOCASE
            ORDER BY cognome
            """;

        cercaPersone(sql, nome);
    }

    public int modificare_person() {
        System.out.print("Inserisci il cognome da modificare: ");
        String vecchioCognome = obj.nextLine();
        System.out.print("Inserisci il nome da modificare: ");
        String vecchioNome = obj.nextLine();

        Integer id = trovaIdPersona(vecchioCognome, vecchioNome);
        if (id == null) {
            System.out.println("Nessun risultato trovato");
            return 0;
        }

        System.out.println("Inserisci il nuovo cognome");
        String cognome = obj.nextLine();
        System.out.println("Inserisci il nuovo nome");
        String nome = obj.nextLine();
        System.out.println("Inserisci la nuova mail");
        String mail = obj.nextLine();
        System.out.println("Inserisci il nuovo indirizzo");
        String indirizzo = obj.nextLine();
        System.out.println("Inserisci il nuovo numero di telefono");
        String num = obj.nextLine();

        String sql = """
            UPDATE persone
            SET cognome = ?, nome = ?, mail = ?, indirizzo = ?, telefono = ?
            WHERE id = ?
            """;

        try (
            Connection connection = Database.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, cognome);
            statement.setString(2, nome);
            statement.setString(3, mail);
            statement.setString(4, indirizzo);
            statement.setString(5, num);
            statement.setInt(6, id);
            return statement.executeUpdate() == 1 ? 1 : 0;
        } catch (SQLException error) {
            stampaErroreDatabase(error);
            return 0;
        }
    }

    public int modificare_aziende() {
        System.out.print("Inserisci la ragione sociale da modificare: ");
        String vecchiaRagione = obj.nextLine();
        System.out.print("Inserisci la descrizione da modificare: ");
        String vecchiaDescrizione = obj.nextLine();

        Integer id = trovaIdAzienda(vecchiaRagione, vecchiaDescrizione);
        if (id == null) {
            System.out.println("Nessun risultato trovato");
            return 0;
        }

        System.out.println("Inserisci la nuova ragione sociale");
        String ragione = obj.nextLine();
        System.out.println("Inserisci la nuova descrizione");
        String desc = obj.nextLine();
        System.out.println("Inserisci la nuova mail");
        String mail = obj.nextLine();
        System.out.println("Inserisci il nuovo indirizzo");
        String indirizzo = obj.nextLine();
        System.out.println("Inserisci il nuovo numero di telefono");
        String num = obj.nextLine();

        String sql = """
            UPDATE aziende
            SET ragione_sociale = ?, descrizione = ?, mail = ?, indirizzo = ?, telefono = ?
            WHERE id = ?
            """;

        try (
            Connection connection = Database.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, ragione);
            statement.setString(2, desc);
            statement.setString(3, mail);
            statement.setString(4, indirizzo);
            statement.setString(5, num);
            statement.setInt(6, id);
            return statement.executeUpdate() == 1 ? 1 : 0;
        } catch (SQLException error) {
            stampaErroreDatabase(error);
            return 0;
        }
    }

    public int elim_persone() {
        System.out.print("Inserisci il cognome: ");
        String cognome = obj.nextLine();
        System.out.print("Inserisci il nome: ");
        String nome = obj.nextLine();

        Integer id = trovaIdPersona(cognome, nome);
        if (id == null) {
            System.out.println("Nessun risultato trovato");
            return 0;
        }

        return eliminaPerId("DELETE FROM persone WHERE id = ?", id);
    }

    public int elim_aziende() {
        System.out.print("Inserisci la ragione sociale: ");
        String ragione = obj.nextLine();
        System.out.print("Inserisci la descrizione: ");
        String descrizione = obj.nextLine();

        Integer id = trovaIdAzienda(ragione, descrizione);
        if (id == null) {
            System.out.println("Nessun risultato trovato");
            return 0;
        }

        return eliminaPerId("DELETE FROM aziende WHERE id = ?", id);
    }

    private void cercaPersone(String sql, String valore) {
        try (
            Connection connection = Database.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, valore);

            try (ResultSet resultSet = statement.executeQuery()) {
                boolean found = false;
                while (resultSet.next()) {
                    found = true;
                    creaPersona(resultSet).getPerson();
                    System.out.println("--------------------");
                }
                if (!found) {
                    System.out.println("Nessun risultato trovato");
                }
            }
        } catch (SQLException error) {
            stampaErroreDatabase(error);
        }
    }

    private Integer trovaIdPersona(String cognome, String nome) {
        String sql = """
            SELECT id, cognome, nome, mail, indirizzo, telefono
            FROM persone
            WHERE cognome = ? COLLATE NOCASE
              AND nome = ? COLLATE NOCASE
            ORDER BY id
            LIMIT 1
            """;

        try (
            Connection connection = Database.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, cognome);
            statement.setString(2, nome);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    creaPersona(resultSet).getPerson();
                    return resultSet.getInt("id");
                }
            }
        } catch (SQLException error) {
            stampaErroreDatabase(error);
        }

        return null;
    }

    private Integer trovaIdAzienda(String ragione, String descrizione) {
        String sql = """
            SELECT id, ragione_sociale, descrizione, mail, indirizzo, telefono
            FROM aziende
            WHERE ragione_sociale = ? COLLATE NOCASE
              AND descrizione = ? COLLATE NOCASE
            ORDER BY id
            LIMIT 1
            """;

        try (
            Connection connection = Database.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, ragione);
            statement.setString(2, descrizione);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    creaAzienda(resultSet).getazienda();
                    return resultSet.getInt("id");
                }
            }
        } catch (SQLException error) {
            stampaErroreDatabase(error);
        }

        return null;
    }

    private int eliminaPerId(String sql, int id) {
        try (
            Connection connection = Database.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setInt(1, id);
            return statement.executeUpdate() == 1 ? 1 : 0;
        } catch (SQLException error) {
            stampaErroreDatabase(error);
            return 0;
        }
    }

    private Persone creaPersona(ResultSet resultSet) throws SQLException {
        return new Persone(
            resultSet.getString("cognome"),
            resultSet.getString("nome"),
            resultSet.getString("mail"),
            resultSet.getString("indirizzo"),
            resultSet.getString("telefono")
        );
    }

    private aziende creaAzienda(ResultSet resultSet) throws SQLException {
        return new aziende(
            resultSet.getString("ragione_sociale"),
            resultSet.getString("descrizione"),
            resultSet.getString("mail"),
            resultSet.getString("indirizzo"),
            resultSet.getString("telefono")
        );
    }

    private void stampaErroreDatabase(SQLException error) {
        System.out.println("Errore database: " + error.getMessage());
    }
}
