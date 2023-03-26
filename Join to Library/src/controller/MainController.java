package controller;

import java.util.Timer;
import java.util.TimerTask;

import model.DataBaseMethods;
import model.Student;
import view.MainPanel;
import view.MainWindow;
import view.SeeBooksPanel;
import view.StudentInfoPanel;
import view.custom.CustomException;

public class MainController {
	public static void goToShowInfo (String cc, MainWindow frame) throws CustomException {
		Student student = DataBaseMethods.getStudent(cc);
		if (!student.getExists()) {
			throw new CustomException(-1, "Estudiante no encontrado");
		}
		goToShowInfo(student, frame);
	}

	public static void goToShowInfo (Student student, MainWindow frame) {
		frame.getPanelBody().removeAll();
		frame.addToBody(new StudentInfoPanel(frame, student));
		frame.getPanelBody().revalidate();
	}

	public static void goToMain (MainWindow frame) {
		frame.getPanelBody().removeAll();
		frame.addToBody(new MainPanel(frame));
		frame.getPanelBody().revalidate();
	}

	/**
	 * @deprecated no usado
	 * @param frame
	 */
	public static void addTimerToMain (MainWindow frame) {
		Timer t = new Timer();
		t.schedule(new TimerTask() {
			public void run () {
				goToMain(frame);
			}
		}, 10000);
	}

	public static void goToSeeBooks (MainWindow frame, Student s) {
		frame.getPanelBody().removeAll();
		frame.addToBody(new SeeBooksPanel(frame, s));
		frame.getPanelBody().revalidate();
	}
}
