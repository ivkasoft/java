public class Book {
    int id;
    int ISBN;
    String title;
    String author;
    boolean available;
    String library;

    public Book(int id, int ISBN, String title, String author, String library,boolean available) {
        this.id = id;
        this.ISBN = ISBN;
        this.title = title;
        this.author = author;
        this.library = library;
        this.available = available;

    }

    public int getId() {
        return id;
    }

    public int getISBN() {
        return ISBN;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean getAvailable() {
        return available;
    }

    public String getLibrary() {
        return library;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
