package placeholder;

import Database.ExamInformationRepository;
import Database.StudentDataRepository;
import controller.Controller;

import javax.swing.*;

public class adminTest {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Controller controller = new Controller(new StudentDataRepository(), new TeacherDataRepository(),
                    new ExamInformationRepository(), new RegisteredExamsRepository(), new GradeEvaluationRepository());

            ClientFrame clientFrame = new ClientFrame();
            clientFrame.setController(controller);

        });
    }
}
