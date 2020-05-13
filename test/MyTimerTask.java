package hr.kviz.test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class MyTimerTask  {

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(() -> {
			try {
				createTask();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		/*EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					createTask();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});*/
	}
	
	
	protected static void createTask() throws Exception {
		ActionListener al = new ActionListener() {
			int poziv = 0;
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("sleep... " + ++poziv);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (poziv == 10) {
					System.out.println("timer stop " + new Date());
					((Timer) e.getSource()).stop();
				}
				return;
			}
		};
		
		System.out.println("timer start " + new Date());		
		Timer timer = new Timer(1000, al); //create the timer which calls the actionperformed method for every 1000 millisecond(1 second=1000 millisecond)
		timer.setInitialDelay(0);
		timer.start();//start the task
		
		return;
	}

}
