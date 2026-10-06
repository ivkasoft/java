package Library.adapter;
import Library.model.Media;

public class RatingAdapter {
    private ExternalRatingSystem system = new ExternalRatingSystem();

    public void setRating(Media media, int rating) {
        double converted = system.convertToFiveStarScale(rating);
        media.setRating(converted);
        System.out.println("Converted " + rating + "/10 to " + converted + " stars.");
    }
}
