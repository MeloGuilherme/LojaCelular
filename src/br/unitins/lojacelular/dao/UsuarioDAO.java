package br.unitins.lojacelular.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.unitins.lojacelular.model.Endereco;
import br.unitins.lojacelular.model.Perfil;
import br.unitins.lojacelular.model.Telefone;
import br.unitins.lojacelular.model.Usuario;

public class UsuarioDAO extends DAO<Usuario> {

	public UsuarioDAO(Connection conn) {
		super(conn);
	}

	public UsuarioDAO() {
		// tch� papai ... cria uma nova conexao
		super(null);
	}
	
	public Usuario login(String login, String senha) {
		
		Connection conn = getConnection();
		
		try {
			PreparedStatement stat = conn.prepareStatement(
					"SELECT " +
					"  id, " +
					"  nome, " +
					"  login, " +
					"  senha, " +
					"  ativo, " +
					"  perfil " +					
					"FROM " +
					"  public.usuario " +
					"WHERE login = ? AND senha = ? ");
			
			stat.setString(1, login);
			stat.setString(2, senha);
			
			ResultSet rs = stat.executeQuery();
			
			Usuario usuario = null;
			
			if(rs.next()) {
				usuario = new Usuario();
				usuario.setTelefone(new Telefone());
				usuario.setEndereco(new Endereco());
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setAtivo(rs.getBoolean("ativo"));
				usuario.setPerfil(Perfil.valueOf(rs.getInt("perfil")));
			}
			
			return usuario;
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}

	@Override
	public void create(Usuario usuario) throws SQLException {
		
		Connection conn = getConnection();
			
		PreparedStatement stat = conn.prepareStatement(
				"INSERT INTO " +
			    "public.usuario " +
			    " (nome, login, senha, ativo, perfil) " +
				"VALUES " +
			    " (?, ?, ?, ?, ?) ", Statement.RETURN_GENERATED_KEYS);
		stat.setString(1, usuario.getNome());
		stat.setString(2, usuario.getLogin());
		stat.setString(3, usuario.getSenha());
		stat.setBoolean(4, usuario.getAtivo());
		stat.setInt(5, usuario.getPerfil().getValue());
		
		stat.execute();
		
		// obtendo o id gerado pela tabela do banco de dados
		ResultSet rs = stat.getGeneratedKeys();
		rs.next();
		
		usuario.getTelefone().setId(rs.getInt("id"));
		usuario.getEndereco().setId(rs.getInt("id"));
		
		TelefoneDAO telDao = new TelefoneDAO(conn);
		telDao.create(usuario.getTelefone());
		
		EnderecoDAO endDao = new EnderecoDAO(conn);
		endDao.create(usuario.getEndereco());
			
	}

	@Override
	public void update(Usuario usuario) throws SQLException {
		
		Connection conn = getConnection();
		
		PreparedStatement stat = conn.prepareStatement(
				"UPDATE public.usuario SET " +
			    " nome = ?, " +
			    " login = ?, " +
			    " senha = ?, " +
			    " ativo = ?, " +
			    " perfil = ? " +
				"WHERE " +
			    " id = ? ");
		stat.setString(1, usuario.getNome());
		stat.setString(2, usuario.getLogin());
		stat.setString(3, usuario.getSenha());
		stat.setBoolean(4, usuario.getAtivo());
		stat.setInt(5, usuario.getPerfil().getValue());
		stat.setInt(6, usuario.getId());
		
		usuario.getEndereco().setId(usuario.getId());
		usuario.getTelefone().setId(usuario.getId());
		
		TelefoneDAO telDao = new TelefoneDAO(conn);
		EnderecoDAO endDao = new EnderecoDAO(conn);
		
		telDao.update(usuario.getTelefone());
		endDao.update(usuario.getEndereco());
			
		stat.execute();
	}
	
	@Override
	public void delete(int id) throws SQLException {
		
		Connection conn = getConnection();
		
		TelefoneDAO telDao = new TelefoneDAO(conn);
		EnderecoDAO endDao = new EnderecoDAO(conn);
		
		telDao.delete(id);
		endDao.delete(id);
		
		PreparedStatement stat = conn.prepareStatement(
				"DELETE FROM public.usuario WHERE id = ?");
		
		stat.setInt(1, id);
		
		stat.execute();
	}
	
	@Override
	public List<Usuario> findAll() {
		
		Connection conn = getConnection();
		
		if (conn == null) 
			return null;
		
		try {
			PreparedStatement stat = conn.prepareStatement(
					"SELECT " +
					"  id, " +
					"  nome, " +
					"  login, " +
					"  senha, " +
					"  ativo, " +
					"  perfil " +					
					"FROM " +
					"  public.usuario ");
			ResultSet rs = stat.executeQuery();
			
			List<Usuario> listaUsuario = new ArrayList<Usuario>();
			
			while(rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setAtivo(rs.getBoolean("ativo"));
				usuario.setPerfil(Perfil.valueOf(rs.getInt("perfil")));
				
				listaUsuario.add(usuario);
			}
			
			if (listaUsuario.isEmpty())
				return null;
			return listaUsuario;
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Usuario findId(Integer id) {
		
		Connection conn = getConnection();
		
		if (conn == null) 
			return null;
		
		try {
			PreparedStatement stat = conn.prepareStatement(
					"SELECT " +
					"  id, " +
					"  nome, " +
					"  login, " +
					"  senha, " +
					"  ativo, " +
					"  perfil " +					
					"FROM " +
					"  public.usuario " +
					"WHERE id = ? ");
			
			stat.setInt(1, id);
			
			ResultSet rs = stat.executeQuery();
			
			Usuario usuario = null;
			
			if(rs.next()) {
				usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setAtivo(rs.getBoolean("ativo"));
				usuario.setPerfil(Perfil.valueOf(rs.getInt("perfil")));
				
				TelefoneDAO telDao = new TelefoneDAO(conn);
				usuario.setTelefone(telDao.findById(usuario.getId()));
				// caso o retorno do telefone seja nulo, instanciar um telefone
				if (usuario.getTelefone() == null)
					usuario.setTelefone(new Telefone());
				
				EnderecoDAO endDao = new EnderecoDAO(conn);
				usuario.setEndereco(endDao.findById(usuario.getId()));
				// caso o retorno do endereco seja nulo, instanciar um endereco
				if (usuario.getEndereco() == null)
					usuario.setEndereco(new Endereco());
			}
			
			return usuario;
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Usuario> findByNome(String nome) {
		
		Connection conn = getConnection();
		
		if (conn == null) 
			return null;

		try {
			PreparedStatement stat = conn.prepareStatement(
					"SELECT " +
					"  id, " +
					"  nome, " +
					"  login, " +
					"  senha, " +
					"  ativo, " +
					"  perfil " +	
					"FROM " +
					"  public.usuario " +
					"WHERE " +
					"  nome ilike ? ");

			stat.setString(1, nome == null ? "%" : "%"+nome+"%");
			ResultSet rs = stat.executeQuery();

			List<Usuario> listaUsuario = new ArrayList<Usuario>();

			while(rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setTelefone(new Telefone());
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setAtivo(rs.getBoolean("ativo"));
				usuario.setPerfil(Perfil.valueOf(rs.getInt("perfil")));

				listaUsuario.add(usuario);
			}

			if (listaUsuario.isEmpty())
				return null;
			return listaUsuario;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Usuario findById(Integer id) {
		
		Connection conn = getConnection();
		
		if (conn == null) 
			return null;

		try {
			PreparedStatement stat = conn.prepareStatement(
					"SELECT " +
					"  id, " +
					"  nome, " +
					"  login, " +
					"  senha, " +
					"  ativo, " +
					"  perfil " +	
					"FROM " +
					"  public.usuario " +
					"WHERE id = ? ");

			stat.setInt(1, id);

			ResultSet rs = stat.executeQuery();

			Usuario usuario = null;

			if(rs.next()) {
				
				usuario = new Usuario();
				usuario.setTelefone(new Telefone());
				usuario.setEndereco(new Endereco());
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setAtivo(rs.getBoolean("ativo"));
				usuario.setPerfil(Perfil.valueOf(rs.getInt("perfil")));
			}

			return usuario;

		} 
		
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Usuario findAllById(Integer id) {
		
		Connection conn = getConnection();
		
		if (conn == null) 
			return null;
			
			try {
				PreparedStatement stat = conn.prepareStatement(
						"SELECT " +
								"  id, " +
								"  nome, " +
								"  login, " +
								"  senha, " + 
								"  ativo, " + 
								"  perfil " + 
								"FROM " + 
								"  public.usuario " + 
								"WHERE id = ? ");
				
				stat.setInt(1, id);
			
			ResultSet rs = stat.executeQuery();
			
			Usuario usuario = null;
			
			if(rs.next()) {
				
				usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setAtivo(rs.getBoolean("ativo"));
				usuario.setPerfil(Perfil.valueOf(rs.getInt("perfil")));
				
				TelefoneDAO telDao = new TelefoneDAO(conn);
				usuario.setTelefone(telDao.findById(usuario.getId()));
				// caso o retorno do telefone seja nulo, instanciar um telefone
				if (usuario.getTelefone() == null)
					usuario.setTelefone(new Telefone());
				
				EnderecoDAO endDao = new EnderecoDAO(conn);
				usuario.setEndereco(endDao.findById(usuario.getId()));
				// caso o retorno do telefone seja nulo, instanciar um telefone
				if (usuario.getEndereco() == null)
					usuario.setEndereco(new Endereco());
			}
			
			return usuario;
			
		} 
		
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
