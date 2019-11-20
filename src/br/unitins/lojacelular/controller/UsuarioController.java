package br.unitins.lojacelular.controller;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.lojacelular.application.*;
import br.unitins.lojacelular.dao.*;
import br.unitins.lojacelular.model.*;

@Named
@ViewScoped
public class UsuarioController implements Serializable {

	private static final long serialVersionUID = 6832268115971206550L;

	private Usuario usuario;

	private List<Usuario> listaUsuario;
	
	public UsuarioController() {
		
		Flash flash = FacesContext.
				getCurrentInstance().
				getExternalContext().getFlash();
		flash.keep("usuarioFlash");

		usuario = (Usuario) flash.get("usuarioFlash");
		
	}

	public Usuario getUsuario() {
		
		if (usuario == null) {
			usuario = new Usuario();
			usuario.setTelefone(new Telefone());
			usuario.setEndereco(new Endereco());
		}
		return usuario;
	}
	
	public void incluir() {
		
		Usuario usuario = (Usuario) Session.getInstance().getAttribute("usuarioLogado");

		if (usuario == null) {
			Util.addMessageWarn("Eh preciso estar logado para cadastrar. Faça o Login!!");
			return;
		}
		
		if(usuario.getPerfil().getValue() != 1) {
			
			Util.addMessageError("Somente administradores podem realizar cadastro!");
			return;
		}
		
		if (validarDados()) {
			
			DAO<Usuario> dao = new UsuarioDAO();
			
			// faz a inclusao no banco de dados
			try {
				
				getUsuario().setSenha(Util.gerarHash(getUsuario().getSenha()));
				
				dao.create(getUsuario());
				dao.getConnection().commit();
				Util.addMessageInfo("Inclusão realizada com sucesso.");
				limpar();
				listaUsuario = null;
			} 
			
			catch (SQLException e) {
				
				dao.rollbackConnection();
				dao.closeConnection();
				Util.addMessageInfo("Erro ao incluir o Usuário no Banco de Dados.");
				e.printStackTrace();
			}
		}
	}
	
	public void alterar() {
		
		Usuario usu = (Usuario) Session.getInstance().getAttribute("usuarioLogado");

		if (usu == null) {
			
			Util.addMessageWarn("Eh preciso estar logado para alterar. Faça o Login!!");
			return;
		}
		
		if(usu.getPerfil().getValue() != 1) {
			
			Util.addMessageError("Somente administradores podem alterar cadastro!");
			return;
		}
		
		if (validarDados()) {
			
			DAO<Usuario> dao = new UsuarioDAO();
			
			// faz a alteracao no banco de dados
			try {
				
				getUsuario().setSenha(Util.gerarHash(getUsuario().getSenha()));

				dao.update(getUsuario());
				dao.getConnection().commit();
				Util.addMessageInfo("Alteração realizada com sucesso.");
				limpar();
				listaUsuario = null;
			} 
			
			catch (SQLException e) {
				
				dao.rollbackConnection();
				dao.closeConnection();
				Util.addMessageInfo("Erro ao alterar o Usuário no Banco de Dados.");
				e.printStackTrace();
			}
		}
	}
	
	public void excluir() {
		
		if (excluir(getUsuario()))
			limpar();
	}
	
	public boolean excluir(Usuario usuario) {
		
		Usuario usu = (Usuario) Session.getInstance().getAttribute("usuarioLogado");

		if (usu == null) {
			Util.addMessageWarn("Eh preciso estar logado para excluir. Faça o Login!!");
			return false;
		}
		
		if(usu.getPerfil().getValue() != 1) {
			
			Util.addMessageError("Somente administradores podem excluir cadastro!");
			return false;
		}

		DAO<Usuario> dao = new UsuarioDAO();

		try {

			dao.delete(usuario.getId());
			dao.getConnection().commit();
			Util.addMessageInfo("Exclusão realizada com sucesso.");
			listaUsuario = null;
			return true;
		}

		catch (Exception e) {

			dao.rollbackConnection();
			Util.addMessageInfo("Erro ao excluir seu Usuário no Banco de Dados.");
			e.printStackTrace();
			return false;
		}

		finally {
			
			dao.closeConnection();
		}
	}

	private boolean validarDados() {

		if (getUsuario().getSenha().isBlank()) {
			Util.addMessageWarn("O campo senha deve ser informado.");
			return false;
		}

		return true;
	}

	public void editar(Usuario usuario) {
		
		Usuario u = (Usuario) Session.getInstance().getAttribute("usuarioLogado");

		if (u == null) {
			Util.addMessageWarn("Eh preciso estar logado para alterar. Faça o Login!!");
			return;
		}
		
		if(u.getPerfil().getValue() != 1) {
			
			Util.addMessageError("Somente administradores podem alterar cadastro!");
			return;
		}
		
		UsuarioDAO dao = new UsuarioDAO();
		
		// buscando um usuario pelo id
		Usuario usu = dao.findAllById(usuario.getId());
		setUsuario(usu);
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getListaUsuario() {
		if (listaUsuario == null) {
			DAO<Usuario> dao = new UsuarioDAO();
			listaUsuario = dao.findAll();
			if (listaUsuario == null)
				listaUsuario = new ArrayList<Usuario>();
		}
		return listaUsuario;
	}

	public void limpar() {
		usuario = null;
	}

	public Perfil[] getListaPerfil() {
		return Perfil.values();
	}

}
