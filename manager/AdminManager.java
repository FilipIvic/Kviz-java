package hr.kviz.manager;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import hr.kviz.dto.PitanjeDto;
import hr.kviz.dto.PodrucjeDto;
import hr.kviz.gui.PitanjaPanel;
import hr.kviz.gui.PodrucjePanel;
import hr.kviz.model.PitanjeTableModel;
import hr.kviz.model.PodrucjeTableModel;
import hr.kviz.sql.PitanjeSql;
import hr.kviz.sql.PodrucjeSql;
import hr.kviz.sql.PostgreConn;
import hr.kviz.sql.KvizSqlUtil;
import hr.kviz.util.KvizVarijable;

public class AdminManager {

	private Connection conn;
	
	public void prviPrikaz(JPanel panelPitanje, JPanel panelPodrucje, JPanel panelNatjecatelj) {
		try {
			conn = PostgreConn.dajKonekciju();
			
			PitanjeSql pitanjesql = new PitanjeSql(conn);
			List<PitanjeDto> listaPitanja = pitanjesql.dajListuPitanja(); 
			
			PodrucjeSql podrsql = new PodrucjeSql(conn);
			List<PodrucjeDto> listaPodrucja = podrsql.dajListuPodrucja();
			
			prviPrikazPitanja(panelPitanje, listaPitanja, listaPodrucja);
			prviPrikazPodrucja(panelPodrucje, listaPodrucja);
			prviPrikazNatjecatelja(panelNatjecatelj);
			
		} catch (Exception e) {
			e.printStackTrace();
			String message = "Error message [" + e.getMessage() + "]";
			JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
		} finally {
			KvizSqlUtil.close(conn);
		}
		return;
	}

	private void prviPrikazPitanja(JPanel panelPitanje, List<PitanjeDto> listaPitanja, List<PodrucjeDto> listaPodrucja) {
		
		PitanjeTableModel tm = new PitanjeTableModel(listaPitanja);
		JTable table = new JTable(tm);
		JScrollPane scrpane = new JScrollPane(table);
		PitanjaPanel ppane = new PitanjaPanel(table);

		napuniComboBoxPodrucje(ppane, listaPodrucja);
		
		ppane.getPanelLista().add(scrpane, BorderLayout.CENTER);
		panelPitanje.add(ppane);
		
		return;
	}

	private void napuniComboBoxPodrucje(PitanjaPanel panelPitanje, List<PodrucjeDto> listaPodrucja) {
		
		JComboBox<PodrucjeDto> cbx = panelPitanje.getCbxPodrucje();
		JComboBox<PodrucjeDto> cbxUnos = panelPitanje.getCbxUnosPodrucje();

		ActionListener al = cbx.getActionListeners()[0];
		cbx.removeActionListener(al);
		cbx.removeAllItems();
		cbxUnos.removeAllItems();
		
		PodrucjeDto prazno = KvizVarijable.dajPraznoPodrucjeDto();
		cbx.addItem(prazno);
		cbxUnos.addItem(prazno);
		for (PodrucjeDto dto : listaPodrucja) {
			cbx.addItem(dto);
			cbxUnos.addItem(dto);
		}
		
		cbx.addActionListener(al);
		
		return;
	}

	private void prviPrikazPodrucja(JPanel panelPodrucje, List<PodrucjeDto> listaPodrucja) {
		
		PodrucjeTableModel tm = new PodrucjeTableModel(listaPodrucja);
		JTable table = new JTable(tm);
		JScrollPane scrpane = new JScrollPane(table);
		
		PodrucjePanel ppane = new PodrucjePanel(table);
		ppane.getPanelLista().add(scrpane, BorderLayout.CENTER);
		panelPodrucje.add(ppane);
		
		return;
	}

	private void prviPrikazNatjecatelja(JPanel panelNatjecatelj) {
		// TODO Auto-generated method stub
		
	}

	public void napuniComboBoxPodrucje(PitanjaPanel panelPitanje) {
		try {
			conn = PostgreConn.dajKonekciju();
			PodrucjeSql podrsql = new PodrucjeSql(conn);
			List<PodrucjeDto> listaPodrucja = podrsql.dajListuPodrucja();
			
			napuniComboBoxPodrucje(panelPitanje, listaPodrucja);
			
		} catch (Exception e) {
			e.printStackTrace();
			String message = "Error message [" + e.getMessage() + "]";
			JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
		} finally {
			KvizSqlUtil.close(conn);
		}
	}
			
}
