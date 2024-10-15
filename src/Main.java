import conrnect.ConnectDB;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        showLaptopList();
    }

    public static void showLaptopList() {
        ConnectDB connectDB = new ConnectDB();
        String query = "select * from laptop;";
        Connection connection;
        try {
            connection = connectDB.connectionDB();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                System.out.println("Name: " +
                        resultSet.getString(2) + ", RAM: " +
                        resultSet.getString(3) + ", SSD: " +
                        resultSet.getString(4) + ", chipset: " +
                        resultSet.getString(5) + ", price: " +
                        resultSet.getDouble(6) + ", stock: " +
                        resultSet.getInt(7));
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}