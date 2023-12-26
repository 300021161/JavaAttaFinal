import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class insertData {

    private static final String URL = "jdbc:postgresql://localhost:5432/newdatabases";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    public static void main(String[] args) {
        List<Restaurant> restaurantDataList = new ReadFromJson().deserialize("player.json");

        createTables();

        for (Restaurant restaurantData : restaurantDataList) {
            insertDataIntoRestaurants(restaurantData);

            for (Order orderData : restaurantData.getOrders()) {
                insertDataIntoOrders(orderData);

                for (Product productData : orderData.getProducts()) {
                    insertDataIntoProducts(productData);
                }
            }
        }

        retrieveData();
    }

    private static void createTables() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS public.\"Restaurants\"(" +
                            "\"id\" serial PRIMARY KEY," +
                            "\"name\" VARCHAR(255) NOT NULL)"
            )) {
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }


            // Create Orders table
            try (PreparedStatement statement = connection.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS public.\"Orders\"(" +
                            "\"id\" serial PRIMARY KEY," +
                            "\"amount\" bigint," +
                            "\"idRestaurant\" bigint," +
                            "FOREIGN KEY (\"idRestaurant\") REFERENCES public.\"Restaurants\"(\"id\"))"
            )) {
                statement.executeUpdate();
            }

            // Create Products table
            try (PreparedStatement statement = connection.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS public.\"Products\"(" +
                            "\"id\" serial PRIMARY KEY," +
                            "\"cost\" bigint," +
                            "\"idOrder\" bigint," +
                            "FOREIGN KEY (\"idOrder\") REFERENCES public.\"Orders\"(\"id\"))"
            )) {
                statement.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertDataIntoRestaurants(Restaurant restaurantData) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO public.\"Restaurants\"(\"name\") VALUES (?)"
             )) {
            statement.setString(1, restaurantData.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private static void insertDataIntoOrders(Order orderData) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO public.\"Orders\" (\"amount\", \"idRestaurant\") VALUES (?, ?)"
             )) {
            statement.setLong(1, orderData.getAmount());
            statement.setLong(2, orderData.getIdRestaurant());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertDataIntoProducts(Product productData) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO public.\"Products\" (\"cost\", \"idOrder\") VALUES (?, ?)"
             )) {
            statement.setLong(1, productData.getCost());
            statement.setLong(2, productData.getIdOrder());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void retrieveData() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM public.\"Orders\""
            )) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        int orderId = resultSet.getInt("id");
                        long amount = resultSet.getLong("amount");
                        long idRestaurant = resultSet.getLong("idRestaurant");

                        System.out.println("Order ID: " + orderId +
                                ", Amount: " + amount +
                                ", Restaurant ID: " + idRestaurant);
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
