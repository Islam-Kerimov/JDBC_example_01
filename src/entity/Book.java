package entity;

public class Book {
    private int id;
    private String name;
    private int year;
    private int amount;

    public Book(int id, String name, int year, int amount) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", year=" + year +
                ", amount=" + amount +
                '}';
    }
}