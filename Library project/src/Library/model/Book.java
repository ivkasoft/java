package Library.model;

public class Book extends Media{
    private String author;

    public Book(String title, String genre, String author) {
        super(title, genre);
        this.author = author;
    }

    @Override
    public String getInfo() {
        return "📚 Book: " + title + " (" + genre + ") by " + author + " | Rating: " + rating;
    }
}
