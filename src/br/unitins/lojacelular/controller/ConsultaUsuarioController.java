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
public class ConsultaUsuarioController implements Serializable {

	private static final long serialVersionUID = -2637853489631964630L;

	private String nome;

	private List<Usuario> listaUsuario = null;

	public List<Usuario> getListaUsuario() {
		
		if (listaUsuario == null) {
			
			UsuarioDAO dao = new UsuarioDAO();
			listaUsuario = dao.findByNome(getNome());
			if (listaUsuario == null)
				listaUsuario = new ArrayList<Usuario>();
			dao.closeConnection();
		}

		return listaUsuario;
	}

	public void pesquisar() {
		listaUsuario = null;
	}

	public void editar(int id) {
		
		UsuarioDAO dao = new UsuarioDAO();
		
		Usuario usuario = dao.findById(id);
		
//		System.out.println(usuario.getNome());
//		System.out.println(usuario.getLogin());
//		System.out.println(usuario.getSenha());
//		System.out.println(usuario.getAtivo());
//		System.out.println(usuario.getDataNasc());
//		System.out.println(usuario.getEndereco().getCidade());
//		System.out.println(usuario.getEndereco().getCep());
//		System.out.println(usuario.getTelefone().getNumero());
//		System.out.println(usuario.getTelefone().getCodigoArea());
//		System.out.println(usuario.getPerfil());
		
		Flash flash = FacesContext.getCurrentInstance().
					getExternalContext().
					getFlash();
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