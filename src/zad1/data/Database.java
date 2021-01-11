package zad1.data;

import java.sql.*;

public class Database {
    private final String url;
    private final TravelData travelData;

    private static final String SCHEMA = "Travels(" +
        "locale varchar(20), " +
        "country varchar(20), " +
        "startDate varchar(20), " +
        "endDate varchar(20), " +
        "place varchar(20), " +
        "price varchar(20), " +
        "currency varchar(20))";

    private static final String INSERT_TEMPLATE =
        "INSERT INTO Travels VALUES (?, ?, ?, ?, ?, ?, ?);";


    public Database(String url, TravelData travelData) {
        this.url = url;
        this.travelData = travelData;
    }

    public void create() {
        try (
            Connection conn = getConnection();
            Statement statement = conn.createStatement();
        ) {

            statement.execute("CREATE TABLE " + SCHEMA);

        } catch (SQLException exc) {
            System.err.println(exc.getMessage());
        }

        fill();

        show();
    }

    public void fill() {
        try (
            Connection conn = getConnection();
            PreparedStatement prepared = conn.prepareStatement(INSERT_TEMPLATE)
        ) {

            for (Travel travel : travelData.getTravelData()) {
                int idx = 1;
                for (String value : travel.asList()) {
                    prepared.setString(idx, value);
                    idx++;
                }
                prepared.addBatch();
            }

            prepared.executeBatch();

        } catch (SQLException exc) {
            exc.printStackTrace();
        }

    }

    private void show() {
        try (
            Connection conn = getConnection();
            Statement statement = conn.createStatement()
        ) {

            var rs = statement.executeQuery("SELECT country FROM Travels");

            while (rs.next()) {
                System.out.println(rs.getString("country"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url);
    }

    public void showGui() {

    }

}
