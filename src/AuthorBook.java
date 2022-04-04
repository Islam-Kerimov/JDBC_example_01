public class AuthorBook {
    private int id;
    private String name;

    public AuthorBook(int id, String name) {
        this.id = id;
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
