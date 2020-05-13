package hr.kviz.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hr.kviz.dto.PodrucjeDto;

public class PodrucjeSql {

	private Connection conn = null;

	public PodrucjeSql(Connection conn) {
		super();
		this.conn = conn;
	}

	public List<PodrucjeDto> dajListuPodrucja() throws SQLException {
		
		List<PodrucjeDto> lista = new ArrayList<PodrucjeDto>();
		
		String sql = "SELECT id_podrucja, opis "
				+ "FROM public.podrucje "
				+ "order by opis";
		
		Statement stm = conn.createStatement();
		ResultSet rs = stm.executeQuery(sql);
		while (rs.next()) {
			PodrucjeDto podr = new PodrucjeDto();
			podr.setIdPodrucja(rs.getInt("id_podrucja"));
			podr.setOpis(rs.getString("opis"));
			lista.add(podr);
		}
		return lista;
	}

	public int dajMaxIdPodrucja() throws SQLException {
		
		int maxId = 0;
		
		String sql = "SELECT MAX(id_podrucja) as maxid "
				+ "FROM public.podrucje ";
		
		Statement stm = conn.createStatement();
		ResultSet rs = stm.executeQuery(sql);
		if (rs.next()) {
			maxId = rs.getInt("maxid");
			maxId++;
		}
		return maxId;
	}

	public void novi(PodrucjeDto dto) throws SQLException {
		
		String sql = "INSERT INTO public.podrucje (id_podrucja, opis) " 
				+ "VALUES (?, ? )";
		
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, dto.getIdPodrucja());
		pst.setString(2, dto.getOpis());
		pst.executeUpdate();

		return;
	}

	public void izmjeni(PodrucjeDto dto) throws SQLException {
		
		String sql = "UPDATE podrucje set opis = ? "
				+ "WHERE id_podrucja = ? "; 				
		
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, dto.getOpis());
		pst.setInt(2, dto.getIdPodrucja());
		pst.executeUpdate();

		return;
		
	}
	
}
