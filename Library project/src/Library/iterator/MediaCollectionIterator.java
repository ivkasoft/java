package Library.iterator;
import java.util.List;
import Library.model.Media;

public class MediaCollectionIterator implements MediaIterator{
    private List<Media> mediaList;
    private int position = 0;

    public MediaCollectionIterator(List<Media> mediaList) {
        this.mediaList = mediaList;
    }

    @Override
    public boolean hasNext() {
        return position < mediaList.size();
    }

    @Override
    public Media next() {
        return mediaList.get(position++);
    }
}
