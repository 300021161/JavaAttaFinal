import java.util.List;

public class Restaurant {
    private long id;

    private String name = "";

    public List<Order> orders;

    public Restaurant() {}

    public Restaurant(long id, List<Order> orders) {
        this.orders = orders;
        this.id = id;
    }

    public Restaurant(long id, List<Order> orders, String name) {
        this.orders = orders;
        this.id = id;
        this.name = name;
    }


    public List<Order> getOrders() {
        return orders;
    }

    public long getId() {return id;}

    public String getName() {return name;}
}