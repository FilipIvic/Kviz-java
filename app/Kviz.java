package hr.kviz.app;

import java.awt.EventQueue;

import hr.kviz.gui.PrijavaFrame;
import hr.kviz.util.KvizUtil;

//pokretanje programa
public class Kviz {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					createAndShowGUI();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	protected static void createAndShowGUI() throws Exception {
		
		PrijavaFrame frame = new PrijavaFrame();	//objekt frame (ekran koji prikazujemo)
		frame.pack();
		KvizUtil.centreWindow(frame);	
		frame.setVisible(true);
		
		return;
	}

	
}
