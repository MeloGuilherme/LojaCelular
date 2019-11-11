package br.unitins.lojacelular.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.lojacelular.model.*;
import br.unitins.lojacelular.application.*;

@Named
@ViewScoped
public class MenuController implements Serializable {

	private static final long serialVersionUID = 6528753431580396919L;

	private Usuario usuarioLogado;

	public Usuario getUsuarioLogado() {
		
		if (usuarioLogado == null) {
			
			// buscando o usuario da sessao
			usuarioLogado = (Usuario) Session.getInstance().getAttribute("usuarioLogado");
			
			if (usuarioLogado == null)
				usuarioLogado = new Usuario();
		}
		
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		
		this.usuarioLogado = usuarioLogado;
	}

	public void encerrarSessao() {

		Session.getInstance().invalidateSession();
		Util.redirect("login.xhtml");
	}

}
