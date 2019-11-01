package br.unitins.lojacelular.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
//import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;
import java.util.List;

import br.unitins.lojacelular.model.Telefone;

public class TelefoneDAO extends DAO<Telefone> {

	public TelefoneDAO(Connection conn) {
		super(conn);
	}

	@Override
	public void create(Telefone telefone) throws SQLException {
		Connection conn = getConnection();
		
		PreparedStatement stat = conn.prepareStatement(
				"INSERT INTO " +
			    "public.telefone " +
			    " (id, codigoarea, numero) " +
				"VALUES " +
			    " (?, ?, ?) ");
		stat.setInt(1, telefone.getId());
		stat.setString(2, telefone.getCodigoArea());
		stat.setString(3, telefone.getNumero());
		
		stat.execute();
		stat.close();
			
	}

	@Override
	public void update(Telefone telefone) throws SQLException {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean delete(int id) throws SQLException {
		Connection conn = getConnection();
		
		PreparedStatement stat = conn.prepareStatement(
				"DELETE FROM public.usuario WHERE id = ?");
		
		stat.setInt(1, id);
		
		stat.execute();
		return false;
	}

	@Override
	public List<Telefone> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
