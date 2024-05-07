/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.personalprojects.cats_app;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author MolinAnimation
 */
public class Start {

    public static void main(String[] args) {

        int menu_option = -1;
        String[] buttons = { "1. See cats", "2. Exit" };

        do {

            // mAIN MENU
            String option = (String) JOptionPane.showInputDialog(null, "Java cats", "Main menu",
                    JOptionPane.INFORMATION_MESSAGE, null, buttons, buttons[0]);

            // user sellection
            for (var i = 0; i < buttons.length; i++) {
                if (option.equals(buttons[i])) {
                    menu_option = i;
                }
            }

            switch (menu_option) {
                case 0:
                {
                    try {
                        CatsService.seeCats();
                    } catch (IOException ex) {
                        Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                    break;

                default:
                    break;
            }

        } while (menu_option != 1);
    }
}
