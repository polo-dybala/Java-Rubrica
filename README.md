# Java Contact Directory CLI with SQLite

Versione del progetto iniziale **Polo Rubrica** collegata a un database SQLite.
La struttura dei metodi principali e del menu e stata mantenuta il piu possibile simile al codice originale.

## Funzionalita

- aggiunta di persone e aziende;
- ricerca di persone per cognome o nome;
- ricerca di aziende per ragione sociale;
- modifica ed eliminazione di contatti;
- persistenza dei dati in SQLite;
- query parametrizzate con `PreparedStatement`.

## Requisiti

- Java 21
- Maven

## Avvio dal terminale

```bash
mvn clean compile
mvn exec:java
```

Al primo avvio viene creato automaticamente il file locale:

```text
rubrica.db
```

Il file e escluso da Git tramite `.gitignore`, per evitare di pubblicare contatti personali.

## Importazione in Eclipse

1. Apri `File -> Import...`.
2. Seleziona `Maven -> Existing Maven Projects`.
3. Indica la cartella che contiene `pom.xml`.
4. Premi `Finish`.
5. Avvia `rubrica.Main` come `Java Application`.

## Struttura

```text
src/main/java/rubrica/
├── Main.java
├── Database.java
├── funzione.java
├── Persone.java
└── aziende.java
```

## Miglioramenti futuri

Per una versione successiva, conviene rinominare le classi seguendo le convenzioni Java:

- `funzione` -> `GestoreRubrica`
- `Persone` -> `Persona`
- `aziende` -> `Azienda`
