package br.unitins.lojacelular.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
public class ConsultaUsuarioController implements Serializable {

	private static final long serialVersionUID = -2637853489631964630L;

	private String nome;

	private List<Usuario> listaUsuario = null;

	public List<Usuario> getListaUsuario() {

		Usuario usu = (Usuario) Session.getInstance().getAttribute("usuarioLogado");

		if (listaUsuario == null) {

			if (usu.getPerfil().getValue() != 1 || usu.getPerfil().getValue() != 2) {

				Util.addMessageError("Somente administradores e funcionários podem listar!");
				listaUsuario = new ArrayList<Usuario>();
			}

			else {

				UsuarioDAO dao = new UsuarioDAO();
				listaUsuario = dao.findByNome(getNome());
				if (listaUsuario == null)
					listaUsuario = new ArrayList<Usuario>();
				dao.closeConnection();
			}
		}

		return listaUsuario;
	}

	public void pesquisar() {

		Usuario usuario = (Usuario) Session.getInstance().getAttribute("usuarioLogado");

		if (usuario == null) {
			
			Util.addMessageWarn("Eh preciso estar logado para alterar. Faça o Login!!");
			return;
		}

		if (usuario.getPerfil().getValue() != 1 || usuario.getPerfil().getValue() != 2) {

			Util.addMessageError("Somente administradores e funcionários podem pesquisar usuários!");
			return;
		}

		listaUsuario = null;
	}

	public void editar(int id) {

		Usuario usu = (Usuario) Session.getInstance().getAttribute("usuarioLogado");

		if (usu == null) {
			
			Util.addMessageWarn("Eh preciso estar logado para alterar. Faça o Login!!");
			return;
		}

		if (usu.getPerfil().getValue() != 1) {

			Util.addMessageError("Somente administradores podem alterar cadastro!");
			return;
		}

		UsuarioDAO dao = new UsuarioDAO();

		Usuario usuario = dao.findAllById(id);

		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("usuarioFlash", usuario);

		Util.redirect("usuario.xhtml");
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}