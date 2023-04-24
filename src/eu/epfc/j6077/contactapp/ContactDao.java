package eu.epfc.j6077.contactapp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactDao {
    private static final String CONNECTION_STRING = "jdbc:h2:./contact";

    public static void add(Contact contact) {
        try (Connection connection = DriverManager.getConnection(CONNECTION_STRING)) {
            String sql = "insert into CONTACT (FIRSTNAME, LASTNAME, EMAIL, PHONE) VALUES (?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, contact.getFirstName());
            preparedStatement.setString(2, contact.getLastName());
            preparedStatement.setString(3, contact.getEmail());
            preparedStatement.setString(4, contact.getPhone());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void update(Contact contact) {
        try (Connection connection = DriverManager.getConnection(CONNECTION_STRING)) {
            String sql = "update CONTACT set FIRSTNAME=?, LASTNAME=?, EMAIL=?, PHONE=? where ID=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, contact.getFirstName());
            preparedStatement.setString(2, contact.getLastName());
            preparedStatement.setString(3, contact.getEmail());
            preparedStatement.setString(4, contact.getPhone());
            preparedStatement.setInt(5, contact.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void delete(int id) {
        try (Connection connection = DriverManager.getConnection(CONNECTION_STRING)) {
            String sql = "delete CONTACT where ID=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static List<Contact> fetch() {
        List<Contact> contacts = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(CONNECTION_STRING)){
            String sql = "select ID, FIRSTNAME, LASTNAME, EMAIL, PHONE from CONTACT";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String firstName = resultSet.getString("FIRSTNAME");
                String lastName = resultSet.getString("LASTNAME");
                String email = resultSet.getString("EMAIL");
                String phone = resultSet.getString("PHONE");
                Contact contact = new Contact(id, firstName, lastName, email, phone);
                contacts.add(contact);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return contacts;
    }

    static void initialize() {
        String sql = "create table if not exists CONTACT (" +
                "  ID integer primary key auto_increment," +
                "  FIRSTNAME varchar(32) not null," +
                "  LASTNAME varchar(32) not null," +
                "  EMAIL varchar(128)," +
                "  PHONE varchar(20)" +
                ")";
        try {
            Connection connection = DriverManager.getConnection(CONNECTION_STRING);
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
