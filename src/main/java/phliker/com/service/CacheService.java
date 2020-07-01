package phliker.com.service;

import javafx.scene.image.Image;
import phliker.com.model.FlickrResponse;
import phliker.com.model.Photo;
import java.util.HashMap;
import java.util.Map;

public class CacheService implements Service {

    private Service flickrService;
    private Map<String,Image>imageMap;

    public CacheService() {
        flickrService = new FlickrService();
        imageMap = new HashMap<>();
    }

    // don't change this method
    @Override
    public FlickrResponse searchPhoto(String tags) {
        return flickrService.searchPhoto(tags);
    }

    @Override
    public Image getImage(Photo photo) {
        if (imageMap.containsKey(photo.getId())){
            return imageMap.get(photo.getId());
        }
        Image img = flickrService.getImage(photo);
        imageMap.put(photo.getId(),img);
        return img;
    }
}