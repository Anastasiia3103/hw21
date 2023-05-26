package hw21;

import java.time.LocalDate;

class Product {
    private String type;
    private double price;
    private boolean discountAvailable;
    private LocalDate addedDate;
    public Product(String type, double price, boolean discountAvailable, LocalDate addedDate ) {
        this.type = type;
        this.addedDate = addedDate;
        this.price = price;
        this.discountAvailable = discountAvailable;
    }

    public double getPrice () {
        return price;
    }

    public String getType () {
        return type;
    }

    public void setPrice (double price) {
        this.price = price;
    }

    public void setType (String type) {
        this.type = type;
    }

    public boolean isDiscountAvailable () {
        return discountAvailable;
    }

    public LocalDate getAddedDate () {
        return addedDate;
    }
    @Override
    public String toString() {
        return "Product{" +
                "type='" + type + '\'' +
                ", price=" + price +
                ", discountAvailable=" + discountAvailable +
                ", addedDate=" + addedDate +
                '}';
    }
}


