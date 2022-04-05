package entity;

public class AuthorBook extends Entity {
    private int id;
    private String name;

    public AuthorBook() {

    }

    public AuthorBook(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "AuthorBook{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
