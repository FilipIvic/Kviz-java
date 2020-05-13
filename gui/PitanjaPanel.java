package hr.kviz.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import hr.kviz.dto.OdgovorDto;
import hr.kviz.dto.PitanjeDto;
import hr.kviz.dto.PodrucjeDto;
import hr.kviz.manager.PitanjeManager;
import hr.kviz.model.PitanjeTableModel;
import net.miginfocom.swing.MigLayout;

public class PitanjaPanel extends JPanel {
	
	private static final long serialVersionUID = -5212093422539885765L;

	private PitanjeTableModel ptm = null;
	private JTable tablePitanje = null;
	private int selrow = -1;
	
	private JPanel panelLista;
	private JTextField textTocno;
	private JTextField textNetocno_1;
	private JTextField textNetocno_2;
	private JTextField textNetocno_3;
	private JLabel lblNaslov;
	private JTextField textUnosIdPitanja;
	private JTextArea textUnosPitanje;
	private JTextField textUnosTocno;
	private JTextField textUnosNetocno_1;
	private JTextField textUnosNetocno_2;
	private JTextField textUnosNetocno_3;
	private JComboBox<PodrucjeDto> cbxPodrucje;
	private JComboBox<PodrucjeDto> cbxUnosPodrucje;

	public PitanjaPanel(JTable tablePitanje) {
		
		//dohvati table model - koji je označavani red
		this.tablePitanje = tablePitanje;
		ptm = (PitanjeTableModel) tablePitanje.getModel();
		selrow = tablePitanje.getSelectedRow();

		//označavanje samo jednog reda i dodavanje listnera za označavanje reda
		tablePitanje.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablePitanje.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				dajOdgovoreZaPitanje(e);
			}
		});
		
		setLayout(new CardLayout(0, 0));
				
		JPanel panelTabela = new JPanel();
		add(panelTabela, "panelTabela");
		panelTabela.setLayout(new MigLayout("", "[100px][100px][100px][100px][100px]", "[30px][200px:300px:400px][25px][30px][25px][30px][30px][30px][30px][30px]"));
		
		JLabel lblPodrucje = new JLabel("Područje : ");
		panelTabela.add(lblPodrucje, "cell 0 0,alignx left,growy");
		lblPodrucje.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		cbxPodrucje = new JComboBox<PodrucjeDto>();
		cbxPodrucje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dohvatiPitanjaZaPodrucje();
			}
		});
		panelTabela.add(cbxPodrucje, "cell 1 0 4 1,grow");
		
		panelLista = new JPanel();
		panelTabela.add(panelLista, "cell 0 1 5 1,grow");
		panelLista.setLayout(new BorderLayout(0, 0));
		
		JLabel lblTocno = new JLabel("Točan odgovor");
		panelTabela.add(lblTocno, "cell 0 3 5 1,grow");
		lblTocno.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTocno.setOpaque(true);
		lblTocno.setBackground(Color.GREEN);
		
		textTocno = new JTextField();
		textTocno.setEditable(false);
		panelTabela.add(textTocno, "cell 0 4 5 1,grow");
		textTocno.setColumns(10);
		
		JLabel lblNetocno = new JLabel("Pogrešni odgovori");
		panelTabela.add(lblNetocno, "cell 0 5 5 1,grow");
		lblNetocno.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNetocno.setOpaque(true);
		lblNetocno.setBackground(new Color(250, 128, 114));
		
		textNetocno_1 = new JTextField();
		textNetocno_1.setEditable(false);
		panelTabela.add(textNetocno_1, "cell 0 6 5 1,grow");
		textNetocno_1.setColumns(10);
		
		textNetocno_2 = new JTextField();
		textNetocno_2.setEditable(false);
		panelTabela.add(textNetocno_2, "cell 0 7 5 1,grow");
		textNetocno_2.setColumns(10);
		
		textNetocno_3 = new JTextField();
		textNetocno_3.setEditable(false);
		panelTabela.add(textNetocno_3, "cell 0 8 5 1,grow");
		textNetocno_3.setColumns(10);
		
		JButton btnNovi = new JButton("Novi unos");
		panelTabela.add(btnNovi, "cell 3 9,grow");
		btnNovi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				novi();
			}
		});
		
		JButton btnIzmjena = new JButton("Izmjeni");
		panelTabela.add(btnIzmjena, "cell 4 9,grow");
		btnIzmjena.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				izmjena();
			}
		});
		
		JPanel panelUnos = new JPanel();
		add(panelUnos, "name_2522695508400");
		panelUnos.setLayout(new MigLayout("", "[grow][grow][grow][grow]", "[][][][][][][][][][][]"));
		
		lblNaslov = new JLabel("Naslov");
		lblNaslov.setHorizontalAlignment(SwingConstants.CENTER);
		lblNaslov.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelUnos.add(lblNaslov, "cell 0 0 4 1,alignx center");
		
		JLabel lblUnosPodrucja = new JLabel("Područje :");
		lblUnosPodrucja.setFont(new Font("Tahoma", Font.BOLD, 12));
		panelUnos.add(lblUnosPodrucja, "cell 0 1,alignx left");
		
		cbxUnosPodrucje = new JComboBox<PodrucjeDto>();
		panelUnos.add(cbxUnosPodrucje, "cell 1 1 3 1,growx");
		
		textUnosIdPitanja = new JTextField();
		textUnosIdPitanja.setVisible(false);
		textUnosIdPitanja.setEditable(false);
		textUnosIdPitanja.setHorizontalAlignment(SwingConstants.RIGHT);
		panelUnos.add(textUnosIdPitanja, "cell 1 2,growx");
		
		textUnosPitanje = new JTextArea();
		textUnosPitanje.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textUnosPitanje.setLineWrap(true);
		textUnosPitanje.setTabSize(2);
		textUnosPitanje.setColumns(40);
		textUnosPitanje.setRows(5);
		panelUnos.add(textUnosPitanje, "cell 0 3 4 1,grow");
		
		JLabel lblUnosTocno = new JLabel("Točan odgovor");
		lblUnosTocno.setBackground(Color.GREEN);
		lblUnosTocno.setOpaque(true);
		lblUnosTocno.setFont(new Font("Tahoma", Font.BOLD, 12));
		panelUnos.add(lblUnosTocno, "cell 0 4 4 1,grow");
		
		textUnosTocno = new JTextField();
		textUnosTocno.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panelUnos.add(textUnosTocno, "cell 0 5 4 1,grow");
		textUnosTocno.setColumns(25);
		
		JLabel lblUnosNetocno = new JLabel("Pogrešni odgovori");
		lblUnosNetocno.setBackground(new Color(255, 99, 71));
		lblUnosNetocno.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblUnosNetocno.setOpaque(true);
		panelUnos.add(lblUnosNetocno, "cell 0 6 4 1,grow");
		
		textUnosNetocno_1 = new JTextField();
		textUnosNetocno_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panelUnos.add(textUnosNetocno_1, "cell 0 7 4 1,growx");
		textUnosNetocno_1.setColumns(25);
		
		textUnosNetocno_2 = new JTextField();
		textUnosNetocno_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panelUnos.add(textUnosNetocno_2, "cell 0 8 4 1,growx");
		textUnosNetocno_2.setColumns(25);
		
		textUnosNetocno_3 = new JTextField();
		textUnosNetocno_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panelUnos.add(textUnosNetocno_3, "cell 0 9 4 1,growx");
		textUnosNetocno_3.setColumns(25);
		
		JLabel lblPitanje = new JLabel("Pitanje :");
		lblPitanje.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPitanje.setHorizontalAlignment(SwingConstants.LEFT);
		panelUnos.add(lblPitanje, "flowx,cell 0 2,alignx left,aligny center");
		
		JButton btnOdustani = new JButton("Odustani");
		btnOdustani.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnOdustani.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showNext();
			}
		});
		
		JButton btnPrihvati = new JButton("Prihvati");
		btnPrihvati.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnPrihvati.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				azuriranjePitanja();
			}
		});
		panelUnos.add(btnPrihvati, "cell 2 10,grow");
		panelUnos.add(btnOdustani, "cell 3 10,grow");
	}

	protected void dajOdgovoreZaPitanje(ListSelectionEvent e) {
		selrow = tablePitanje.getSelectedRow();
		System.out.println("tablePitanje valueChanged selrow=" + selrow + ", getValueIsAdjusting=" + e.getValueIsAdjusting());
		if (selrow > -1 && e.getValueIsAdjusting() == false) {
			System.out.println("tablePitanje " + e.getSource().toString());
			try {
				Integer idPitanja = ptm.getPitanjeDto(selrow).getIdPitanja();
				PitanjeManager manager = new PitanjeManager();
				List<OdgovorDto> lista = manager.dajListuOdgovoraNaPitanje(idPitanja);
				for (OdgovorDto dto : lista) {
					if (dto.isTocno()) {
						textTocno.setText(dto.getTekst());
					} else if (dto.getRedniBroj() == 2) {
						textNetocno_1.setText(dto.getTekst());
					} else if (dto.getRedniBroj() == 3) {
						textNetocno_2.setText(dto.getTekst());
					} else if (dto.getRedniBroj() == 4) {
						textNetocno_3.setText(dto.getTekst());
					}
				}
			} catch (Exception err) {
				err.printStackTrace();
				String message = "Error message [" + err.getMessage() + "]";
				JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		return;
	}

	protected void dohvatiPitanjaZaPodrucje() {
		try {
			PodrucjeDto cbx = (PodrucjeDto) cbxPodrucje.getSelectedItem();
			System.out.println("cbxPodrucje, actionPerformed:" + cbx);
			PitanjeManager mng = new PitanjeManager();
			List<PitanjeDto> lst = mng.dajListuPitanja(cbx.getIdPodrucja());
			ptm.setListaPitanja(lst);
		} catch (Exception err) {
			err.printStackTrace();
			String message = "Error message [" + err.getMessage() + "]";
			JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
		}
		return;
	}

	protected void novi() {
		lblNaslov.setText("Unos novog pitanja i odgovora");
		cbxUnosPodrucje.setSelectedItem(cbxPodrucje.getSelectedItem());
		textUnosIdPitanja.setText("");
		textUnosPitanje.setText("");
		textUnosTocno.setText("");
		textUnosNetocno_1.setText("");
		textUnosNetocno_2.setText("");
		textUnosNetocno_3.setText("");
		showNext();
		return;
	}

	protected void izmjena() {
		lblNaslov.setText("Izmjena pitanja i odgovora");
		selrow = tablePitanje.getSelectedRow();
		if (selrow == -1) {
			String info = "Za ovu radnju morate označiti jedan red";
			JOptionPane.showMessageDialog(null, info, "Info", JOptionPane.INFORMATION_MESSAGE);
		} else {
			PitanjeDto dto = ptm.getPitanjeDto(selrow);
			cbxUnosPodrucje.setSelectedItem(dto.getPodrucjeDto());
			textUnosIdPitanja.setText(Integer.toString(dto.getIdPitanja()));
			textUnosPitanje.setText(dto.getTekst());
			textUnosTocno.setText(textTocno.getText());
			textUnosNetocno_1.setText(textNetocno_1.getText());
			textUnosNetocno_2.setText(textNetocno_2.getText());
			textUnosNetocno_3.setText(textNetocno_3.getText());
			showNext();
		}				
		return;
	}

	protected void azuriranjePitanja() {
		try {
			PitanjeManager manager = new PitanjeManager();
			PodrucjeDto podrdto = (PodrucjeDto) getCbxUnosPodrucje().getSelectedItem();
			if (podrdto.getIdPodrucja().intValue() == -1) {
				throw new Exception("Niste odabrali područje!");
			}
			
			PitanjeDto dto = new PitanjeDto();
			dto.setIdPodrucja(podrdto.getIdPodrucja());
			dto.setTekst(provjeraUnosa("Pitanje", textUnosPitanje.getText(), 130));
			String[] odgovori = { 
					provjeraUnosa("Točan odgovor", textUnosTocno.getText(), 50), 
					provjeraUnosa("Netočan odgovor 1", textUnosNetocno_1.getText(), 50), 
					provjeraUnosa("Netočan odgovor 2", textUnosNetocno_2.getText(), 50), 
					provjeraUnosa("Netočan odgovor 3", textUnosNetocno_3.getText(), 50)
			};

			System.out.println(dto);
			System.out.println(odgovori);
			
			boolean noviUnos = (lblNaslov.getText().startsWith("Unos")) ? true : false;
			if (noviUnos) {
				manager.novi(dto, odgovori);
				ptm.addPitanjeDto(dto);
			} else {
				dto.setIdPitanja(Integer.parseInt(textUnosIdPitanja.getText()));
				manager.izmjeni(dto, odgovori);
				ptm.setPitanjeDto(dto);
			}
			
			textTocno.setText("");
			textNetocno_1.setText("");
			textNetocno_2.setText("");
			textNetocno_3.setText("");
			
			showNext();
			
		} catch (Exception err) {
			String message = "Error message [" + err.getMessage() + "]";
			System.err.println(message);
			JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
		}
		return;
	}

	/**
	 * Provjera unosa pitanja i odgovora. Provjeravam duljinu i specijalne znakove
	 * (carriage return i tab)
	 * @param labela labelu koju prikazujemo uz eventualnu grešku
	 * @param text tekst koji kontroliramo
	 * @param duljina maksimalna duljina teksta
	 * @return očišćeni tekst
	 * @throws Exception ispis greške
	 */
	protected String provjeraUnosa(String labela, String text, int duljina) throws Exception {
		String unos = (text == null) ? "" : text.trim().replaceAll("\n", " ");
		unos = unos.replaceAll("\t", " ");
		if (unos.length() == 0) {
			throw new Exception(labela + " nema unos");
		} else if (unos.length() > duljina) {
			throw new Exception(labela + " ima duljinu veću od dozvoljene (" + duljina + " znakova)");
		}
		return unos;
	}

	public JPanel getPanelLista() {
		return panelLista;
	}
	
	private void showNext() {
		CardLayout layout = (CardLayout) this.getLayout();			
		layout.next(this);
		return;
	}
	

	public JComboBox<PodrucjeDto> getCbxPodrucje() {
		return cbxPodrucje;
	}
	
	public JComboBox<PodrucjeDto> getCbxUnosPodrucje() {
		return cbxUnosPodrucje;
	}
	
}
