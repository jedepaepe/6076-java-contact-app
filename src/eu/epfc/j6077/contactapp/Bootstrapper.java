package eu.epfc.j6077.contactapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Bootstrapper {
    public static void main(String[] args) {
        initializeDb();
        Scanner input = new Scanner(System.in);
        String choice;
        do {
            System.out.println("Choisissez dans les options suivantes");
            System.out.println("(1) lister les contacts");
            System.out.println("(2) ajouter un contact");
            System.out.println("(3) modifier un contact");
            System.out.println("(4) supprimer un contact");
            System.out.println("(Q) quitter l'application");
            choice = input.nextLine();
            switch (choice) {
                case "1" -> consultContacts();
                case "2" -> addContact();
                case "3" -> updateContact();
                case "4" -> deleteContact();
            }
        } while (! choice.equals("Q"));
    }

    private static void deleteContact() {
        System.out.println("debug - delete contact");
    }

    private static void updateContact() {
        System.out.println("debug - update contact");
    }

    private static void addContact() {
        System.out.println("debug - add contact");
    }

    private static void consultContacts() {
        System.out.println("debug - consult contacts");
    }

    private static void initializeDb() {
        String sql = "create table if not exists CONTACT (" +
                "  ID integer primary key auto_increment," +
                "  FIRSTNAME varchar(32) not null," +
                "  LASTNAME varchar(32) not null," +
                "  EMAIL varchar(128)," +
                "  PHONE varchar(20)" +
                ")";
        try {
            Connection connection = DriverManager.getConnection("jdbc:h2:./contact");
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
