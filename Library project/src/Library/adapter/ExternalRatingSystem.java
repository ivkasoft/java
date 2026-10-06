package Library.adapter;

public class ExternalRatingSystem {
    public double convertToFiveStarScale(int rating) {
        return Math.round((rating / 2.0) * 10.0) / 10.0;
    }
}
