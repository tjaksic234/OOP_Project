package Application;

import Database.StudentDataRepository;
import controller.Controller;
import view.ClientFrame;
import Database.GradeEvaluationRepository;
import Database.TeacherDataRepository;

import javax.swing.*;

public class adminTest {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Controller controller = new Controller(new StudentDataRepository(), new TeacherDataRepository(),
                    new GradeEvaluationRepository());

            ClientFrame clientFrame = new ClientFrame();
            clientFrame.setController(controller);

        });
    }
}
