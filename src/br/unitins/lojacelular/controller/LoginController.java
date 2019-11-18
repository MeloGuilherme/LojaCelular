package br.unitins.lojacelular.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.unitins.lojacelular.application.*;
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

			// armazenando um usuario na sessao
			Session.getInstance().setAttribute("usuarioLogado", usuario);
			Util.redirect("menu.xhtml");
			Integer num = usuario.getPerfil().getValue();

			if (num.equals(1)) {

				System.out.println("O usuário é um Administrador.");
			}

			else if (num.equals(2)) {

				System.out.println("O usuário é um Funcionário.");
			}

			else if (num.equals(3)) {

				System.out.println("O usuário é um Cliente.");

			}

			else {

				System.out.println("Falha na autenticação.");
			}
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
