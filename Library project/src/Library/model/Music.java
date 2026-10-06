package Library.model;

public class Music extends Media{
    private String artist;
    public Music(String title, String ganre, String artist){
        super(title,ganre);
        this.artist=artist;
    }

    @Override
    public String getInfo(){
        return "🎵 Music: " + title + " (" + genre + ") by " + artist + " | Rating: " + rating;
    }
}
