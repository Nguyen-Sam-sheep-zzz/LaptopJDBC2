import conrnect.ConnectDB;

import java.sql.*;

public class Main {
    public static void main(String[] args) {

        ConnectDB connectDB = new ConnectDB();
        connectDB.connectionDB();
    }

    public void CreateLaptop(String name, String RAM, String chipset, double price, int stock) {
        ConnectDB connectDB = new ConnectDB();
        Connection connection = connectDB.connectionDB();
        PreparedStatement preparedStatement;
        String insertLaptop = "insert into Laptop (name,RAM,SSD,chipset,price,stock) values (?,?,?,?,?,?)";

        try {
            preparedStatement = connection.prepareStatement(insertLaptop);

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, RAM);
            preparedStatement.setString(3, chipset);

            preparedStatement.setDouble(4, price);
            preparedStatement.setInt(5, stock);

            int row = preparedStatement.executeUpdate();
            if (row != 0) {
                System.out.println("Add laptop succsessfully");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    public void SelectFullProductLaptop() {
        ConnectDB connectDB = new ConnectDB();
        Connection connection = connectDB.connectionDB();
        Statement statement = null;
        String selecyAllProduct = "select * from products";

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selecyAllProduct);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String RAM = resultSet.getString("RAM");
                String SSD = resultSet.getString("SSD");
                String chipset = resultSet.getString("chipset");
                double price = resultSet.getDouble("price");
                int stock = resultSet.getInt("stock");

                System.out.println("| id:" + id + " | name:" + name + " | RAM:" + RAM + " | SSD:" + SSD + " | chipset:" + chipset + " | price:" + price + " | stock:" + stock + " |");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void deleteLaptop(int id) {
        ConnectDB connectDB = new ConnectDB();
        Connection connection = connectDB.connectionDB();
        PreparedStatement preparedStatement;
        String insertLaptop = "delete from laptop where id = ?";

        try {
            preparedStatement = connection.prepareStatement(insertLaptop);

            preparedStatement.setInt(1, id);

            int row = preparedStatement.executeUpdate();
            if (row != 0) {
                System.out.println("deleted laptop succsessfully");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

