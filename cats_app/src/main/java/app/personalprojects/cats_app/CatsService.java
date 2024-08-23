/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.personalprojects.cats_app;

import com.google.gson.Gson;
import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
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

        Request request = new Request.Builder()
                .url("https://api.thecatapi.com/v1/images/search")
                .get()
                .build();
        Response response = client.newCall(request).execute();

        String jsonResponse = response.body().string();
        System.out.println("json response " + response);
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

            String menu = "options: "
                    + "\n1. See other cat "
                    + "\n 2. Favorite"
                    + "\n 3. Favorite Cats"
                    + "\n  Back to main menu";

            String[] buttons = {"see other cat", "favorite", "favorite cats", "back"};
            String cat_id = cats.getId();
            String option = (String) JOptionPane.showInputDialog(null, menu, cat_id, JOptionPane.INFORMATION_MESSAGE, wallpaperCat, buttons, buttons[0]);
            int selection = -1;

            for (int i = 0; i < buttons.length; i++) {
                if (option.equals(buttons[i])) {
                    selection = i;
                }
            }

            switch (selection) {
                case 0:
                    seeCats();
                    break;
                case 1:
                    favoriteCat(cats);
                    break;
                case 2:
                    System.out.println("lanzando favorite cats");
                    listFavoriteCats(wallpaperCat);
                    break;
                default:
                    break;
            }

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void favoriteCat(Cats cat) {
        String input = JOptionPane.showInputDialog(null, "Type a nick for this cat");
        CatsDAO.saveFavoriteCat(cat, input);
    }

    public static void listFavoriteCats(ImageIcon currentCat) throws MalformedURLException, IOException {

        List<Cats> favoriteCats = CatsDAO.listFavoriteCats();
        String[] buttons = new String [favoriteCats.size()];
        int counter = 0;
        for (Cats cat : favoriteCats) {
            buttons[counter] = cat.getNickName();
            counter ++;
        }

        String option = (String) JOptionPane.showInputDialog(null, null, "favorites", JOptionPane.INFORMATION_MESSAGE, currentCat, buttons, buttons[0]);

        Cats currentFavoriteCat = new Cats();
        for (int i = 0; i < buttons.length; i++) {
            if (option.equals(buttons[i])) {
                currentFavoriteCat = favoriteCats.get(i);
                break;
            }
        }
        
        
        URL url = new URL(currentFavoriteCat.getUrl());
        
        Image image = ImageIO.read(url);

        ImageIcon wallpaperCat = new ImageIcon(image);

        if (wallpaperCat.getIconWidth() > 800) {
            // redimencionar
            Image wallpaper = wallpaperCat.getImage();
            Image modified = wallpaper.getScaledInstance(800, 600, java.awt.Image.SCALE_SMOOTH);
            wallpaperCat = new ImageIcon(modified);
        }
        
        
        JOptionPane.showInputDialog(null, null,currentFavoriteCat.getNickName() , JOptionPane.INFORMATION_MESSAGE, wallpaperCat, buttons, buttons[0]);
        //mostrar la imagen del gato correspondiente en la lista de selection

    }
}
