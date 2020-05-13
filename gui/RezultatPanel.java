package hr.kviz.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import net.miginfocom.swing.MigLayout;

public class RezultatPanel extends JPanel {

	private static final long serialVersionUID = 670820573093556120L;
	
	public RezultatPanel() {
		setBackground(Color.WHITE);
		setLayout(new MigLayout("", "[grow][grow][grow][grow][grow][grow]", "[][][][][][][][][][][]"));
				
		dodajZaglavlje();;
		
	}

	private void dodajZaglavlje() {
		List<String> zaglavlje = new ArrayList<>();
		zaglavlje.add("Poredak");
		zaglavlje.add("Ime");
		zaglavlje.add("Vrijeme");
		zaglavlje.add("Pitanja");
		zaglavlje.add("Točno");
		zaglavlje.add("Bodova");
		addJLabel(zaglavlje, Color.BLUE, 0, true);
	}
	
	public void dodajRezultat(int red, List<String> podaci, Color bojaOkvira, boolean opaque) {
		addJLabel(podaci, bojaOkvira, red, opaque);
		return;
	}
	
	private void addJLabel(List<String> podaci, Color bojaOkvira, int red, boolean opaque) {
		for (int i = 0; i < 6; i++) {
			String cell = "cell " + i + " " + red + ",growx";
			JLabel labela = new JLabel(podaci.get(i));
			if (i != 1) {
				labela.setHorizontalAlignment(SwingConstants.CENTER);
			}
			labela.setBorder(new LineBorder(bojaOkvira));
			if (opaque) {
				labela.setBackground(SystemColor.activeCaption);
				labela.setOpaque(opaque);
			}
			
			labela.setFont(new Font("Verdana", Font.BOLD, 11));
			add(labela, cell);
		}
		return;
	}
		
	/*private void testLabel() {
		JLabel lbl00 = new JLabel("Poredak");
		lbl00.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.RED, Color.RED, Color.RED, Color.RED));
		lbl00.setHorizontalAlignment(SwingConstants.CENTER);
		lbl00.setFont(new Font("Verdana", Font.BOLD, 12));
		add(lbl00, "cell 0 0,growx"); //column - row
		
		JLabel lbl01 = new JLabel("Ime"); //ovo
		lbl01.setBorder(new BevelBorder(BevelBorder.RAISED, Color.GREEN, Color.GREEN, Color.GREEN, Color.GREEN));
		lbl01.setFont(new Font("Verdana", Font.BOLD, 12));
		add(lbl01, "cell 1 0,growx");
		
		JLabel lbl02 = new JLabel("Vrijeme");
		lbl02.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.ORANGE, Color.YELLOW));
		lbl02.setHorizontalAlignment(SwingConstants.CENTER);
		lbl02.setFont(new Font("Verdana", Font.BOLD, 12));
		add(lbl02, "cell 2 0,growx");
		
		JLabel lbl03 = new JLabel("Pitanja"); //ovo
		lbl03.setBorder(new LineBorder(Color.BLUE, 2, true)); 
		lbl03.setHorizontalAlignment(SwingConstants.CENTER);
		lbl03.setFont(new Font("Verdana", Font.BOLD, 12));
		add(lbl03, "cell 3 0,growx");
		
		JLabel lbl04 = new JLabel("Točno");
		lbl04.setBorder(new MatteBorder(2, 2, 2, 2, (Color) Color.MAGENTA));
		lbl04.setHorizontalAlignment(SwingConstants.CENTER);
		lbl04.setFont(new Font("Verdana", Font.BOLD, 12));
		add(lbl04, "cell 4 0,growx");
		
		JLabel lbl05 = new JLabel("Bodova");
		lbl05.setHorizontalAlignment(SwingConstants.CENTER);
		lbl05.setFont(new Font("Verdana", Font.BOLD, 12));
		add(lbl05, "cell 5 0");
		return;
	}*/
	
}
