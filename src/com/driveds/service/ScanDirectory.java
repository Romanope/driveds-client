package com.driveds.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.driveds.controllers.ControladorDiretorio;
import com.driveds.exceptions.InvalidDirectoryException;
import com.driveds.model.Arquivo;
import com.driveds.util.Constants;

public class ScanDirectory implements Runnable {

	public static String user;
	
	public ScanDirectory(String login) {
		user = login;
	}
	
	@Override
	public void run() {
		
		while (true) {
			try {
				List<Arquivo> filesAddEdits = ControladorDiretorio.atualizarArquivoAdicionadosEditados();
				//houve mudança no diretório do usuário
				if (filesAddEdits.size() > 0) {
					ControladorDiretorio.enviarArquivos(filesAddEdits);
				}
				
				ArrayList<Arquivo> arquivosRemovidos = ControladorDiretorio.verificarArquivosRemovidos();
				if (arquivosRemovidos.size() > 0) {
					ControladorDiretorio.removerArquivosNuvem(arquivosRemovidos);
				}
			} catch (InvalidDirectoryException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			sleep();
		}
	
	}
	
	private void sleep() {
		try {
			Thread.sleep(Constants.INTERVAL);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		
	}
	
}
