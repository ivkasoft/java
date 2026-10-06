package Library.model;

public abstract class Media {
    protected String title;
    protected String genre;
    protected double rating;

    public Media(String title, String genre) {
        this.title = title;
        this.genre = genre;
    }

    public String getTitle() { return title; }
    public String getGenre() { return genre; }
    public double getRating() { return rating; }

    public void setRating(double rating) { this.rating = rating; }

    public abstract String getInfo();
}
