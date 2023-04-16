package eu.epfc.j6077.contactapp;

import java.sql.*;
import java.util.Scanner;

public class Bootstrapper {
    public static void main(String[] args) {
        initializeDb();
        Scanner input = new Scanner(System.in);
        String choice;
        do {
            System.out.println("\nChoisissez dans les options suivantes");
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
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nFormulaire de contact");
        System.out.print("Prénom: ");
        String firstName = scanner.nextLine();
        System.out.print("Nom de famille: ");
        String lastName = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Téléphone: ");
        String phone = scanner.nextLine();
        try (Connection connection = DriverManager.getConnection("jdbc:h2:./contact")) {
            String sql = "insert into CONTACT (FIRSTNAME, LASTNAME, EMAIL, PHONE) VALUES (?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, phone);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        scanner.close();
    }

    private static void consultContacts() {
        System.out.println("\nListe des contacts:");
        try (Connection connection = DriverManager.getConnection("jdbc:h2:./contact")){
            String sql = "select ID, FIRSTNAME, LASTNAME, EMAIL, PHONE from CONTACT";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String firstName = resultSet.getString("FIRSTNAME");
                String lastName = resultSet.getString("LASTNAME");
                String email = resultSet.getString("EMAIL");
                String phone = resultSet.getString("PHONE");
                System.out.println(String.join(" - ", "" + id, firstName, lastName, email, phone));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
