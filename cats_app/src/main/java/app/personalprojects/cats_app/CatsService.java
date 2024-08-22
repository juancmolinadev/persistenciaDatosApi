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
        MediaType mediaType = MediaType.parse("text/plain");
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
            
            String menu = "options: "+
                    "\n1. See other cat "+
                    "\n 2. Favorite" + 
                    "\n Back to main menu";
            
            String[] buttons = {"see other cat", "favorite", "back"};
            String cat_id = cats.getId();
            String option = (String) JOptionPane.showInputDialog(null,menu,cat_id,JOptionPane.INFORMATION_MESSAGE, wallpaperCat,buttons,buttons[0]);
            int selection = -1;
            
            for(int i=0; i<buttons.length; i++){
                if(option.equals(buttons[i])){
                    selection = i;
                }
            }
            
            switch(selection){
                case 0:
                    seeCats();
                    break;
                case 1:
                    favoriteCat(cats);
                    break;
                default:
                    break;
            }
            
            
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
    public static void favoriteCat(Cats cat){
        
    }
}
