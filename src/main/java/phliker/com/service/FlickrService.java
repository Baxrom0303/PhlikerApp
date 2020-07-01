package phliker.com.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.scene.image.Image;
import phliker.com.model.FlickrResponse;
import phliker.com.model.Photo;
import phliker.com.utils.AppProperties;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;


/**
 * Created by nsarvar on 2/15/18.
 */
public class FlickrService implements Service {

    private boolean debugging;
    private StringBuilder stringBuilder;
    private ObjectMapper mapper;

    FlickrService() {
        debugging = AppProperties.getBool("debug");
        stringBuilder = new StringBuilder();
        mapper = new ObjectMapper();

        if (debugging) {
            System.out.println("[debug] FlickrService: constructor");
        }
    }

    /**
     * searchPhoto method requests api receives response and converts string into POJO
     *
     * @param tags this is the searching element
     * @return FlickerResponse object
     */
    @Override
    public FlickrResponse searchPhoto(String tags) {
        stringBuilder
                .append(AppProperties.getProperty("flicker_api_url"))
                .append("&method=")
                .append(AppProperties.getProperty("search"))
                .append("&api_key=")
                .append(AppProperties.getProperty("api_key"))
                .append("&tags=")
                .append(tags);

        FlickrResponse flickrResponse = null;
        try {
            String JsonString = readJson(stringBuilder.toString());
            flickrResponse = mapper.readValue(JsonString,FlickrResponse.class);
        }catch (Exception e){
            System.out.println(e);
        }
        return flickrResponse;
    }

    /**
     * getImage photo method requests api response and converts string into POJO
     *
     * @param photo object which holds required data for getImage method
     * @return Image object.
     */
    @Override
    public Image getImage(Photo photo) {
        Image img;
        img = new Image(String.format(AppProperties.getProperty("photo_url"),photo.getFarm(),photo.getServer(),photo.getId(),photo.getSecret()));
        return img;
    }

    /**
     * Opens url connection for the given url and reads connection data prepares for conversion.
     *
     * @param apiUrl apiUrl is connection url.
     * @return String response for conversion.
     * */
    private String readJson(String apiUrl) {
        String response = null;
        try {
            URL url = new URL(apiUrl);
            URLConnection con = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String inputline;
            
            while ((inputline = in.readLine()) != null) {
                response = inputline;
            }

        } catch (IOException e) {
            System.out.println(e);
        }
        return response;
    }
}