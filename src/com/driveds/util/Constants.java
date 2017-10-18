package com.driveds.util;

import java.io.File;

public class Constants {

	public static final String DEFAULT_DIRECTORY = System.getProperty("user.home") + File.separator + "DriveDS";
	
	public static final long INTERVAL = 3000;

	public static final String POST = "POST";
	
	public static final String ADICIONADO = "ADICIONADO";
	
	public static final String MODIFICADO = "MODIFICADO";
	
	public static final String URL_ENVIAR_ARQUIVOS = "http://localhost:3000/arquivos";
	
	public static final String APPLICATION_JSON = "application/json";

	public static final String TEXT_PLAIN = "text/plain";

	public static final String URL_FIND_ALL_BY_USER = "http://localhost:3000/arquivos/";

	public static final String URL_CONSULTAR_USUARIO = "http://localhost:3000/usuario/?";

	public static final String URL_DELETE_FILE = "http://localhost:3000/arquivos/?";

	public static final String GET = "GET";

	public static final String DELETE = "DELETE";
	
}
