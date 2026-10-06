package Library.library;

import java.util.*;
import Library.model.Media;
import Library.iterator.*;

public class MediaLibrary{
    private List<Media> mediaList=new ArrayList<>();

    public void addMedia(Media media) { mediaList.add(media); }
    public void removeMedia(String title) { mediaList.removeIf(m -> m.getTitle().equalsIgnoreCase(title)); }

    public List<Media> getAll() { return mediaList; }

    public List<Media> filterByGenre(String genre) {
        List<Media> result=new ArrayList<>();
        for(Media m : mediaList) {
            if(m.getGenre().equalsIgnoreCase(genre)) {
                result.add(m);
            }
        }
        return result;
    }

    public MediaIterator iterator() { return new MediaCollectionIterator(mediaList); }
}
