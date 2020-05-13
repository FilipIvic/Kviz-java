package hr.kviz.manager;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import hr.kviz.dto.OdgovorDto;
import hr.kviz.dto.PitanjeCompareDto;
import hr.kviz.dto.PitanjeDto;
import hr.kviz.sql.OdgovorSql;
import hr.kviz.sql.PitanjeSql;
import hr.kviz.sql.PostgreConn;
import hr.kviz.sql.KvizSqlUtil;

public class PitanjeManager {

	private Connection conn;

	public List<PitanjeDto> dajListuPitanja(int idPodrucja) throws Exception {
		List<PitanjeDto> lista = null;
		try {
			conn = PostgreConn.dajKonekciju();
			PitanjeSql pitanjesql = new PitanjeSql(conn);
			if (idPodrucja < 0) {
				lista = pitanjesql.dajListuPitanja(); 
			} else {
				lista = pitanjesql.dajListuPitanja(idPodrucja); 
			}
		} catch (Exception e) {
			throw new Exception("dajListuPitanja, err=" + e.getMessage());
		} finally {
			KvizSqlUtil.close(conn);
		}
		return lista;
	}

	public List<OdgovorDto> dajListuOdgovoraNaPitanje(int idPitanja) {
		System.out.println("AdminManager dajListuOdgovoraNaPitanje idPitanja=" + idPitanja);
		List<OdgovorDto> lista = null;
		try {
			conn = PostgreConn.dajKonekciju();
			OdgovorSql sql = new OdgovorSql(conn);
			lista = sql.dajListuOdgovoraNaPitanje(idPitanja);
		} catch (Exception e) {
			System.out.println("OdgovorDto, err=" + e.getMessage());
			lista = new ArrayList<OdgovorDto>();
		} finally {
			KvizSqlUtil.close(conn);
		}
		return lista;
	}
	
	public void novi(PitanjeDto dto, String[] odgovori) {
		try {
			conn = PostgreConn.dajKonekciju();
			PitanjeSql sql = new PitanjeSql(conn);
			dto.setIdPitanja(sql.dajMaxIdPitanja());
			
			List<OdgovorDto> listaOdgovora = dajOdgovore(dto, odgovori);
			sql.novi(dto, listaOdgovora);
			
		} catch (Exception e) {
			System.out.println("novi, err=" + e.getMessage());			
		} finally {
			KvizSqlUtil.close(conn);
		}
		return;
	}

	public void izmjeni(PitanjeDto dto, String[] odgovori) {
		try {
			conn = PostgreConn.dajKonekciju();
			PitanjeSql sql = new PitanjeSql(conn);
			sql.izmjeni(dto, dajOdgovore(dto, odgovori));
		} catch (Exception e) {
			System.out.println("izmjeni, err=" + e.getMessage());			
		} finally {
			KvizSqlUtil.close(conn);
		}
		return;
	}

	private List<OdgovorDto> dajOdgovore(PitanjeDto dto, String[] odgovori) {
		List<OdgovorDto> lista = new ArrayList<OdgovorDto>();
		int rbr = 0;
		for (String tekst : odgovori) {
			OdgovorDto odgdto = new OdgovorDto();
			odgdto.setIdPitanja(dto.getIdPitanja());
			odgdto.setRedniBroj(++rbr);
			odgdto.setTekst(tekst);
			lista.add(odgdto);
		}
		return lista;
	}

	public List<PitanjeCompareDto> dajSvaPitanjaSaOdgovorima() throws Exception {
		List<PitanjeCompareDto> lista = null;
		try {
			conn = PostgreConn.dajKonekciju();
			PitanjeSql pitanjesql = new PitanjeSql(conn);
			lista = pitanjesql.dajSvaPitanjaSaOdgovorima();
		} catch (Exception e) {
			throw new Exception("dajSvaPitanjaSaOdgovorima, err=" + e.getMessage());
		} finally {
			KvizSqlUtil.close(conn);
		}
		return lista;
	}
	
	
}
