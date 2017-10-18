package com.driveds.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.driveds.exceptions.InvalidDirectoryException;
import com.driveds.model.Arquivo;
import com.driveds.service.Request;
import com.driveds.service.Response;
import com.driveds.service.ScanDirectory;
import com.driveds.util.Constants;
import com.driveds.util.Util;
import com.google.gson.Gson;

public class ControladorDiretorio {

	private static File[] files; 
	
	private static Map<String, String> hashs;
	
	public static void createDirectory(String directory) {
		new File(directory).mkdir();
	}
	
	public static List<Arquivo> atualizarArquivoAdicionadosEditados() throws InvalidDirectoryException, IOException {
		
		init();

		ArrayList<Arquivo> arquivos = new ArrayList<Arquivo>();
		
		files = listFiles(Constants.DEFAULT_DIRECTORY);

		atualizarArquivoAdicionadosEditados(arquivos, files);

		return arquivos;
	}

	/**
	 * Verifica se algum arquivo removido do 
	 * diretório desde o último escaneamento
	 */
	public static ArrayList<Arquivo> verificarArquivosRemovidos() {
		
		ArrayList<Arquivo> arquivosRemovidos = new ArrayList<Arquivo>();
		Iterator<String> it = hashs.keySet().iterator();
		List<String> keys = new ArrayList<String>();
		while (it.hasNext()) {
			String key = it.next();
			File arqEncontrado = null;
			for (File file: files) {
				if (file.getName().equalsIgnoreCase(key)) {
					arqEncontrado = file;
					break;
				}
			}

			if (arqEncontrado == null) {
				addFileTolist(arquivosRemovidos, key, hashs.get(key), new byte[0], false, true, ScanDirectory.user);
			}
		}
		
		for (Arquivo arqRemovido: arquivosRemovidos) {
			hashs.remove(arqRemovido.getNome());
		}
		
		return arquivosRemovidos;
	}

	/*
	 * Método responsável por fazer a verificação dos arquivos adicionados e editados
	 */
	private static void atualizarArquivoAdicionadosEditados(List<Arquivo> arquivos, File[] files)
			throws FileNotFoundException, IOException {
		for (File file: files) {
			String hash = Util.getHashFromFile(file);
			if (hashs.get(file.getName()) == null) {
				hashs.put(file.getName(), hash);
				addFileTolist(arquivos, file.getName(), hash, Util.toBytes(file), true, false, ScanDirectory.user);
			} else {
				String lastHash = hashs.get(file.getName());
				String currentHash = Util.getHashFromFile(file);
				if (!lastHash.equals(currentHash)) {
					hashs.put(file.getName(), hash);
					addFileTolist(arquivos, file.getName(), hash, Util.toBytes(file), true, false, ScanDirectory.user);
				}
			}
		}
	}
	
	private static void init() {
		if (hashs == null) {
			hashs = new HashMap<String, String>();
		}
	}
	
	private static void addFileTolist (List<Arquivo> arquivos, String nomeArquivo, String hash, byte[] conteudo, boolean novoOrModificado, boolean removido, String usuario) {
		arquivos.add(new Arquivo(nomeArquivo, hash, conteudo, novoOrModificado, removido, usuario));
	}
	
	private static File[] listFiles(String directory) throws InvalidDirectoryException {
		File folder = new File(directory);
		if (!folder.exists()) {
			createDirectory(directory);
		}
		return folder.listFiles();
	}
	
	public static boolean enviarArquivos(List<Arquivo> arquivos) {
		List<Arquivo> data = new ArrayList<Arquivo>();
		for (Arquivo arquivo: arquivos) {
			enviarArquivo(data, arquivo);
		}
		
		return false;
	}

	private static void enviarArquivo(List<Arquivo> data, Arquivo arquivo) {
		System.out.println("Enviando arquivo: " + arquivo.getNome());
		try {
			data.add(arquivo);
			String jsonArqs = new Gson().toJson(data);
			Request.post(Constants.URL_ENVIAR_ARQUIVOS, Constants.APPLICATION_JSON, jsonArqs);
			data.clear();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void removerArquivosNuvem(List<Arquivo> arqRemovidos) {
		for (Arquivo arq: arqRemovidos) {
			removerArquivo(arq.getNome());
		}
	}
	
	private static void removerArquivo(String nomeArquivo) {
		String arg = nomeArquivo + "/" + ScanDirectory.user;
		String url = Constants.URL_DELETE_FILE.replace("?", arg);
		System.out.println("removendo arquivo " + nomeArquivo);
		try {
			Response response = Request.delete(url, Constants.TEXT_PLAIN);
			System.out.println("Código do resultado da operação realizada para apagar o arquivo no servidor " + response.getStatusCode());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
