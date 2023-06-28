package frame_panel;

import javax.swing.*;

public class JTABLE_TESTER {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new StudentTablePanel();
            }
        });
    }
}
