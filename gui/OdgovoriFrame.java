package hr.kviz.gui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import hr.kviz.dto.NatjecateljDto;
import hr.kviz.dto.OdgovorCompareDto;
import hr.kviz.dto.PitanjeCompareDto;
import hr.kviz.dto.RezultatDto;
import hr.kviz.dto.SvaPitanjaDto;
import hr.kviz.manager.PitanjeManager;
import hr.kviz.manager.RezultatManager;
import hr.kviz.util.KrajException;
import net.miginfocom.swing.MigLayout;

public class OdgovoriFrame extends JFrame {


	private static final long serialVersionUID = -5239306868572840064L;
	
	private RezultatDto rezultatdto;
	private Timer timer = null;
	private SvaPitanjaDto svaPitanja = null;
	private List<PitanjeCompareDto> grupaPitanja = null;
	private PitanjeCompareDto odabranoPitanje = null;
	private long vrijemeZavrsetka = 0l;
	private long vrijemeIzbora = 0l;
	
	private static final String PANEL_PODRUCJE = "panelPodrucje";
	private static final String PANEL_PITANJE = "panelPitanje";
	private static final String PANEL_REZULTAT = "panelRezultat";
	
	private static final String IZBOR_START = "Pritisnite start za početak kviza";
	private static final String IZBOR_PODRUCJE = "Izaberite područje za prikaz pitanja";
	private static final String IZBOR_PITANJE = "Odgovorite na pitanje";
	private static final String IZBOR_REZULTAT = "Prikaz rezultata";
	
	private JTextField textVrijeme;
	private JTextField textBodovi;
	private JPanel panelCard;
	private JPanel panelPodrucje;
	private JLabel lblPodrucje_1;
	private JLabel lblPodrucje_2;
	private JLabel lblPodrucje_3;
	private JLabel lblPodrucje_4;
	private JPanel panelPitanje;
	private JLabel lblPitanje;
	private JLabel lblOdgovor_A;
	private JLabel lblOdgovor_B;
	private JLabel lblOdgovor_C;
	private JLabel lblOdgovor_D;
	private JPanel panelStart;
	private JLabel lblStart;
	private JLabel lblBodovi;
	private JPanel panelRezultat;
	private JLabel lblKraj;
	private JPanel panelLista;
	private JLabel lblIzbor;

	public OdgovoriFrame(NatjecateljDto natjecatelj) {

		rezultatdto = new RezultatDto();
		rezultatdto.setIdNatjecatelja(natjecatelj.getIdNatjecatelja());
		rezultatdto.setIme(natjecatelj.getIme());
		rezultatdto.setBrojPitanja(0);
		rezultatdto.setBrojTocnih(0);
		rezultatdto.setBodova(0);
		rezultatdto.setPoredak(0);
		
		svaPitanja = new SvaPitanjaDto();
		svaPitanja.setSvaPitanja(dajSvaPitanja());
		
		setResizable(false);
		setTitle("Odgovori točno na pitanja");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new MigLayout("", "[grow,fill][grow,fill]", "[][grow][][]"));
		
		lblIzbor = new JLabel(IZBOR_START);
		lblIzbor.setOpaque(true);
		lblIzbor.setHorizontalAlignment(SwingConstants.CENTER);
		lblIzbor.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblIzbor.setBackground(SystemColor.activeCaption);
		getContentPane().add(lblIzbor, "cell 0 0 2 1,growx");
		
		panelCard = new JPanel();
		getContentPane().add(panelCard, "cell 0 1 2 1,grow");
		panelCard.setLayout(new CardLayout(0, 0));
		
		panelStart = new JPanel();
		panelStart.setBackground(Color.WHITE);
		panelCard.add(panelStart, "name_478242127400");
		panelStart.setLayout(new MigLayout("", "[727px,grow,center]", "[415px,grow]"));
		
