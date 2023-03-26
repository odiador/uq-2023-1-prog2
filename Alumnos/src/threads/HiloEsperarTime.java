package threads;

import javax.swing.JComponent;

public class HiloEsperarTime extends Thread {
	private JComponent comp;
	private float seconds;

	public HiloEsperarTime(JComponent comp, float seconds) {
		setComponent(comp);
		setSeconds(seconds);
	}

	public void run() {
		getComponent().setVisible(true);
		try {
			sleep((int) (1000 * getSeconds()));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getComponent().setVisible(false);
	}

	public JComponent getComponent() {
		return comp;
	}

	public void setComponent(JComponent label) {
		this.comp = label;
	}

	public float getSeconds() {
		return seconds;
	}

	public void setSeconds(float seconds) {
		this.seconds = seconds;
	}
}