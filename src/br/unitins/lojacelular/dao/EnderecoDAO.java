package br.unitins.lojacelular.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import br.unitins.lojacelular.model.Endereco;

public class EnderecoDAO extends DAO<Endereco>{

	public EnderecoDAO(Connection conn) {
		super(conn);
	}

	@Override
	public void create(Endereco endereco) throws SQLException {
		Connection conn = getConnection();
		
		PreparedStatement stat = conn.prepareStatement(
				"INSERT INTO " +
			    "public.endereco " +
			    " (id, cidade, estado, cep, logradouro) " +
				"VALUES " +
			    " (?, ?, ?, ?, ?) ");
		
		stat.setInt(1, endereco.getId());
		stat.setString(2, endereco.getCidade());
		stat.setString(3, endereco.getEstado());
		stat.setString(4, endereco.getCep());
		stat.setString(5, endereco.getLogradouro());
		
		stat.execute();
		stat.close();
	}

	@Override
	public void update(Endereco endereco) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Endereco> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
