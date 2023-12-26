import java.util.List;

public class Order {

    private long idOrder;

    private long idRestaurant;

    private long amount;

    private List<Product> products;

    public Order(){}

    public Order(long idOrder, long idRestaurant, List<Product> products) {
        this.idOrder = idOrder;
        this.idRestaurant = idRestaurant;
        this.products = products;
        this.amount = getA();
    }

    private long getA(){
        long sum = 0;
        for (Product product : products){
            sum += product.getCost();
        }
        return sum;
    }

    public long getAmount() {
        return amount;
    }

    public long getIdOrder() {
        return idOrder;
    }

    public long getIdRestaurant() {
        return idRestaurant;
    }

    public List<Product> getProducts() {
        return products;
    }
}
