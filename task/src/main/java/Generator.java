import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generator {

    public Restaurant[] generateRestaurants(int numRestaurants) {
        Restaurant[] restaurants = new Restaurant[numRestaurants];

        System.out.println("__________");
        for (int i = 0; i < numRestaurants; i++) {
            long restaurantId = (long) i + 1;
            List<Order> orders = generateOrders(restaurantId);
            Restaurant restaurant = new Restaurant(restaurantId, orders);
            restaurants[i] = restaurant;
        }

        return restaurants;
    }

    private List<Order> generateOrders(long idRestaurant) {
        List<Order> orders = new ArrayList<>();
        Random random = new Random();

        int numOrders = random.nextInt(100) + 1;

        for (int i = 0; i < numOrders; i++) {
            long orderId = (long) i + 1;
            List<Product> products = generateProducts(orderId);

            Order order = new Order(orderId, idRestaurant, products);
            orders.add(order);
        }

        return orders;
    }

    private List<Product> generateProducts(long orderId) {
        List<Product> products = new ArrayList<>();
        Random random = new Random();

        int numProducts = random.nextInt(50) + 1;

        for (int i = 0; i < numProducts; i++) {
            long productId = (long) i + 1;
            long cost = random.nextLong(10000) + 1;

            Product product = new Product(cost, orderId, productId);
            products.add(product);
        }

        return products;
    }
}
