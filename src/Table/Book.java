package Table;

import java.util.GregorianCalendar;


public class Book {
    private String name;
    private Author author;
    private Publisher publisher;
    private double price;
    private int qty=0;

    public Book(String name, Author author, Publisher publisher, double price, int qty) {
        this.name = name;
        this.author = author;
        this.publisher = publisher;
        this.price = price;
        this.qty = qty;
    }

    public String getName() {
        return name;
    }

    public Author getAuthor() {
        return author;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public double getPrice() {
        return price;
    }

    public int getQty() {
        return qty;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", author=" + author +
                ", publisher=" + publisher +
                ", price=" + price +
                ", qty=" + qty +
                '}';
    }
}
