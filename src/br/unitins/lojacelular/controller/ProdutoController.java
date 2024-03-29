package br.unitins.lojacelular.controller;

import java.io.Serializable;
import java.sql.SQLException;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.lojacelular.application.Session;
import br.unitins.lojacelular.application.Util;
import br.unitins.lojacelular.dao.*;
import br.unitins.lojacelular.model.*;

@Named
@ViewScoped
public class ProdutoController implements Serializable{

	private static final long serialVersionUID = 2605891871600272264L;
	
	private Produto produto;
	
	public ProdutoController() {
		
		Flash flash = FacesContext.
				getCurrentInstance().
				getExternalContext().getFlash();
		flash.keep("produtoFlash");
		
		produto = (Produto) flash.get("produtoFlash");
	}
	
	public void incluir() {
		
		Usuario usuario = (Usuario) Session.getInstance().getAttribute("usuarioLogado");

		if (usuario == null) {
			
			Util.addMessageWarn("Eh preciso estar logado para cadastrar. Fa�a o Login!!");
			return;
		}
		
		if(usuario.getPerfil().getValue() != 1) {
			
			Util.addMessageError("Somente administradores podem realizar cadastro!");
			return;
		}
		
		DAO<Produto> dao = new ProdutoDAO();
		
		// faz a inclusao no banco de dados
		try {
			
			dao.create(getProduto());
			dao.getConnection().commit();
			Util.addMessageInfo("Inclus�o realizada com sucesso.");
			limpar();
		} 
		
		catch (SQLException e) {
			
			dao.rollbackConnection();
			dao.closeConnection();
			Util.addMessageInfo("Erro ao incluir o Produto no Banco de Dados.");
			e.printStackTrace();
		}
	}

	public void alterar() {
		
		Usuario usuario = (Usuario) Session.getInstance().getAttribute("usuarioLogado");

		if (usuario == null) {
			
			Util.addMessageWarn("Eh preciso estar logado para alterar. Fa�a o Login!!");
			return;
		}
		
		if(usuario.getPerfil().getValue() != 1) {
			
			Util.addMessageError("Somente administradores podem alterar cadastro!");
			return;
		}
		
		DAO<Produto> dao = new ProdutoDAO();
		
		// faz a alteracao no banco de dados
		try {
			
			dao.update(getProduto());
			dao.getConnection().commit();
			Util.addMessageInfo("Altera��o realizada com sucesso.");
			limpar();
			Util.redirect("consultaproduto.xhtml");
		} 
		
		catch (SQLException e) {
			
			dao.rollbackConnection();
			dao.closeConnection();
			Util.addMessageInfo("Erro ao alterar o Produto no Banco de Dados.");
			e.printStackTrace();
			Util.redirect("consultaproduto.xhtml");
		}
	}
	
	public void excluir() {
		
		Usuario usuario = (Usuario) Session.getInstance().getAttribute("usuarioLogado");

		if (usuario == null) {
			
			Util.addMessageWarn("Eh preciso estar logado para deletar. Fa�a o Login!!");
			return;
		}
		
		if(usuario.getPerfil().getValue() != 1) {
			
			Util.addMessageError("Somente administradores podem excluir cadastro!");
			return;
		}

		DAO<Produto> dao = new ProdutoDAO();
		
		// faz a exclusao no banco de dados
		try {
			
			dao.delete(getProduto().getId());
			dao.getConnection().commit();
			Util.addMessageInfo("Exclus�o realizada com sucesso.");
			limpar();
		}
		
		catch (SQLException e) {
			
			dao.rollbackConnection();
			Util.addMessageInfo("Erro ao excluir o Produto no Banco de Dados.");
			e.printStackTrace();
		} 
		
		finally {
			
			dao.closeConnection();
			Util.redirect("consultaproduto.xhtml");
		}
	}

	public Produto getProduto() {
		
		if (produto == null) {
			
			produto = new Produto();
		}
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	public void limpar() {
		produto = null;
	}
	
	public MarcaProduto[] getListaMarcaProduto() {
		return MarcaProduto.values();
	}
	
	public CorProduto[] getListaCorProduto() {
		return CorProduto.values();
	}
	
}