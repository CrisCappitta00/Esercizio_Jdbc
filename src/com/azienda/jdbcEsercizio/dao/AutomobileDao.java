package com.azienda.jdbcEsercizio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.azienda.jdbcEsercizio.model.Automobile;

public class AutomobileDao implements DaoInterface<Automobile>{
	private Connection con;
	public AutomobileDao(Connection con) {
		super();
		this.con = con;
	}
	// max id per il campo id
	public int getMaxId() throws SQLException {
		String sql = "select max(id) from automobile";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			return rs.getInt(1);
		}
		return 0;
		
	};
	public boolean exists(Automobile ref) throws SQLException {
		String sql = "select count(*) from automobile where marca = ? and modello = ? and colore = ? and cilindrata = ? and targa = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, ref.getMarca());
		ps.setString(2, ref.getModello());
		ps.setString(3, ref.getColore());
		ps.setFloat(4, ref.getCilindrata());
		ps.setString(5, ref.getTarga());
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			return rs.getInt(1) > 0;
		}
		return false;
	};

	@Override
	public void create(Automobile ref) throws SQLException {
		String sql = "insert into automobile(id,marca,modello,colore,cilindrata,targa) values(?,?,?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, ref.getId());
		ps.setString(2, ref.getMarca());
		ps.setString(3, ref.getModello());
		ps.setString(4, ref.getColore());
		ps.setFloat(5, ref.getCilindrata());
		ps.setString(6, ref.getTarga());
		ps.executeUpdate();
	}
	@Override
	public List<Automobile> read() throws SQLException {
		List<Automobile> auto = new ArrayList<>();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select * from automobile");
		while (rs.next()) {
			Integer id = rs.getInt("id"); // usare la stringa
			String marca = rs.getString("marca");
			String modello = rs.getString("modello");
			String colore = rs.getString("colore");
			Float cilindrata = rs.getFloat("cilindrata");
			String targa = rs.getString("targa");
			Automobile a = new Automobile(id, marca, modello, colore, cilindrata,targa);
			auto.add(a);
		}
		return auto;
	}
	@Override
	public void update(String prefisso, String nuovaTarga) throws SQLException {
		String sql = "update automobile set targa = ? where marca like ?";
	    PreparedStatement ps = con.prepareStatement(sql);
	    ps.setString(1, nuovaTarga);
	    ps.setString(2, prefisso + "%");
	    ps.executeUpdate();
	}
	@Override
	public void delete(String prefisso) throws SQLException {
		String sql = "delete from automobile where marca like ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, prefisso + "%");
		ps.executeUpdate();
	}

}
