/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.personalprojects.cats_app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author MolinAnimation
 */
public class CatsDAO {
    
    public static void saveFavoriteCat(Cats cat, String nickName) {
        ConnectionJDBC con = new ConnectionJDBC();
        
        try (Connection conn = con.getConnection()) {
            PreparedStatement stmt = null;
            try {
                String query = "INSERT INTO cats (nick_name, image) VALUES (?,?)";
                stmt = conn.prepareStatement(query);
                stmt.setString(1, nickName);
                stmt.setString(2, cat.getUrl());
                
                stmt.executeUpdate();
                System.out.println("Favorite " + nickName + " Saved Succesfully");
            } catch (SQLException e) {
                System.out.println(e);
            }
        } catch (SQLException e) {
            System.out.println("Error \n" + e);
        }
    }
    
    public static List<Cats> listFavoriteCats() {
        List<Cats> allFavoriteCats = new LinkedList<>();
        ConnectionJDBC con = new ConnectionJDBC();
        try (Connection conn = con.getConnection()) {
            PreparedStatement stmt = null;
            try {
                String query = "SELECT * FROM cats";
                stmt = conn.prepareStatement(query);
                
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Cats favoriteCat = new Cats();
                    favoriteCat.setUrl(rs.getString("image"));
                    favoriteCat.setNickName(rs.getString("nick_name"));
                    allFavoriteCats.add(favoriteCat);
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        } catch (SQLException e) {
            System.out.println("Error \n" + e);
        }
        return allFavoriteCats;
    }
}
