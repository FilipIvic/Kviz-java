package hr.kviz.gui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import hr.kviz.dto.NatjecateljDto;
import hr.kviz.manager.PrijavaManager;
import hr.kviz.util.Konstante;
import hr.kviz.util.KvizUtil;
import net.miginfocom.swing.MigLayout;

public class PrijavaFrame extends JFrame {

	private static final long serialVersionUID = -3929871769370145476L;
	private static final String TEXT_PRIJAVA_DEFAULT = "email, telefon, id";
	private static final String TEXT_PRIJAVA_REGISTRACIJA = "Unesite email, telefon, id, ...";
	
	private static final String PANEL_PRIJAVA = "panelPrijava";
	private static final String PANEL_LOZINKA = "panelLozinka";
	private static final String PANEL_REGISTRACIJA = "panelRegistracija";
	
	private NatjecateljDto natjecatelj;
	
	private JTextField textIdPrijave;
	private JPanel panelRegistracija;
	private JTextField textRegistracija;
	private JPasswordField passwordNatjecatelja;
	private JLabel lblPassNatj;
	private JLabel labelVerifyPass;
	private JPasswordField passwordVerify;
	private JLabel labelIme;
	private JTextField textIme;
	private JLabel lblKviz;
	private JButton btnDalje;
	private JPanel panelLozinka;
	private JLabel lblPorukaPrijave;
	private JLabel labelKviz_1;
	private JLabel labelPorukaLogin;
	private JPasswordField passwordKorisnika;
	private JButton btnPrijava;
	private JLabel lblNatjecatelj;
	private JLabel labelPorukaReg;
	private JButton btnRegistracija;
	private JLabel labelReg;
	
	//prijava Frame(okvir) se sastoji od 3 panele, panel Prijava, panel Lozinka, panel Registracija,
	
