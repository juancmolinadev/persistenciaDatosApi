/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.personalprojects.cats_app;

import com.google.gson.Gson;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 *
 * @author MolinAnimation
 */
public class CatsService {

    public static void seeCats() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        Request request = new Request.Builder()
                .url("https://api.thecatapi.com/v1/images/search")
                .get()
                .build();
        Response response = client.newCall(request).execute();

        String jsonResponse = response.body().string();
        jsonResponse = jsonResponse.substring(1, jsonResponse.length() - 1);

        Gson gson = new Gson();
        Cats cats = gson.fromJson(jsonResponse, Cats.class);

        Image image = null;
        try {
            URL url = new URL(cats.getUrl());
            image = ImageIO.read(url);

            ImageIcon wallpaperCat = new ImageIcon(image);

            if (wallpaperCat.getIconWidth() > 800) {
                // redimencionar
                Image wallpaper = wallpaperCat.getImage();
                Image modified = wallpaper.getScaledInstance(800, 600, java.awt.Image.SCALE_SMOOTH);
                wallpaperCat = new ImageIcon(modified);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
