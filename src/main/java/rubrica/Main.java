package rubrica;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Database.initialize();

        Scanner obj = new Scanner(System.in);
        funzione fun = new funzione(obj);
        int choice = 0;

        do {
            System.out.println("********** Welcome to Polo Rubrica **********");
            System.out.println("1. Add Person");
            System.out.println("2. Add Azienda");
            System.out.println("3. Search Person (using cognome)");
            System.out.println("4. Search Azienda");
            System.out.println("5. Search Person (using nome)");
            System.out.println("6. Modify Person");
            System.out.println("7. Modify Azienda");
            System.out.println("8. Delete Person");
            System.out.println("9. Delete Azienda");
            System.out.println("10. Exit");
            System.out.println("Select an option");

            try {
                choice = Integer.parseInt(obj.nextLine());
            } catch (NumberFormatException error) {
                System.out.println("Wrong option: insert a number from 1 to 10.");
                continue;
            }

            switch (choice) {
                case 1:
                    fun.addperson();
                    break;
                case 2:
                    fun.addaziende();
                    break;
                case 3:
                    fun.searchperson();
                    break;
                case 4:
                    fun.searchaziende();
                    break;
                case 5:
                    fun.searchperson1();
                    break;
                case 6:
                    if (fun.modificare_person() == 1) {
                        System.out.println("Modification successful");
                    } else {
                        System.out.println("Modification failed");
                    }
                    break;
                case 7:
                    if (fun.modificare_aziende() == 1) {
                        System.out.println("Modification successful");
                    } else {
                        System.out.println("Modification failed");
                    }
                    break;
                case 8:
                    if (fun.elim_persone() == 1) {
                        System.out.println("Deletion successful");
                    } else {
                        System.out.println("Deletion failed");
                    }
                    break;
                case 9:
                    if (fun.elim_aziende() == 1) {
                        System.out.println("Deletion successful");
                    } else {
                        System.out.println("Deletion failed");
                    }
                    break;
                case 10:
                    System.out.println("Arrivederci.");
                    break;
                default:
                    System.out.println("Wrong option");
            }
        } while (choice != 10);

        obj.close();
    }
}
