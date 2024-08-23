/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.personalprojects.cats_app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author MolinAnimation
 */
public class ConnectionJDBC {
    public Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3307/favorite_cats", "root", "");
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("conection failed");
        }
        return con;
    }
}
