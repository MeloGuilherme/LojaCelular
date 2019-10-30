package br.unitins.lojacelular.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.unitins.lojacelular.model.CorProduto;
import br.unitins.lojacelular.model.MarcaProduto;
import br.unitins.lojacelular.model.Produto;

public class ProdutoDAO extends DAO<Produto> {

	public ProdutoDAO(Connection conn) {
		super(conn);
	}

	public ProdutoDAO() {
		// tchê papai ... cria uma nova conexao
		super(null);
	}

	@Override
	public void create(Produto produto) throws SQLException {
		
		Connection conn = getConnection();

		PreparedStatement stat = conn.prepareStatement(
				"INSERT INTO " +
			    "public.produto " +
			    " (nome, descricao, preco, marcaproduto, corproduto) " +
				"VALUES " +
			    " (?, ?, ?, ?, ?) ");
		stat.setString(1, produto.getNome());
		stat.setString(2, produto.getDescricao());
		stat.setDouble(3, produto.getPreco());
		stat.setInt(4, produto.getMarcaProduto().getValue());
		stat.setInt(5, produto.getCorProduto().getValue());

		stat.execute();
	}

	@Override
	public void update(Produto produto) throws SQLException {
		
		Connection conn = getConnection();

		PreparedStatement stat = conn.prepareStatement(
				"UPDATE public.produto SET " +
			    " nome = ?, " +
			    " descricao = ?, " +
			    " preco = ?, " +
			    " marcaproduto = ?, " +
			    " corproduto = ? " +
				"WHERE " +
			    " id = ? ");
		stat.setString(1, produto.getNome());
		stat.setString(2, produto.getDescricao());
		stat.setDouble(3, produto.getPreco());
		stat.setInt(4, produto.getMarcaProduto().getValue());
		stat.setInt(5, produto.getCorProduto().getValue());
		stat.setInt(6, produto.getId());

		stat.execute();
	}

	@Override
	public boolean delete(int id) {
		
		Connection  conn = getConnection();
		if (conn == null) 
			return false;

		try {
			PreparedStatement stat = conn.prepareStatement(
					"DELETE FROM public.produto WHERE id = ?");
			stat.setInt(1, id);

			stat.execute();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Produto> findAll() {
		
		Connection conn = getConnection();
		
		if (conn == null) 
			return null;

		try {
			PreparedStatement stat = conn.prepareStatement(
					"SELECT " +
					"  id, " +
					"  nome, " +
					"  descricao, " +
					"  preco, " +
					"  marcaproduto, " +
					"  corproduto " +
					"FROM " +
					"  public.produto ");
			ResultSet rs = stat.executeQuery();

			List<Produto> listaProduto = new ArrayList<Produto>();

			while(rs.next()) {
				Produto produto = new Produto();
				produto.setId(rs.getInt("id"));
				produto.setNome(rs.getString("nome"));
				produto.setDescricao(rs.getString("descricao"));
				produto.setPreco(rs.getDouble("preco"));
				produto.setMarcaProduto(MarcaProduto.valueOf(rs.getInt("marcaproduto")));
				produto.setCorProduto(CorProduto.valueOf(rs.getInt("corproduto")));

				listaProduto.add(produto);
			}

			if (listaProduto.isEmpty())
				return null;
			return listaProduto;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Produto> findByNome(String nome) {
		Connection conn = getConnection();
		if (conn == null) 
			return null;

		try {
			PreparedStatement stat = conn.prepareStatement(
					"SELECT " +
					"  id, " +
					"  nome, " +
					"  descricao, " +
					"  preco, " +
					"  marcaproduto, " +
					"  corproduto " +
					"FROM " +
					"  public.produto " +
					"WHERE " +
					"  nome ilike ? ");

			stat.setString(1, nome == null ? "%" : "%"+nome+"%");
			ResultSet rs = stat.executeQuery();

			List<Produto> listaProduto = new ArrayList<Produto>();

			while(rs.next()) {
				Produto produto = new Produto();
				produto.setId(rs.getInt("id"));
				produto.setNome(rs.getString("nome"));
				produto.setDescricao(rs.getString("descricao"));
				produto.setPreco(rs.getDouble("preco"));
				produto.setMarcaProduto(MarcaProduto.valueOf(rs.getInt("marcaproduto")));
				produto.setCorProduto(CorProduto.valueOf(rs.getInt("corproduto")));

				listaProduto.add(produto);
			}

			if (listaProduto.isEmpty())
				return null;
			return listaProduto;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Produto findById(Integer id) {
		Connection conn = getConnection();
		if (conn == null) 
			return null;

		try {
			PreparedStatement stat = conn.prepareStatement(
					"SELECT " +
					"  id, " +
					"  nome, " +
					"  descricao, " +
					"  valor " +
					"FROM " +
					"  public.produto " +
					"WHERE id = ? ");

			stat.setInt(1, id);

			ResultSet rs = stat.executeQuery();

			Produto produto = null;

			if(rs.next()) {
				produto = new Produto();
				produto.setId(rs.getInt("id"));
				produto.setNome(rs.getString("nome"));
				produto.setDescricao(rs.getString("descricao"));
				produto.setPreco(rs.getDouble("preco"));
				produto.setMarcaProduto(MarcaProduto.valueOf(rs.getInt("marcaproduto")));
				produto.setCorProduto(CorProduto.valueOf(rs.getInt("corproduto")));
			}

			return produto;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}