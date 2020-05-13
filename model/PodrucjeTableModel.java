package hr.kviz.model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import hr.kviz.dto.PodrucjeDto;

public class PodrucjeTableModel extends AbstractTableModel {

	private static final long serialVersionUID = -5512902716080348944L;

	private List<PodrucjeDto> listaPodrucja;

	private final String[] columnNames = new String[] { 
		"ID", "Područje"
	};
	
    private final Class<?>[] columnClass = new Class[] {
            Integer.class, String.class
        };
	
	public PodrucjeTableModel(List<PodrucjeDto> listaPodrucja) {
		this.listaPodrucja = listaPodrucja;
	}

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
	
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnClass[columnIndex];
    }
 
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return (listaPodrucja == null) ? 0 : listaPodrucja.size();
	}

	@Override
	public Object getValueAt(int redIndex, int kolIndeks) {
		PodrucjeDto dto = getPodrucjeDto(redIndex);
		Object value = null;
		if (kolIndeks == 0) {
			value = dto.getIdPodrucja();
		} else if (kolIndeks == 1) {
			value = dto.getOpis();
		}
		return value;
	}

	public PodrucjeDto getPodrucjeDto(int redIndeks) {
		return listaPodrucja.get(redIndeks);
	}
	
	public void setPodrucjeDto(PodrucjeDto dto) {
		int redIndex = listaPodrucja.indexOf(dto);
		listaPodrucja.set(redIndex, dto);
		fireTableRowsUpdated(redIndex, redIndex);
		return;
	}

	public void addPodrucjeDto(PodrucjeDto dto) {
		listaPodrucja.add(dto);
		fireTableDataChanged();
		return;
	}


}
