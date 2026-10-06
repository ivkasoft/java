package Library.model;

public class Movie extends Media{
    private String director;

    public Movie(String title, String genre, String director) {
        super(title, genre);
        this.director = director;
    }

    @Override
    public String getInfo() {
        return "🎬 Movie: " + title + " (" + genre + ") by " + director + " | Rating: " + rating;
    }
}
