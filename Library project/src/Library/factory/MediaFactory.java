package Library.factory;
import Library.model.*;

public class MediaFactory {
    public static Media createMedia(String type, String title, String genre, String extra) {
        switch (type.toLowerCase()) {
            case "book":
                return new Book(title, genre, extra);
            case "movie":
                return new Movie(title, genre, extra);
            case "music":
                return new Music(title, genre, extra);
            default:
                throw new IllegalArgumentException("Unknown media type: " + type);
        }
    }
}
