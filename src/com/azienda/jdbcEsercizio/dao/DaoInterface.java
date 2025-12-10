package com.azienda.jdbcEsercizio.dao;

import java.sql.SQLException;
import java.util.List;

public interface DaoInterface<T> {
	public void create(T ref) throws SQLException;
	public List<T> read() throws SQLException;
	public void update(String prefisso, String nuovaTarga) throws SQLException; 
	public void delete(String prefisso) throws SQLException;
}
