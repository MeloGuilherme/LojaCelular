package br.unitins.lojacelular.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.lojacelular.application.Util;
import br.unitins.lojacelular.dao.*;
import br.unitins.lojacelular.model.*;

@Named
@ViewScoped
public class ConsultaProdutoController implements Serializable{

	private static final long serialVersionUID = -955924933425674663L;
	
	private String nome;
	
	private List<Produto> listaProduto = null;
	
	public void pesquisar() {
		
		listaProduto = null;
	}
	
	public void editar(int id) {
		
		ProdutoDAO dao = new ProdutoDAO();
		
		// buscando um usuario pelo id
		Produto produto = dao.findById(id);
		
		Flash flash = FacesContext.
				getCurrentInstance().
				getExternalContext().getFlash();
		
		flash.put("produtoFlash", produto);
		
		Util.redirect("produto.xhtml");
	}

	public List<Produto> getListaProduto() {
		
		if (listaProduto == null) {
			
			ProdutoDAO dao = new ProdutoDAO();
			
			listaProduto = dao.findByNome(getNome());
			
			if (listaProduto == null)
				listaProduto = new ArrayList<Produto>();
			
			dao.closeConnection();
		}
		return listaProduto;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}