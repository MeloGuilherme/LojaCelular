package br.unitins.lojacelular.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.unitins.lojacelular.model.*;

public class EnderecoDAO extends DAO<Endereco> {

	public EnderecoDAO(Connection conn) {
		super(conn);
	}

	@Override
	public void create(Endereco endereco) throws SQLException {
		
		Connection conn = getConnection();

		PreparedStatement stat = conn.prepareStatement("INSERT INTO " + "public.endereco "
				+ " (id, cidade, estado, cep, logradouro) " + "VALUES " + " (?, ?, ?, ?, ?) ");

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
	public void delete(int id) throws SQLException {
		
		Connection conn = getConnection();

		PreparedStatement stat = conn.prepareStatement(
				"DELETE FROM public.endereco WHERE id =  ?");
		stat.setInt(1, id);

		stat.execute();
		stat.close();
	}

	@Override
	public List<Endereco> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public Endereco findById(Integer id) {

		Connection conn = getConnection();

		try {
			PreparedStatement stat = conn.prepareStatement(
					"SELECT " +
					"  id, " +
					"  cidade, " +
					"  estado, " +					
					"  cep, " +					
					"  logradouro " +					
					"FROM " +
					"  public.endereco " +
					"WHERE id = ? ");
			
			stat.setInt(1, id);

			ResultSet rs = stat.executeQuery();

			Endereco endereco = null;

			if (rs.next()) {
				endereco = new Endereco();
				endereco.setId(rs.getInt("id"));
				endereco.setCidade(rs.getString("cidade"));
				endereco.setEstado(rs.getString("estado"));
				endereco.setCep(rs.getString("cep"));
				endereco.setLogradouro(rs.getString("logradouro"));
			}

			return endereco;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
