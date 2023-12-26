import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {

    private long cost;

    private long idProduct;

    private long idOrder;

    public Product(){}

    public Product( long cost, long idOrder, long idProduct) {
        this.cost = cost;
        this.idOrder = idOrder;
        this.idProduct = idProduct;
    }

    public long getCost() {
        return cost;
    }

    public long getIdProduct() {
        return idProduct;
    }

    public long getIdOrder() {
        return idOrder;
    }
}
