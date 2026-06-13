package rubrica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Gestisce la connessione al database SQLite e crea le tabelle al primo avvio.
 */
public final class Database {
    private static final String URL = "jdbc:sqlite:rubrica.db";

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException error) {
            throw new IllegalStateException(
                "Driver SQLite non trovato. Avvia il progetto con Maven.",
                error
            );
        }
    }

    private Database() {
        // Classe di utilita: non deve essere istanziata.
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public static void initialize() {
        String creaTabellaPersone = """
            CREATE TABLE IF NOT EXISTS persone (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                cognome TEXT NOT NULL,
                nome TEXT NOT NULL,
                mail TEXT,
                indirizzo TEXT,
                telefono TEXT NOT NULL
            )
            """;

        String creaTabellaAziende = """
            CREATE TABLE IF NOT EXISTS aziende (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                ragione_sociale TEXT NOT NULL,
                descrizione TEXT,
                mail TEXT,
                indirizzo TEXT,
                telefono TEXT NOT NULL
            )
            """;

        try (
            Connection connection = getConnection();
            Statement statement = connection.createStatement()
        ) {
            statement.execute(creaTabellaPersone);
            statement.execute(creaTabellaAziende);
            System.out.println("Database inizializzato correttamente.");
        } catch (SQLException error) {
            throw new IllegalStateException(
                "Errore durante l'inizializzazione del database: " + error.getMessage(),
                error
            );
        }
    }
}
