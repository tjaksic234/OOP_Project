package Application;

import view.MainFrame;

import javax.swing.*;

/**
 * The main class of the application.
 * This class extends the JFrame class and serves as the entry point for the application.
 */
public class App extends JFrame {

    /**
     * The main method of the application.
     * It creates and displays the main frame of the application.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {

            /**
             * Run method to execute the application.
             */
            public void run() {
                new MainFrame();
            }
        });
    }
}
