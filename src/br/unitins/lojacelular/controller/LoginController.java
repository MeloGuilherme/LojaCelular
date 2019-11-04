package br.unitins.lojacelular.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.unitins.lojacelular.application.Util;
import br.unitins.lojacelular.dao.*;
import br.unitins.lojacelular.model.*;

@Named
@RequestScoped
public class LoginController {

	private Usuario usuario;

	public void logar() {
		
		UsuarioDAO dao = new UsuarioDAO();
		
		String hashSenha = Util.gerarHash(getUsuario().getSenha());
		
		Usuario usuario = dao.login(getUsuario().getLogin(), hashSenha);
		
		if (usuario != null) {
			
			Util.redirect("menu.xhtml");
		}
		
		else {
			
			Util.addMessageError("Usuário ou Senha Inválido.");
		}
	}
	
	public void limpar() {
		setUsuario(new Usuario());
//		usuario = new Usuario();
	}

	public Usuario getUsuario() {
		if (usuario == null)
			usuario = new Usuario();
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
