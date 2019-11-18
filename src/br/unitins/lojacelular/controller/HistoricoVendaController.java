package br.unitins.lojacelular.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.lojacelular.application.*;
import br.unitins.lojacelular.model.*;
import br.unitins.lojacelular.dao.*;

@Named
@ViewScoped
public class HistoricoVendaController implements Serializable {

	private static final long serialVersionUID = -5945580520022602692L;

	private List<Venda> listaVenda = null;

	public List<Venda> getListaVenda() {

		if (listaVenda == null) {
			VendaDAO dao = new VendaDAO();
			Usuario usuario = (Usuario) Session.getInstance().getAttribute("usuarioLogado");
			listaVenda = dao.findByUsuario(usuario.getId());
			if (listaVenda == null)
				listaVenda = new ArrayList<Venda>();
			dao.closeConnection();
		}
		return listaVenda;
	}

	public void detalhes(Venda venda) {

		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("detalheVenda", venda);

		Util.redirect("detalhesvenda.xhtml");
	}

}