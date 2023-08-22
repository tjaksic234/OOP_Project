package placeholder;

import view.TableFrame;

import javax.swing.*;

public class table extends JFrame {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TableFrame();
        });
    }
}
