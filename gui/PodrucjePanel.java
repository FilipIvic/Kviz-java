package hr.kviz.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import hr.kviz.dto.PodrucjeDto;
import hr.kviz.manager.PodrucjeManager;
import hr.kviz.model.PodrucjeTableModel;
import hr.kviz.util.KvizUtil;
import hr.kviz.util.KvizVarijable;
import net.miginfocom.swing.MigLayout;

public class PodrucjePanel extends JPanel {
	private static final long serialVersionUID = -4441329078093654369L;
	
	private PodrucjeTableModel ptm = null;
	private int selrow = -1;
	
	private JTextField textId;
	private JTextField textOpis;
	private JPanel panelLista;
	private JLabel lblNaslov;

	public PodrucjePanel(JTable tablePodrucje) {
		
		//dohvati table model - za označavanje reda
		tablePodrucje.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		KvizUtil.setColumnWidths(tablePodrucje, 50, 450);

		ptm = (PodrucjeTableModel) tablePodrucje.getModel();
		selrow = tablePodrucje.getSelectedRow();
		
		setLayout(new CardLayout(0, 0));
		
		JPanel panelPregled = new JPanel();
		add(panelPregled, "panelPregled");
		panelPregled.setLayout(new BorderLayout(0, 0));
		
		panelLista = new JPanel();
		panelLista.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Podru\u010Dja", TitledBorder.LEFT, TitledBorder.ABOVE_TOP, null, new Color(0, 0, 0)));
		panelPregled.add(panelLista, BorderLayout.CENTER);
		panelLista.setLayout(new BorderLayout(15, 15));
		
		JPanel panelIzbor = new JPanel();
		panelIzbor.setPreferredSize(new Dimension(125, 10));
		panelPregled.add(panelIzbor, BorderLayout.EAST);
		panelIzbor.setLayout(null);
		
		JButton btnNovi = new JButton("Novi unos");
		btnNovi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblNaslov.setText("Unos novog područja");
				textId.setText("");
				textOpis.setText("");
				showNext();
			}
		});
		btnNovi.setBounds(10, 11, 105, 23);
		panelIzbor.add(btnNovi);
		
		JButton btnIzmjena = new JButton("Izmjeni");
		btnIzmjena.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblNaslov.setText("Izmjena opisa područja");
				try {
					selrow = tablePodrucje.getSelectedRow();
					if (selrow == -1) {
						String info = "Za ovu radnju morate označiti jedan red";
						JOptionPane.showMessageDialog(null, info, "Info", JOptionPane.INFORMATION_MESSAGE);
					} else {
						PodrucjeDto dto = ptm.getPodrucjeDto(selrow);
						textId.setText(Integer.toString(dto.getIdPodrucja()));
						textOpis.setText(dto.getOpis());
						showNext();
					}
				} catch (Exception err) {
					String message = "Error message [" + err.getMessage() + "]";
					JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnIzmjena.setBounds(10, 45, 105, 23);
		panelIzbor.add(btnIzmjena);
		
		JPanel panelUnos = new JPanel();
		add(panelUnos, "name_2522695508400");
		panelUnos.setLayout(new MigLayout("", "[][50px,center][][grow][grow][]", "[][][][][][]"));
		
		lblNaslov = new JLabel("Naslov");
		lblNaslov.setHorizontalAlignment(SwingConstants.CENTER);
		lblNaslov.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelUnos.add(lblNaslov, "cell 1 0 5 1");
		
		JLabel lbld = new JLabel("ID :");
		panelUnos.add(lbld, "cell 1 2,alignx trailing");
		
		textId = new JTextField();
		textId.setHorizontalAlignment(SwingConstants.RIGHT);
		textId.setEditable(false);
		panelUnos.add(textId, "cell 2 2,alignx left");
		textId.setColumns(10);
		
		JLabel lblOpis = new JLabel("Opis :");
		panelUnos.add(lblOpis, "cell 1 3,alignx trailing");
		
		JButton btnPrihvati = new JButton("Prihvati");
		btnPrihvati.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					PodrucjeManager manager = new PodrucjeManager();					
					if (lblNaslov.getText().startsWith("Unos")) {
						PodrucjeDto dto = manager.novi(textOpis.getText());
						ptm.addPodrucjeDto(dto);
					} else {
						PodrucjeDto dto = new PodrucjeDto();
						dto.setIdPodrucja(Integer.parseInt(textId.getText()));
						dto.setOpis(textOpis.getText());
						manager.izmjeni(dto);
						ptm.setPodrucjeDto(dto);
					}			
					KvizVarijable.setNovoPodrucje(true);
					showNext();
				} catch (Exception err) {
					String message = "Error message [" + err.getMessage() + "]";
					JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		textOpis = new JTextField();
		panelUnos.add(textOpis, "cell 2 3 3 1,growx");
		textOpis.setColumns(10);
		panelUnos.add(btnPrihvati, "cell 2 5,growx");
		
		JButton btnOdustani = new JButton("Odustani");
		btnOdustani.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showNext();
			}
		});
		panelUnos.add(btnOdustani, "cell 4 5,growx");
	}

	public JPanel getPanelLista() {
		return panelLista;
	}
	
	private void showNext() {
		CardLayout layout = (CardLayout) this.getLayout();			
		layout.next(this);
		return;
	}
	

}
