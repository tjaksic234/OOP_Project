package frame_panel;

import aux_classes.StudentManager;

import javax.swing.*;

public class RunStudentEvaluation extends JFrame {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new StudentPanel(new StudentManager());
            }
        });
    }
}
