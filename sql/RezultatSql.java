package hr.kviz.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hr.kviz.dto.RezultatDto;

public class RezultatSql {
	
	private Connection conn = null;

	public RezultatSql(Connection conn) {
		super();
		this.conn = conn;
	}

	public List<RezultatDto> dajListuTop10() throws SQLException {
		List<RezultatDto> lista = new ArrayList<RezultatDto>();
		String sql = 
				"SELECT r.id_rezultata, r.id_natjecatelja, n.ime, r.vrijeme, r.broj_pitanja, r.broj_tocnih, r.bodova " + 
				"from rezultat r " + 
				"join natjecatelj n on r.id_natjecatelja = n.id_natjecatelja " + 
				"order by r.bodova desc, r.vrijeme desc " + 
				"fetch first 10 rows only";
		
		int poredak = 1;
		Statement stm = null;
		try {
			stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				RezultatDto rezdto = new RezultatDto();
				rezdto.setPoredak(poredak++);
				rezdto.setIdRezultata(rs.getInt("id_rezultata"));
				rezdto.setIdNatjecatelja(rs.getInt("id_natjecatelja"));
				rezdto.setIme(rs.getString("ime"));
				rezdto.setVrijeme(rs.getString("vrijeme"));
				rezdto.setBrojPitanja(rs.getInt("broj_pitanja"));
				rezdto.setBrojTocnih(rs.getInt("broj_tocnih"));
				rezdto.setBodova(rs.getInt("bodova"));
				lista.add(rezdto);
			}							
		} catch (Exception e) {
			throw new SQLException("sql error:" + e.getMessage());
		} finally {
			KvizSqlUtil.close(stm);
		}
		return lista;
	}

	public void unosRezultata(RezultatDto rezdto) throws SQLException {
		String sql = "INSERT INTO rezultat(id_rezultata, id_natjecatelja, vrijeme, broj_pitanja, broj_tocnih, bodova) " + 
				"VALUES (?, ?, CURRENT_TIMESTAMP, ?, ?, ?)";
		
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, rezdto.getIdRezultata());
			pst.setInt(2, rezdto.getIdNatjecatelja());
			pst.setInt(3, rezdto.getBrojPitanja());
			pst.setInt(4, rezdto.getBrojTocnih());
			pst.setInt(5, rezdto.getBodova());
			pst.executeUpdate();
		} catch (Exception e) {
			throw new SQLException("sql error:" + e.getMessage());
		} finally {
			KvizSqlUtil.close(pst);
		}
		return;
	}
	
	public Integer dajMaxIdRezultata() throws SQLException {
		int maxIdRezultat = 0;
		String sql = "SELECT MAX(id_rezultata) as maxid "
				+ "FROM rezultat ";
		Statement stm = conn.createStatement();
		ResultSet rs = stm.executeQuery(sql);
		if (rs.next()) {
			maxIdRezultat = rs.getInt("maxid");
		}
		maxIdRezultat++;
		return maxIdRezultat;
	}
	
	
	public int dajPoredak(int bodova) throws SQLException {
		int pozicija = 0;
		String sql = "SELECT count(*) as broj "
				+ "FROM rezultat WHERE bodova > " + bodova;
		Statement stm = null;
		try {
			stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			if (rs.next()) {
				pozicija = rs.getInt("broj");
			}
			pozicija++;
		} catch (Exception e) {
			throw new SQLException("sql error:" + e.getMessage());
		} finally {
			KvizSqlUtil.close(stm);
		}
		return pozicija;
	}
	
	/**
	 * Tiomestamp formata "2020-01-23 19:00:35.017925+01"
	 * @return
	 * @throws SQLException
	 */
	public String dajTimestamp() throws SQLException {
		String vrijeme = null;
		String sql = "SELECT current_timestamp as vrijeme";
		Statement stm = null;
		try {
			stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			if (rs.next()) {
				vrijeme = rs.getString("vrijeme");
				int duljina = vrijeme.length();
				if (duljina > 10) {
					vrijeme = vrijeme.substring(0, duljina - 3);
				}
			}
		} catch (Exception e) {
			throw new SQLException("sql error:" + e.getMessage());
		} finally {
			KvizSqlUtil.close(stm);
		}
		return vrijeme;
	}
	
}
