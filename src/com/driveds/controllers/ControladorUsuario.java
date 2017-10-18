package com.driveds.controllers;

import java.io.IOException;

import com.driveds.exceptions.PasswordIncorrectException;
import com.driveds.exceptions.UserNotFoundException;
import com.driveds.model.Usuario;
import com.driveds.service.Request;
import com.driveds.service.Response;
import com.driveds.util.Constants;
import com.driveds.util.Util;
import com.google.gson.Gson;

public class ControladorUsuario {

	public static boolean isDadosValido(String login, String senha) {
		return !((Util.isNullOrEmpty(login)) || (Util.isNullOrEmpty(senha)));
	}
	
	public static boolean userIsValid(String login, String senha) throws IOException, UserNotFoundException, PasswordIncorrectException {
		
		Usuario usuario = consultarUsuario(login);
		
		if (usuario == null) {
			throw new UserNotFoundException("login inválido. Favor cadastrar-se na aplicação online");
		}
		
		if (!usuario.getSenha().equals(senha)) {
			throw new PasswordIncorrectException("Senha Inválida!");
		}
		
		return true;
	}
	
	public static Usuario consultarUsuario(String login) throws IOException {
		
		Response response = Request.get(Constants.URL_CONSULTAR_USUARIO.replace("?", login), Constants.TEXT_PLAIN);
		
		return new Gson().fromJson(response.getConteudo(), Usuario.class);
	}
}
