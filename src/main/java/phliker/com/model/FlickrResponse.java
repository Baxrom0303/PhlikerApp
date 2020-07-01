package phliker.com.model;

/**
 * Created by nsarvar on 2/16/18.
 */
public class FlickrResponse {
    /*
        modify this class. Think about how it might look. It is depend on JSON response structure
        */

    //    private Photos photos;
    private Photos photos;

    private String stat;

    public Photos getPhotos() {
        return photos;
    }

    public void setPhotos(Photos photos) {
        this.photos = photos;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

}