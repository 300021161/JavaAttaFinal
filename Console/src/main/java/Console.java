import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Console {

    private static final String URL = "jdbc:postgresql://localhost:5432/newdatabases";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Выберите действие:");
            System.out.println("1. Добавить ресторан");
            System.out.println("2. Добавить заказ");
            System.out.println("3. Добавить продукт");
            System.out.println("4. Удалить продукт");
            System.out.println("5. Заменить заказ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addRestaurant();
                    break;
                case 2:
                    addOrder();
                    break;
                case 3:
                    addProduct();
                    break;
                case 4:
                    deleteProduct();
                    break;
                case 5:
                    updateOrder();
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }

            System.out.println("Хотите продолжить? (y/n)");
            String continueChoice = scanner.nextLine();
            if (!continueChoice.equalsIgnoreCase("y")) {
                break;
            }
        }

        System.out.println("Программа завершена.");
        scanner.close();
    }

    private static void addRestaurant() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите название ресторана:");
        String name = scanner.nextLine();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO public.\"Restaurants\" (\"name\") VALUES (?)"
             )) {
            statement.setString(1, name);
            statement.executeUpdate();
            System.out.println("Ресторан успешно добавлен.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addOrder() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите сумму заказа:");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Введите id ресторана:");
        int idRestaurant = scanner.nextInt();
        scanner.nextLine();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO public.\"Orders\" (\"amount\", \"idRestaurant\") VALUES (?, ?)"
             )) {
            statement.setDouble(1, amount);
            statement.setInt(2, idRestaurant);
            statement.executeUpdate();
            System.out.println("Заказ успешно добавлен.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void addProduct() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите стоимость продукта:");
        double cost = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Введите id заказа:");
        int idOrder = scanner.nextInt();
        scanner.nextLine();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO public.\"Products\" (\"cost\", \"idOrder\") VALUES (?, ?)"
             )) {
            statement.setDouble(1, cost);
            statement.setInt(2, idOrder);
            statement.executeUpdate();
            System.out.println("Продукт успешно добавлен.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void deleteProduct() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите id продукта для удаления:");
        int productId = scanner.nextInt();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(
                     "DELETE FROM public.\"Products\" WHERE \"id\" = ?"
             )) {
            statement.setInt(1, productId);
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Продукт успешно удален.");
            } else {
                System.out.println("Продукт с указанным id не найден.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void updateOrder() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите id заказа для замены:");
        int orderId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Введите новую сумму заказа:");
        double newAmount = scanner.nextDouble();
        scanner.nextLine();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE public.\"Orders\" SET \"amount\" = ? WHERE \"id\" = ?"
             )) {
            statement.setDouble(1, newAmount);
            statement.setInt(2, orderId);
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Заказ успешно заменен.");
            } else {
                System.out.println("Заказ с указанным id не найден.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}