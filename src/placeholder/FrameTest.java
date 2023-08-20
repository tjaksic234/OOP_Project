package placeholder;

import javax.swing.*;

public class FrameTest {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ClientFrame();
            }
        });
    }
}
