package br.unitins.lojacelular.controller;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.lojacelular.application.Util;
import br.unitins.lojacelular.dao.*;
import br.unitins.lojacelular.model.*;

@Named
@ViewScoped
public class UsuarioController implements Serializable {

	private static final long serialVersionUID = 6832268115971206550L;

	private Usuario usuario;

	private List<Usuario> listaUsuario;

	public Usuario getUsuario() {
		
		if (usuario == null) {
			usuario = new Usuario();
			usuario.setTelefone(new Telefone());
			usuario.setEndereco(new Endereco());
		}
		return usuario;
	}
	
	public void incluir() {
		
		if (validarDados()) {
			
			DAO<Usuario> dao = new UsuarioDAO();
			// faz a inclusao no banco de dados
			
			try {
				
				dao.create(getUsuario());
				dao.getConnection().commit();
				Util.addMessageInfo("Inclus�o realizada com sucesso.");
				limpar();
				listaUsuario = null;
			} 
			
			catch (SQLException e) {
				
				dao.rollbackConnection();
				dao.closeConnection();
				Util.addMessageInfo("Erro ao incluir o Usu�rio no Banco de Dados.");
				e.printStackTrace();
			}
		}
	}
	
	public void alterar() {
		
		if (validarDados()) {
			
			DAO<Usuario> dao = new UsuarioDAO();
			// faz a alteracao no banco de dados
			
			try {
				
				dao.update(getUsuario());
				dao.getConnection().commit();
				Util.addMessageInfo("Altera��o realizada com sucesso.");
				limpar();
				listaUsuario = null;
			} 
			
			catch (SQLException e) {
				
				dao.rollbackConnection();
				dao.closeConnection();
				Util.addMessageInfo("Erro ao alterar o Usu�rio no Banco de Dados.");
				e.printStackTrace();
			}
		}
	}
	
	public void excluir() {
		
		if (excluir(getUsuario()))
			limpar();
	}
	
	public boolean excluir(Usuario usuario) {
		
		DAO<Usuario> dao = new UsuarioDAO();
		
		// faz a exclusao no banco de dados
		if (dao.delete(usuario.getId())) {
			
			Util.addMessageInfo("Exclus�o realizada com sucesso.");
			listaUsuario = null;
			return true;
		} 
		
		else {
			Util.addMessageInfo("Erro ao excluir o Usu�rio no Banco de Dados.");
			return false;
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
		UsuarioDAO dao = new UsuarioDAO();
		// buscando um usuario pelo id
		Usuario usu = dao.findId(usuario.getId());
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
