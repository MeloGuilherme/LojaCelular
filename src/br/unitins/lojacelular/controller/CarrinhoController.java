package br.unitins.lojacelular.controller;

import java.io.Serializable;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.lojacelular.model.*;
import br.unitins.lojacelular.application.*;
import br.unitins.lojacelular.dao.*;

@Named
@ViewScoped
public class CarrinhoController implements Serializable {

	private static final long serialVersionUID = -6905700390714994638L;

	private Venda venda;

	public Venda getVenda() {
		if (venda == null)
			venda = new Venda();

		// obtendo o carrinho da sessao
		List<ItemVenda> carrinho = (ArrayList<ItemVenda>) Session.getInstance().getAttribute("carrinho");

		// adicionando os itens do carrinho na venda
		if (carrinho == null)
			carrinho = new ArrayList<ItemVenda>();
		venda.setListaItemVenda(carrinho);

		return venda;
	}

	public void remover(int idProduto) {
	
		List<ItemVenda> lista = (ArrayList<ItemVenda>) Session.getInstance().getAttribute("carrinho");
		lista.remove(idProduto);
	}

	public void finalizar() {
		Usuario usuario = (Usuario) Session.getInstance().getAttribute("usuarioLogado");
		if (usuario == null) {
			Util.addMessageWarn("Eh preciso estar logado para realizar uma venda. Faca o Login!!");
			return;
		}
		
		List<ItemVenda> carrinho = (ArrayList<ItemVenda>) Session.getInstance().getAttribute("carrinho");
		
		if(carrinho == null) {
			
			Util.addMessageError("Insira produtos no carrinho!!!");
			return;
		}
		
		// montar a venda
		Venda venda = new Venda();
		venda.setData(LocalDate.now());
		venda.setUsuario(usuario);
		
		venda.setListaItemVenda(carrinho);
		
		// salvar no banco
		VendaDAO dao = new VendaDAO();
		try {
			dao.create(venda);
			dao.getConnection().commit();
			Util.addMessageInfo("Venda realizada com sucesso.");
			// limpando o carrinho
			Session.getInstance().setAttribute("carrinho", null);
		} catch (SQLException e) {
			dao.rollbackConnection();
			dao.closeConnection();
			Util.addMessageInfo("Erro ao finalizar a Venda.");
			e.printStackTrace();
		}

	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

}
