package phliker.com.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import phliker.com.model.Photo;
import phliker.com.model.Photos;
import phliker.com.service.CacheService;
import phliker.com.utils.AppProperties;

import java.util.List;

/**
 *
 */
public class PhotoController {
    // view elements
    @FXML
    TextField searchField;
    @FXML
    ImageView imageView;
    @FXML
    ImageView loaderImageView;
    @FXML
    Button prevButton;
    @FXML
    Button nextButton;
    @FXML
    Label title;
    @FXML
    Label counter;

    private CacheService cacheService;
    private boolean debugging;
    private Photos photos;
    private int count;


    public PhotoController() {
        debugging = AppProperties.getBool("debug");
        cacheService = new CacheService();

        if (debugging) {
            System.out.println("[debug] PhotoController: constructor");
        }

    }

    @FXML
    private void searchImage(ActionEvent event) {
        String tags = searchField.getText().trim();
        System.out.println(tags);
        if (tags.isEmpty()){
            System.out.println("Search field is empty");
            title.setText("Please fill out the field!");
            return;
        }
        System.out.println(" search button clicked ");
        nextButton.setVisible(false);
        prevButton.setVisible(false);
        count=0;
        photos = cacheService.searchPhoto(tags).getPhotos();
        if (photos.getPhoto().size() > 1){
            nextButton.setVisible(true);
        }
        if (photos.getPhoto().size()==0)
            return;
        setAttribute();
        prevButton.setDisable(false);
        nextButton.setDisable(false);
    }

    @FXML
    public void nextImage(ActionEvent event) {
        System.out.println(" next button clicked ");
        count++;
        if (count != photos.getPhoto().size()){
            setAttribute();
        }
        if (!prevButton.isVisible()){
            prevButton.setVisible(true);
        }
    }

    @FXML
    /**
     *
     */
    public void prevImage(ActionEvent event) {
        System.out.println(" prev button clicked ");
        count--;
        if (count >= 0){
            setAttribute();
        }
        if (!nextButton.isVisible()){
            nextButton.setVisible(true);
        }
    }

    private void setAttribute(){
        Image img = cacheService.getImage(photos.getPhoto().get(count));
        imageView.setImage(img);
        title.setText(photos.getPhoto().get(count).getTitle());
        counter.setText(count + 1 + "/" + photos.getPhoto().size());
        if (count == 0){
            prevButton.setVisible(false);
        }else if (count == photos.getPhoto().size()-1){
            nextButton.setVisible(false);
        }

    }
}