	public PrijavaFrame() {
		getContentPane().setBackground(Color.WHITE);
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Kviz");
		getContentPane().setLayout(new CardLayout(25, 0));
		
		
		
		
		//panel Prijava
		
		JPanel panelPrijava = new JPanel();
		panelPrijava.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		getContentPane().add(panelPrijava, PANEL_PRIJAVA);
		panelPrijava.setBackground(Color.WHITE);
		panelPrijava.setLayout(new MigLayout("", "[grow]", "[40px:n][80px:n][30px:n][30px:n][30px:n][60px:n][30px:n]"));
		
		lblKviz = new JLabel("Kviz");
		lblKviz.setForeground(Color.GRAY);
		lblKviz.setFont(new Font("Verdana", Font.BOLD, 14));
		panelPrijava.add(lblKviz, "cell 0 0");
		
		JLabel labelPrijava = new JLabel("Prijava");
		labelPrijava.setFont(new Font("Verdana", Font.BOLD, 16));
		panelPrijava.add(labelPrijava, "cell 0 1,grow");
		
		textIdPrijave = new JTextField();
		textIdPrijave.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				showDefaultText(e, textIdPrijave, TEXT_PRIJAVA_DEFAULT);
			}
		});
		textIdPrijave.setForeground(Color.LIGHT_GRAY);
		textIdPrijave.setFont(new Font("Dialog", Font.BOLD, 14));
		textIdPrijave.setColumns(30);
		textIdPrijave.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.GRAY));
		panelPrijava.add(textIdPrijave, "cell 0 2,grow");
		
		JLabel labelPrijavaRegistracija = new JLabel("Niste registrirani? Registrirajte se.");
		labelPrijavaRegistracija.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		labelPrijavaRegistracija.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				showCard(PANEL_REGISTRACIJA);
			}
		});
		labelPrijavaRegistracija.setForeground(Color.BLUE);
		labelPrijavaRegistracija.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelPrijava.add(labelPrijavaRegistracija, "cell 0 4,growx,aligny center");
		
		lblPorukaPrijave = new JLabel("");
		lblPorukaPrijave.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblPorukaPrijave.setForeground(Color.RED);
		panelPrijava.add(lblPorukaPrijave, "cell 0 5,growx,aligny center");
		
		btnDalje = new JButton("Dalje");
		btnDalje.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDalje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				provjeraIdKodPrijave();
			}
		});		
		btnDalje.setContentAreaFilled(false);
		btnDalje.setBorderPainted(false);
		btnDalje.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnDalje.setOpaque(true);
		btnDalje.setForeground(Color.WHITE);
		btnDalje.setBackground(Color.BLUE);
		btnDalje.setPreferredSize(new Dimension(100, 25));
		panelPrijava.add(btnDalje, "cell 0 6,alignx right,growy");

		
		
		
		//panel Lozinka
		
		
		panelLozinka = new JPanel();
		panelLozinka.setLayout(new MigLayout("", "[grow]", "[40px:n][50px:n][30px:n][160px:n][30px:n]"));
		panelLozinka.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		panelLozinka.setBackground(Color.WHITE);
		getContentPane().add(panelLozinka, PANEL_LOZINKA);
		
		labelKviz_1 = new JLabel("Kviz");
		labelKviz_1.setForeground(Color.GRAY);
		labelKviz_1.setFont(new Font("Verdana", Font.BOLD, 14));
		panelLozinka.add(labelKviz_1, "cell 0 0");
		
		lblNatjecatelj = new JLabel("Natjecatelj");
		lblNatjecatelj.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				showCard(PANEL_PRIJAVA);
			}
		});
		lblNatjecatelj.setIcon(new ImageIcon(PrijavaFrame.class.getResource("/hr/kviz/icon/leftArrow.png")));
		lblNatjecatelj.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNatjecatelj.setFont(new Font("Verdana", Font.BOLD, 14));
		panelLozinka.add(lblNatjecatelj, "cell 0 1,aligny center");
		
		passwordKorisnika = new JPasswordField();
		panelLozinka.add(passwordKorisnika, "cell 0 2,grow");
		
		labelPorukaLogin = new JLabel("");
		labelPorukaLogin.setForeground(Color.RED);
		labelPorukaLogin.setFont(new Font("Verdana", Font.PLAIN, 14));
		panelLozinka.add(labelPorukaLogin, "cell 0 3,alignx center,aligny center");
		
		btnPrijava = new JButton("Prijava");
		btnPrijava.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prijava();
			}
		});
		btnPrijava.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPrijava.setContentAreaFilled(false);
		btnPrijava.setBorderPainted(false);
		btnPrijava.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnPrijava.setOpaque(true);
		btnPrijava.setForeground(Color.WHITE);
		btnPrijava.setBackground(Color.BLUE);
		btnPrijava.setPreferredSize(new Dimension(100, 25));
		panelLozinka.add(btnPrijava, "cell 0 4,alignx right,growy");
		
		
		
		//panel Registracija
		
		panelRegistracija = new JPanel();
		panelRegistracija.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent arg0) {
				System.out.println("panelRegistracija componentShown");
				initTextField(textRegistracija, TEXT_PRIJAVA_REGISTRACIJA);
			}
		});
		panelRegistracija.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		panelRegistracija.setBackground(Color.WHITE);
		getContentPane().add(panelRegistracija, PANEL_REGISTRACIJA);
		panelRegistracija.setLayout(new MigLayout("", "[100px][grow]", "[40px:n][35px][30px][35px][30px][35px][60px][30px]"));

		labelReg = new JLabel("Registracija natjecatelja");
		labelReg.setForeground(Color.GRAY);
		labelReg.setFont(new Font("Verdana", Font.BOLD, 14));
		panelRegistracija.add(labelReg, "cell 0 0 2 1");
		
		textRegistracija = new JTextField();
		textRegistracija.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				provjeraIdKodRegistracije();
			}
		});
		textRegistracija.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				showDefaultText(arg0, textRegistracija, TEXT_PRIJAVA_REGISTRACIJA);
			}
		});
		textRegistracija.setFont(new Font("Dialog", Font.BOLD, 14));
		textRegistracija.setColumns(30);
		textRegistracija.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.GRAY));
		panelRegistracija.add(textRegistracija, "cell 0 1 2 1,grow");
		
		lblPassNatj = new JLabel("Lozinka : ");
		lblPassNatj.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelRegistracija.add(lblPassNatj, "flowx,cell 0 2,alignx right,growy");
		
		passwordNatjecatelja = new JPasswordField();
		panelRegistracija.add(passwordNatjecatelja, "cell 1 2,grow");
		
		labelVerifyPass = new JLabel("Potvrdi lozinku: ");
		labelVerifyPass.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelRegistracija.add(labelVerifyPass, "cell 0 3,alignx right,growy");
		
		passwordVerify = new JPasswordField();
		panelRegistracija.add(passwordVerify, "cell 1 3,grow");
		
		labelIme = new JLabel("Ime za rezultat :");
		labelIme.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelRegistracija.add(labelIme, "cell 0 4,alignx trailing");
		
		textIme = new JTextField();
		textIme.setFont(new Font("Dialog", Font.BOLD, 14));
		textIme.setColumns(30);
		textIme.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.GRAY));
		panelRegistracija.add(textIme, "cell 1 4,grow");
		
		labelPorukaReg = new JLabel("");
		labelPorukaReg.setForeground(Color.RED);
		labelPorukaReg.setFont(new Font("Verdana", Font.PLAIN, 14));
		panelRegistracija.add(labelPorukaReg, "cell 0 6 2 1,grow");
		
		btnRegistracija = new JButton("Registracija");
		btnRegistracija.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean uspjesnaRegistracija = registracija();
				if (uspjesnaRegistracija) {
					showFrameOdgovori();
				}
			}
		});
		btnRegistracija.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRegistracija.setContentAreaFilled(false);
		btnRegistracija.setBorderPainted(false);
		btnRegistracija.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnRegistracija.setOpaque(true);
		btnRegistracija.setForeground(Color.WHITE);
		btnRegistracija.setBackground(Color.BLUE);
		btnRegistracija.setPreferredSize(new Dimension(100, 25));
		panelRegistracija.add(btnRegistracija, "cell 1 7,alignx right,growy");

		initTextField(textIdPrijave, TEXT_PRIJAVA_DEFAULT);		
		
		return;
	}
	
	//.gui ->funkcija -> PrijavaMenager.java -> NatjecateljSql.java

	protected void provjeraIdKodPrijave() {
		System.out.println("dalje actionPerformed");
		String idPrijava = textIdPrijave.getText().trim();
		if (TEXT_PRIJAVA_DEFAULT.equals(idPrijava) || idPrijava.isEmpty()) {
			lblPorukaPrijave.setText("Unesite svoj id za prijavu");
		} else {
			lblPorukaPrijave.setText("");
			try {
				PrijavaManager mng = new PrijavaManager();
				natjecatelj = mng.checkNatjecatelj(idPrijava);
				if (natjecatelj.getIdNatjecatelja() == null) {
					lblPorukaPrijave.setText("Vaš ID nije pronađen u registru. Provjerite ID ili se registrirajte.");
				} else {
					lblNatjecatelj.setText(natjecatelj.getIme());
					String cardName = PANEL_LOZINKA;
					showCard(cardName);
					passwordKorisnika.requestFocus(true);							
				}
			} catch (Exception err) {
				err.printStackTrace();
				String message = "Error message [" + err.getMessage() + "]";
				JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		return;
	}

	protected void prijava() {
		labelPorukaLogin.setText("");
		boolean prijavaOk = false;
		try {
			char[] ch = passwordKorisnika.getPassword();
			PrijavaManager mng = new PrijavaManager();
			prijavaOk = mng.checkNatjecatelj(natjecatelj, ch);
			if (prijavaOk == false) {
				labelPorukaLogin.setText("Pogrešna lozinka, ponovite unos");
			}
		} catch (Exception err) {
			prijavaOk = false;
			String message = "Error message [" + err.getMessage() + "]";
			JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
		}				
		if (prijavaOk) {
			showFrameOdgovori();
		}
		return;
	}

	protected void provjeraIdKodRegistracije() {
		System.out.println("textRegistracija focusLost");
		labelPorukaReg.setText("");
		String idPrijava = textRegistracija.getText().trim();
		try {
			PrijavaManager mng = new PrijavaManager();
			natjecatelj = mng.checkNatjecatelj(idPrijava);
			if (natjecatelj.getIdNatjecatelja() != null) {
				labelPorukaReg.setText("Uneseni ID (" + idPrijava + ")koristi drugi natjecatelj, promijenite ID.");
				initTextField(textRegistracija, TEXT_PRIJAVA_REGISTRACIJA);
			} else {
				passwordNatjecatelja.requestFocus(true);							
			}
		} catch (Exception err) {
			err.printStackTrace();
			String message = "Error message [" + err.getMessage() + "]";
			JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
		}
		return;
	}

	protected boolean registracija() {
		boolean registracijaOK = false;
		System.out.println("btnRegistracija actionPerformed");
		labelPorukaReg.setText("");
		String idPrijava = textRegistracija.getText().trim();
		if (idPrijava.length() == 0 || TEXT_PRIJAVA_REGISTRACIJA.equals(idPrijava)) {
			labelPorukaReg.setText("Unesite ID za registraciju");
			textRegistracija.requestFocus(true);
		} else {
			char[] chPass = passwordNatjecatelja.getPassword();
			if (chPass.length < 4) {
				labelPorukaReg.setText("Unesite lozinku / lozinka mora imati minimalno 4 znaka");
				passwordKorisnika.requestFocus(true);
			} else {
				char[] chVerf = passwordVerify.getPassword();
				boolean passOk = Arrays.equals(chPass, chVerf);
				if (passOk == false) {
					labelPorukaReg.setText("Lozinka i verificirana lozinka nisu identične, ponovite verifikaciju");
					passwordVerify.requestFocus(true);
				} else {
					String ime = textIme.getText().trim();
					if (ime.length() == 0) {
						labelPorukaReg.setText("Unesite ime za prikaz na top listi rezultata");
						textIme.requestFocus(true);
					} else {
						try {
							PrijavaManager mng = new PrijavaManager();
							natjecatelj = mng.unosNatjecatelja(idPrijava, ime, chPass);
							registracijaOK = true;
						} catch (Exception err) {
							err.printStackTrace();
							String message = "Error message [" + err.getMessage() + "]";
							JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		}
		return registracijaOK;
	}

	private void showDefaultText(KeyEvent ke, JTextField textfield, String textDefault) {
		int keyCode = ke.getKeyCode();
		if (Character.isJavaIdentifierPart(keyCode)) {
			String sid = textfield.getText().trim();
			if (sid.isEmpty() || textDefault.equals(sid)) {
				initTextField(textfield, textDefault);
			} else {	
				textfield.setForeground(Color.BLACK);
				if (sid.startsWith(textDefault) == false) { 
					//imamo unos negdje						
					if (sid.contains(textDefault)) { 
						//unos je na početku, izbriši višak
						int pozicija = sid.indexOf(textDefault);
						textfield.setText(sid.substring(0, pozicija));
					}
				}
			}
		}
		return;
	}

	private void initTextField(JTextField textfield, String textDefault) {
		textfield.setForeground(Color.LIGHT_GRAY);
		textfield.setText(textDefault);
		textfield.setCaretPosition(0);
		textfield.requestFocus(true);
		return;
	}
	
	private void showCard(String cardName) {
		CardLayout layout = (CardLayout) getContentPane().getLayout();			
		layout.show(getContentPane(), cardName);
		return;
	}

	private void showFrameOdgovori() {
				
		JFrame frame = null;
		String uloga = natjecatelj.getUloga();
		if (Konstante.ULOGA_ADMINISTRATOR.equals(uloga)) {
			frame = new AdminFrame();
		} else {
			this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			frame = new OdgovoriFrame(natjecatelj);
			this.setCursor(Cursor.getDefaultCursor());
		}
		frame.pack();
		KvizUtil.centreWindow(frame);

		this.setVisible(false);
		frame.setVisible(true);
		
		return;
	}
		
}
