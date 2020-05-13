package hr.kviz.test;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import hr.kviz.gui.RezultatPanel;
import hr.kviz.util.KvizUtil;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.border.MatteBorder;
import java.awt.Color;

public class JFrameTest extends JFrame {

	private static final long serialVersionUID = 1813590522008055245L;
	private JTable table;
	
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
	
	
	protected static void createAndShowGUI() {
		JFrameTest frame = new JFrameTest();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		KvizUtil.centreWindow(frame);
		frame.setVisible(true);
	}


	public JFrameTest() {
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				System.out.println("table focusGained");
			}
		});
		table.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				System.out.println("table propertyChange");
			}
		});
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("table mouseClicked");
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
			},
			new String[] {
				"New column", "New column"
			}
		));
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting() == false) {
					/*
					 * e.getSource() returns an object like this
					 * javax.swing.DefaultListSelectionModel 1052752867 ={11}
					 * where 11 is the index of selected element when mouse button is released
					 */
					String strSource= e.getSource().toString();
					int start = strSource.indexOf("{") + 1;
					int stop = strSource.length() - 1;
					System.out.println("source=" + strSource);
					System.out.println("start=" + start + ", stop=" + stop);
					System.out.println("selected=" + strSource.substring(start, stop));
				}
				return;
			}
		});
		
		
		scrollPane.setViewportView(table);
		
		Object[] obj = {" ", "x", "y", "z"};
		JComboBox<?> comboBox = new JComboBox<Object>(obj);
		comboBox.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				System.out.println("propertyChange " + arg0.toString());
			}
		});
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("x=" + arg0.getActionCommand());
				System.out.println("selected item=" + comboBox.getSelectedItem());
			}
		});
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				//System.out.println("itemStateChanged");
				System.out.println("itemStateChanged=" + arg0.getItem() + ", stateChanged " + arg0.getStateChange() );
			}
		});
		getContentPane().add(comboBox, BorderLayout.NORTH);
		
		JPanel panelTest = new JPanel();
		panelTest.setBorder(new MatteBorder(2, 2, 2, 2, (Color) Color.RED));
		panelTest.setPreferredSize(new Dimension(10, 250));
		getContentPane().add(panelTest, BorderLayout.SOUTH);
		panelTest.setLayout(new BorderLayout(0, 0));
		
		RezultatPanel rezultatPanel = new RezultatPanel();
		panelTest.add(rezultatPanel, BorderLayout.NORTH);
		
		
	}



}
