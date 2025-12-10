package com.azienda.jdbcEsercizio.businessLogic;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.azienda.jdbcEsercizio.dao.AutomobileDao;
import com.azienda.jdbcEsercizio.model.Automobile;

public class AutomobileServices {
	private AutomobileDao autoDao;
	private Connection con;
	public AutomobileServices(AutomobileDao autoDao, Connection con) {
		super();
		this.autoDao = autoDao;
		this.con = con;
	}
	public void insertAuto(String marca, String modello , String colore, Float cilindrata, String targa) throws Exception {
		try {
			// controllo campi
			if(marca == null || marca.isEmpty() ||
					modello == null || modello.isEmpty() ||
					colore == null || colore.isEmpty() || 
					cilindrata == null || targa == null || targa.isEmpty()) {
				throw new Exception("Tutti i campi devono essere valorizzati.");
			}
			// creo auto senza id
			Automobile auto = new Automobile(null, marca, modello, colore, cilindrata, targa);
			//controllo duplicati
			if(autoDao.exists(auto)) {
				throw new Exception("Esiste già un'automobile con questi dati.");
			}
			// calcolo id = max(id) +1
			int nuovoId = autoDao.getMaxId() + 1;
			auto.setId(nuovoId);
			// inserimento db
			autoDao.create(auto);
			con.commit();
		} catch (Exception e) {
			con.rollback();
			throw e;
		}
	}
	public void deleteAuto(String prefisso) throws SQLException{
		try {
			autoDao.delete(prefisso);
			con.commit();
		} catch (Exception e) {
			con.rollback();
			throw e;
		}
	}
	public void updateTargaByMarcaPrefix(String prefisso, String nuovaTarga) throws Exception {
	    try {
	        autoDao.update(prefisso, nuovaTarga);
	        con.commit();
	    } catch (Exception e) {
	        con.rollback();
	        throw e;
	    }
	}
	public List<Automobile> getAll() throws Exception {
	    return autoDao.read();
	}

}