		lblStart = new JLabel("");
		lblStart.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblStart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {					
					prikaziPodrucjaZaIzbor();
				} catch (KrajException e) {
					lblIzbor.setText(IZBOR_REZULTAT);
					showCard(PANEL_REZULTAT);
				}
				lblIzbor.setText(IZBOR_PODRUCJE);
				showCard(PANEL_PODRUCJE);
			}
		});
		lblStart.setIcon(new ImageIcon(OdgovoriFrame.class.getResource("/hr/kviz/icon/startYellow.jpg")));
		panelStart.add(lblStart, "cell 0 0,alignx center,aligny center");
		
		panelPodrucje = new JPanel();
		panelCard.add(panelPodrucje, PANEL_PODRUCJE);
		panelPodrucje.setLayout(new MigLayout("", "[400px][400px]", "[200px][200px]"));
		
		lblPodrucje_1 = new JLabel("Područje A");
		lblPodrucje_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				podrucje(0);
			}
		});
		lblPodrucje_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblPodrucje_1.setBackground(SystemColor.info);
		lblPodrucje_1.setOpaque(true);
		lblPodrucje_1.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		lblPodrucje_1.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblPodrucje_1.setHorizontalAlignment(SwingConstants.CENTER);
		panelPodrucje.add(lblPodrucje_1, "cell 0 0,grow");
		
		lblPodrucje_2 = new JLabel("<html>Područje<br/>B</html>");
		lblPodrucje_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				podrucje(1);
			}
		});
		lblPodrucje_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblPodrucje_2.setBackground(SystemColor.info);
		lblPodrucje_2.setOpaque(true);
		lblPodrucje_2.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		lblPodrucje_2.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblPodrucje_2.setHorizontalAlignment(SwingConstants.CENTER);
		panelPodrucje.add(lblPodrucje_2, "cell 1 0,grow");
		
		lblPodrucje_3 = new JLabel("<html>Područje<br/>C</html>");
		lblPodrucje_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				podrucje(2);
			}
		});
		lblPodrucje_3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblPodrucje_3.setBackground(SystemColor.info);
		lblPodrucje_3.setOpaque(true);
		lblPodrucje_3.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		lblPodrucje_3.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblPodrucje_3.setHorizontalAlignment(SwingConstants.CENTER);
		panelPodrucje.add(lblPodrucje_3, "cell 0 1,grow");
		
		lblPodrucje_4 = new JLabel("Područje D");
		lblPodrucje_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				podrucje(3);
			}
		});
		lblPodrucje_4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblPodrucje_4.setBackground(SystemColor.info);
		lblPodrucje_4.setOpaque(true);
		lblPodrucje_4.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		lblPodrucje_4.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblPodrucje_4.setHorizontalAlignment(SwingConstants.CENTER);
		panelPodrucje.add(lblPodrucje_4, "cell 1 1,grow");
		
		panelPitanje = new JPanel();
		panelCard.add(panelPitanje, PANEL_PITANJE);
		panelPitanje.setLayout(new MigLayout("", "[400px][400px]", "[160px][120px][120px]"));
		
		lblPitanje = new JLabel("Pitanje");
		lblPitanje.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		lblPitanje.setOpaque(true);
		lblPitanje.setHorizontalAlignment(SwingConstants.CENTER);
		lblPitanje.setFont(new Font("Tahoma", Font.BOLD, 36));
		lblPitanje.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		lblPitanje.setBackground(Color.LIGHT_GRAY);
		panelPitanje.add(lblPitanje, "cell 0 0 2 1,grow");
		
		lblOdgovor_A = new JLabel("a) odgovor");
		lblOdgovor_A.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				odgovor(0);
			}
		});
		lblOdgovor_A.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblOdgovor_A.setOpaque(true);
		lblOdgovor_A.setHorizontalAlignment(SwingConstants.CENTER);
		lblOdgovor_A.setFont(new Font("Tahoma", Font.BOLD, 34));
		lblOdgovor_A.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		lblOdgovor_A.setBackground(SystemColor.info);
		panelPitanje.add(lblOdgovor_A, "cell 0 1,grow");
		
		lblOdgovor_B = new JLabel("b) odgovor");
		lblOdgovor_B.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				odgovor(1);
			}
		});
		lblOdgovor_B.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblOdgovor_B.setOpaque(true);
		lblOdgovor_B.setHorizontalAlignment(SwingConstants.CENTER);
		lblOdgovor_B.setFont(new Font("Tahoma", Font.BOLD, 34));
		lblOdgovor_B.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		lblOdgovor_B.setBackground(SystemColor.info);
		panelPitanje.add(lblOdgovor_B, "cell 1 1,grow");
		
		lblOdgovor_C = new JLabel("c) odgovor");
		lblOdgovor_C.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				odgovor(2);
			}
		});
		lblOdgovor_C.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblOdgovor_C.setOpaque(true);
		lblOdgovor_C.setHorizontalAlignment(SwingConstants.CENTER);
		lblOdgovor_C.setFont(new Font("Tahoma", Font.BOLD, 34));
		lblOdgovor_C.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		lblOdgovor_C.setBackground(SystemColor.info);
		panelPitanje.add(lblOdgovor_C, "cell 0 2,grow");
		
		lblOdgovor_D = new JLabel("d) odgovor");
		lblOdgovor_D.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				odgovor(3);
			}
		});
		lblOdgovor_D.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblOdgovor_D.setOpaque(true);
		lblOdgovor_D.setHorizontalAlignment(SwingConstants.CENTER);
		lblOdgovor_D.setFont(new Font("Tahoma", Font.BOLD, 34));
		lblOdgovor_D.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		lblOdgovor_D.setBackground(SystemColor.info);
		panelPitanje.add(lblOdgovor_D, "cell 1 2,grow");
		
		panelRezultat = new JPanel();
		panelCard.add(panelRezultat, PANEL_REZULTAT);
		panelRezultat.setLayout(new MigLayout("", "[grow]", "[100px][grow]"));
		
		lblKraj = new JLabel("");
		lblKraj.setHorizontalAlignment(SwingConstants.CENTER);
		lblKraj.setBackground(new Color(102, 0, 0));
		lblKraj.setIcon(new ImageIcon(OdgovoriFrame.class.getResource("/hr/kviz/icon/krajIgre.jpg")));
		lblKraj.setOpaque(true);
		panelRezultat.add(lblKraj, "cell 0 0,growx");
		
		panelLista = new JPanel();
		panelRezultat.add(panelLista, "cell 0 1,grow");
		
		lblBodovi = new JLabel("Bodovi");
		lblBodovi.setForeground(Color.YELLOW);
		lblBodovi.setHorizontalAlignment(SwingConstants.CENTER);
		lblBodovi.setBackground(SystemColor.textHighlight);
		lblBodovi.setOpaque(true);
		lblBodovi.setFont(new Font("Tahoma", Font.BOLD, 24));
		getContentPane().add(lblBodovi, "cell 0 2");
		
		JLabel lblPreostaloVrijeme = new JLabel("Vrijeme");
		lblPreostaloVrijeme.setHorizontalAlignment(SwingConstants.CENTER);
		lblPreostaloVrijeme.setBackground(SystemColor.activeCaption);
		lblPreostaloVrijeme.setOpaque(true);
		lblPreostaloVrijeme.setFont(new Font("Tahoma", Font.BOLD, 24));
		getContentPane().add(lblPreostaloVrijeme, "cell 1 2");
		
		textBodovi = new JTextField();
		textBodovi.setText("0");
		textBodovi.setHorizontalAlignment(SwingConstants.CENTER);
		textBodovi.setForeground(Color.YELLOW);
		textBodovi.setFont(new Font("Tahoma", Font.BOLD, 40));
		textBodovi.setEditable(false);
		textBodovi.setColumns(10);
		textBodovi.setBackground(SystemColor.textHighlight);
		getContentPane().add(textBodovi, "cell 0 3");
		
		textVrijeme = new JTextField();
		textVrijeme.setText("60:0");
		textVrijeme.setHorizontalAlignment(SwingConstants.CENTER);
		textVrijeme.setForeground(Color.BLACK);
		textVrijeme.setFont(new Font("Tahoma", Font.BOLD, 40));
		textVrijeme.setEditable(false);
		textVrijeme.setColumns(10);
		textVrijeme.setBackground(SystemColor.activeCaption);
		getContentPane().add(textVrijeme, "cell 1 3");
		
	}

	protected void odgovor(int indeksOdgovora) {
		timer.stop();
		OdgovorCompareDto odgovor = odabranoPitanje.getListaOdgovora().get(indeksOdgovora);
		System.out.println("Odgovor=" + odgovor);
		int bodova = Integer.parseInt(textBodovi.getText());
		if (odgovor.isTocno()) {
			rezultatdto.addTocno();
			lblBodovi.setForeground(Color.GREEN);
			bodova += 5;			
		} else {
			bodova -= 2;
			lblBodovi.setForeground(Color.RED);
		}
		textBodovi.setText("" + bodova);			
		
		try {
			prikaziPodrucjaZaIzbor();
			lblIzbor.setText(IZBOR_PODRUCJE);
			showCard(PANEL_PODRUCJE);
		} catch (KrajException e) {
			// nema više pitanja
			timer.stop();
			System.out.println("**************** kraj *****************");
			lblIzbor.setText(IZBOR_REZULTAT);
			showCard(PANEL_REZULTAT);
		}
		
		return;
	}

	protected void podrucje(int indeksPodrucja) {
		long vrijemePauze = System.currentTimeMillis() - vrijemeIzbora;
		vrijemeZavrsetka += vrijemePauze;
		System.out.println("Vrijeme izbora područja " + vrijemePauze + " msek");

		odabranoPitanje = grupaPitanja.get(indeksPodrucja);
		odabranoPitanje.setOdabrano(true); //s ovim označavamo da je pitanje odabrano i da se više ne može pojaviti
		System.out.println("Pitanje=" + odabranoPitanje);

		List<OdgovorCompareDto> odgovori = odabranoPitanje.getListaOdgovora();
		lblPitanje.setText(dajHtmlText(odabranoPitanje.getTekst(), 35));
		lblOdgovor_A.setText(dajHtmlText(odgovori.get(0).getTekst(), 25));
		lblOdgovor_B.setText(dajHtmlText(odgovori.get(1).getTekst(), 25));
		lblOdgovor_C.setText(dajHtmlText(odgovori.get(2).getTekst(), 25));
		lblOdgovor_D.setText(dajHtmlText(odgovori.get(3).getTekst(), 25));

		if (svaPitanja.getPitanjeBroj() == 1) {
			//na prvo pitanje postavi vrijeme i inicijalna podrucja
			vrijemeZavrsetka = System.currentTimeMillis();
			vrijemeZavrsetka += (60 * 1000); //vrijeme završetka			
			postaviVrijeme();
		} else {
			timer.restart();
		}

		lblIzbor.setText(IZBOR_PITANJE);
		showCard(PANEL_PITANJE);
		return;
	}

	protected void prikaziPodrucjaZaIzbor() throws KrajException {
		grupaPitanja = svaPitanja.sljedecaGrupaPitanja();

		lblPodrucje_1.setText(dajHtmlText(grupaPitanja.get(0).getOpisPodrucja(), 15));
		lblPodrucje_2.setText(dajHtmlText(grupaPitanja.get(1).getOpisPodrucja(), 15));
		lblPodrucje_3.setText(dajHtmlText(grupaPitanja.get(2).getOpisPodrucja(), 15));
		lblPodrucje_4.setText(dajHtmlText(grupaPitanja.get(3).getOpisPodrucja(), 15));
		
		vrijemeIzbora = System.currentTimeMillis();
		return;
	}
	
	private void postaviVrijeme() {
		ActionListener al = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				long trajanje = vrijemeZavrsetka - System.currentTimeMillis();
				if (trajanje < 0) { //završeno vrijeme
					trajanje = 0;
					textVrijeme.setText("0.0");
					Timer t = (Timer) e.getSource();
					t.stop();
					lblIzbor.setText(IZBOR_REZULTAT);
					showCard(PANEL_REZULTAT);
				}

				int sek = (int) trajanje / 1000;
				long msek = trajanje - (sek * 1000); 
				int dsek = (int) (msek / 100);
				textVrijeme.setText("" + sek + ":" + dsek);
			}
		};
		timer = new Timer(100, al);
		timer.setInitialDelay(0);
		timer.start();
		
		return;
	}

	protected void showCard(String cardName) {
		
		if (PANEL_REZULTAT.equals(cardName)) {
			rezultatdto.setBrojPitanja(svaPitanja.getPitanjeBroj());
			rezultatdto.setBodova(Integer.parseInt(textBodovi.getText()));
			RezultatManager rezman = new RezultatManager();
			rezman.prikazRezultata(panelLista, rezultatdto);
		}
		
		CardLayout layout = (CardLayout) panelCard.getLayout();			
		layout.show(panelCard, cardName);
		return;
	}
	
	protected String dajHtmlText(String text, int duljina) {
		StringBuffer sb = new StringBuffer();
		sb.append("<html>");
		int rbr = 0;
		char[] charArray = text.toCharArray();
		for (char c : charArray) {
			rbr++;
			if (c == ' ' && rbr >= duljina) {
				sb.append("<br/>");
				rbr = 0;
			} else {
				sb.append(c);
			}
		}
		sb.append("</html>");
		//System.out.println(sb.toString());
		return sb.toString();
	}
	
	protected List<PitanjeCompareDto> dajSvaPitanja() {
		List<PitanjeCompareDto> lista = null;
		try {
			PitanjeManager manager = new PitanjeManager();
			lista = manager.dajSvaPitanjaSaOdgovorima();
			
			//lista = KvizUtil.dajTestnaPitanja();
		} catch (Exception e) {
			new ArrayList<>();
		}
		return lista;
	}
	
}
