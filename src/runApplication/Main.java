package runApplication;

import java.awt.EventQueue;

import view.GUI_Login;


/**
 * This class runs the java application
 * @author Cindy Wang
 * @version 1.0
 */
public class Main {

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GUI_Login frame = new GUI_Login();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
