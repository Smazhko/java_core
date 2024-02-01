package web_shop;

public class Order {
    private Integer id;
    private Client client;
    private Product product;
    private Integer productCount;
    private String status;
    private static Integer newID = 500;

    public Order(Client client, Product product, Integer productCount, String status) {
        this.id = newID++;
        this.client = client;
        this.product = product;
        this.productCount = productCount;
        this.status = status;
    }

    public double getTotalCost(){
        return product.getPrice() * productCount;
    }

    @Override
    public String toString() {
        return "Заказ: id: " +
                id + ", " +
                client.toString() + " || " +
                product.toString() + ", заказано " +
                productCount +
                " шт., ВСЕГО " + getTotalCost() + "р. || статус - " + status;
    }
}
