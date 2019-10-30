package br.unitins.lojacelular.application;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.commons.codec.digest.DigestUtils;

public class Util {

	public static void addMessageInfo(String message) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
	}

	public static void addMessageWarn(String message) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, message, null));
	}

	public static void addMessageError(String message) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
	}
	
	// Redireciona para a URL desejada
	public static void redirect(String url) {
		
		try {
			
			FacesContext.getCurrentInstance().getExternalContext().redirect(url);
		} 
		
		catch (IOException e) {
			
			addMessageError("Erro ao redirecionar a página.");
			e.printStackTrace();
		}
	}
	
	public static String gerarHash(String valor) {
		
		return DigestUtils.sha256Hex(valor);
	}
}
