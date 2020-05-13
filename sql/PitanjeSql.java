package hr.kviz.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import hr.kviz.dto.OdgovorCompareDto;
import hr.kviz.dto.OdgovorDto;
import hr.kviz.dto.PitanjeCompareDto;
import hr.kviz.dto.PitanjeDto;
import hr.kviz.util.SlucajniBroj;

public class PitanjeSql {

	private Connection conn = null;

	public PitanjeSql(Connection conn) {
		super();
		this.conn = conn;
	}

	public List<PitanjeDto> dajListuPitanja() throws SQLException {
		String sql = "SELECT p.id_pitanja, p.id_podrucja, p.tekst, po.opis "
				+ "FROM pitanje p "
				+ "JOIN podrucje po on po.id_podrucja = p.id_podrucja "
				+ "order by p.id_pitanja ";
		
		return listaPitanja(sql);
	}

	public List<PitanjeDto> dajListuPitanja(int idPodrucja) throws SQLException {
		String sql = "SELECT p.id_pitanja, p.id_podrucja, p.tekst, po.opis "
				+ "FROM pitanje p "
				+ "JOIN podrucje po on po.id_podrucja = p.id_podrucja "
				+ "WHERE p.id_podrucja = " + idPodrucja
				+ "order by p.id_pitanja ";
		
		return listaPitanja(sql);
	}
	
	
	private List<PitanjeDto> listaPitanja(String sql) throws SQLException {
		List<PitanjeDto> lista = new ArrayList<PitanjeDto>();
		Statement stm = null;
		try {
			stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				PitanjeDto dto = new PitanjeDto();
				dto.setIdPitanja(rs.getInt("id_pitanja"));
				dto.setIdPodrucja(rs.getInt("id_podrucja"));
				dto.setTekst(rs.getString("tekst"));
				dto.setOpisPodrucja(rs.getString("opis"));
				lista.add(dto);
			}			
		} catch (Exception e) {
			throw new SQLException("sql error:" + e.getMessage());
		} finally {
			KvizSqlUtil.close(stm);
		}
		return lista;
	}

	public Integer dajMaxIdPitanja() throws SQLException {
		int maxIdPitanja = 0;
		String sql = "SELECT MAX(id_pitanja) as maxid "
				+ "FROM public.pitanje ";
		
		Statement stm = conn.createStatement();
		ResultSet rs = stm.executeQuery(sql);
		if (rs.next()) {
			maxIdPitanja = rs.getInt("maxid");
		}
		maxIdPitanja++;
		return maxIdPitanja;
	}

	public void novi(PitanjeDto dto, List<OdgovorDto> listaOdgovora) throws SQLException {
		String sql = "INSERT INTO public.pitanje(" + 
				"id_pitanja, id_podrucja, tekst) " + 
				"VALUES (?, ?, ?);";

		PreparedStatement pst = null;
		try {
			conn.setAutoCommit(false);
			
			pst = conn.prepareStatement(sql);
			pst.setInt(1, dto.getIdPitanja());
			pst.setInt(2, dto.getIdPodrucja());
			pst.setString(3, dto.getTekst());
			pst.executeUpdate();
			
			OdgovorSql odgsql = new OdgovorSql(conn);
			odgsql.novi(dto.getIdPitanja(), listaOdgovora);
			
			conn.commit();
			conn.setAutoCommit(true);
		} catch (Exception e) {
			conn.rollback();
			conn.setAutoCommit(true);
			throw new SQLException("SQL error=" + e.getMessage());
		} finally {
			if (pst != null) {
				try {
					pst.close();
				} catch (SQLException ignore) {	}
			}
		}
		return;
	}

	public void izmjeni(PitanjeDto dto, List<OdgovorDto> listaOdgovora) throws SQLException {
		String sql = "UPDATE public.pitanje " + 
				"SET id_podrucja=?, tekst=? " + 
				"WHERE id_pitanja = ?";
		
		PreparedStatement pst = null;
		try {
			conn.setAutoCommit(false);
			
			pst = conn.prepareStatement(sql);
			pst.setInt(1, dto.getIdPodrucja());
			pst.setString(2, dto.getTekst());
			pst.setInt(3, dto.getIdPitanja());
			pst.executeUpdate();
			
			OdgovorSql odgsql = new OdgovorSql(conn);
			odgsql.izmjeni(dto.getIdPitanja(), listaOdgovora);
			
			conn.commit();
			conn.setAutoCommit(true);
		} catch (Exception e) {
			conn.rollback();
			conn.setAutoCommit(true);
			throw new SQLException("SQL error=" + e.getMessage());
		} finally {
			KvizSqlUtil.close(pst);
		}
		return;
		
	}

	public List<PitanjeCompareDto> dajSvaPitanjaSaOdgovorima() throws SQLException {
		
		List<PitanjeCompareDto> lista = new ArrayList<>(); 
		List<PitanjeDto> tmplista = dajListuPitanja();

		String sql = "SELECT redni_broj, tekst "
				+ "FROM odgovor "
				+ "WHERE id_pitanja = ? "
				+ "order by redni_broj ";

		SlucajniBroj slbroj = new SlucajniBroj();
		PreparedStatement pst = null;
		try {
			ResultSet rs = null;
			pst = conn.prepareStatement(sql);
			for (PitanjeDto dto : tmplista) {
				List<OdgovorCompareDto> listaOdgovora = new ArrayList<>();
				
				pst.setInt(1, dto.getIdPitanja());
				rs = pst.executeQuery();
				while (rs.next()) {
					OdgovorCompareDto odgovor = new OdgovorCompareDto();
					odgovor.setRedniBroj(rs.getInt("redni_broj"));
					odgovor.setTekst(rs.getString("tekst"));
					odgovor.setCompareId(slbroj.nextBD());
					listaOdgovora.add(odgovor);
				}
				Collections.sort(listaOdgovora);
				
				PitanjeCompareDto pitanje = new PitanjeCompareDto();
				pitanje.setIdPitanja(dto.getIdPitanja());
				pitanje.setTekst(dto.getTekst());
				pitanje.setOpisPodrucja(dto.getOpisPodrucja());
				pitanje.setCompareId(slbroj.nextBD());
				pitanje.setListaOdgovora(listaOdgovora);
				lista.add(pitanje);
			}
			Collections.sort(lista);
			
		} catch (Exception e) {
			throw new SQLException("SQL error=" + e.getMessage());
		} finally {
			KvizSqlUtil.close(pst);
		}
		return lista;
	}
		
	public List<OdgovorDto> dajListuOdgovoraNaPitanje(Integer idPitanja) throws SQLException {
		
		List<OdgovorDto> lista = new ArrayList<OdgovorDto>();
		
		String sql = "SELECT redni_broj, tekst "
				+ "FROM odgovor "
				+ "WHERE id_pitanja = " + idPitanja
				+ "order by redni_broj ";
		
		Statement stm = conn.createStatement();
		ResultSet rs = stm.executeQuery(sql);
		while (rs.next()) {
			OdgovorDto dto = new OdgovorDto();
			dto.setIdPitanja(idPitanja);
			dto.setRedniBroj(rs.getInt("redni_broj"));
			dto.setTekst(rs.getString("tekst"));
			lista.add(dto);
		}
		return lista;
	}
	
	
}
