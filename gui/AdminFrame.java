package hr.kviz.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dialog.ModalExclusionType;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import hr.kviz.manager.AdminManager;
import hr.kviz.util.KvizVarijable;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class AdminFrame extends JFrame {
	
	private static final long serialVersionUID = -1490104662026901515L;
	
	private JPanel panelPitanje;
	private JPanel panelPodrucje;
	private JPanel panelNatjecatelj;
	
	public AdminFrame() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setModalExclusionType(ModalExclusionType.TOOLKIT_EXCLUDE);
		setTitle("Kviz - administriranje");
		
		JTabbedPane tabbedPaneAdmin = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPaneAdmin, BorderLayout.CENTER);
		
		panelPitanje = new JPanel();
		panelPitanje.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				System.out.println("panelPitanje componentShown");
				if (KvizVarijable.isNovoPodrucje()) {
					System.out.println("panelPitanje novo područje");
					Component[] comps = panelPitanje.getComponents();
					for (Component component : comps) {
						if (component instanceof PitanjaPanel) {
							PitanjaPanel ppane = (PitanjaPanel) component;
							AdminManager manager = new AdminManager();
							manager.napuniComboBoxPodrucje(ppane);
							KvizVarijable.setNovoPodrucje(false);
							break;
						}
					}					
				}
				return;
			}
		});
		tabbedPaneAdmin.addTab("Pitanja", null, panelPitanje, null);
		
		panelPodrucje = new JPanel();
		tabbedPaneAdmin.addTab("Područja", null, panelPodrucje, null);
		
		//panelNatjecatelj = new JPanel();
		//tabbedPaneAdmin.addTab("Natjecatelji", null, panelNatjecatelj, null);

		AdminManager manager = new AdminManager();
		manager.prviPrikaz(panelPitanje, panelPodrucje, panelNatjecatelj);
		
		return;
	}

}
