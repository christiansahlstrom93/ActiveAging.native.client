package com.web.activeagingnativeclient.Server;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Christian on 2015-10-02.
 */
public class ShopItems extends ServerHandler {

    private List<String> title = new ArrayList<>();
    private List<String> description = new ArrayList<>();
    private List<String> imageUrl = new ArrayList<>();

    private List<Float> price = new ArrayList<>();

    public void fetchShopInformation(String query) {

        getTitle().add("Burgare");
        getTitle().add("Burgare");
        getTitle().add("Burgare");

        getDescription().add("Fruktansvärt god");
        getDescription().add("Fruktansvärt god");
        getDescription().add("Fruktansvärt god");

        getPrice().add((float) 100);
        getPrice().add((float) 200);
        getPrice().add((float) 900);

        getImageUrl().add("http://www.mcdonalds.ie/content/iehome/food/_jcr_content/genericpagecontent/everything/image.img.jpg/1381846764459.jpg");
        getImageUrl().add("http://www.mcdonalds.ie/content/iehome/food/_jcr_content/genericpagecontent/everything/image.img.jpg/1381846764459.jpg");
        getImageUrl().add("http://www.mcdonalds.ie/content/iehome/food/_jcr_content/genericpagecontent/everything/image.img.jpg/1381846764459.jpg");
    }

    public List<String> getTitle() {
        return title;
    }

    public List<String> getDescription() {
        return description;
    }

    public List<String> getImageUrl() {
        return imageUrl;
    }

    public List<Float> getPrice() {
        return price;
    }
}
