package web_shop;

public class Product {
    private Integer id;
    private String name;
    private Double price;
    private Integer count;
    private static Integer newID = 300;

    public Product(String name, Double price, Integer count) {
        this.id = newID++;
        this.name = name;
        this.price = price;
        this.count = count;
    }

    @Override
    public String toString() {
        return "Товар: id " +
                id + ", " +
                name + ", цена " +
                price + "p, кол-во " +
                count + " шт.";
    }

    public Integer getId() {
        return id;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getCount() {
        return count;
    }

    public void decreaseCount(Integer amount){
        if (amount <= count)
            count = count - amount;
    }
}
