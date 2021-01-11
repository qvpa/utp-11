package zad1.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private final String url;
    private final TravelData travelData;


    public Database(String url, TravelData travelData) {
        this.url = url;
        this.travelData = travelData;
    }

    public void create() {
        try (Connection connection = getConnection()) {

        } catch (SQLException exc) {
            exc.printStackTrace();
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url);
    }

    public void showGui() {
    }

    //test
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:sqlite:E:/Downloads/Travels.db";

        try(Connection connection = DriverManager.getConnection(url)) {
            System.out.println("It works");
        }
    }
}
