package com.driveds.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import com.driveds.exceptions.InvalidDirectoryException;
import com.driveds.util.Constants;
import com.driveds.util.Util;

public class ControllerDirectory {

	private int numberOfFiles;
	
	private static Map<String, String> hashs;
	
	public static void createDirectory() {
		new File(Constants.DEFAULT_DIRECTORY).mkdir();
	}
	
	public static void initScan() throws InvalidDirectoryException, FileNotFoundException {
		
		createDirectory();
		
		initMapHashs();
		
		File[] files = listFiles(Constants.DEFAULT_DIRECTORY);
		
		for (File file: files) {
			
			if (hashs.get(file.getName()) == null) {
				hashs.put(file.getName(), Util.getHashFromFile(file));
				sendContent(file);
			} else {
				String lastHash = hashs.get(file.getName());
				String currentHash = Util.getHashFromFile(file);
				
				System.out.println("lastHash: " + lastHash);
				System.out.println("currentHash: " + currentHash);
				if (!lastHash.equals(currentHash)) {
					System.out.println("Hashs são diferentes!!!");
					sendContent(file);
				} else {
					System.out.println("hashs são iguais!!!");
				}
			}
			
			System.out.println("File Name: " + file.getName());
		}
	}
	
	private static void initMapHashs() {
		if (hashs == null) {
			hashs = new HashMap<String, String>();
		}
	}
	
	private static void sendContent (File file) {
		//implementar envio dos dados para o WS
	}
	
	private static File[] listFiles(String directory) throws InvalidDirectoryException {
		File folder = new File(directory);
		if (!folder.exists()) {
			throw new InvalidDirectoryException("Directory not exists");
		}
		return folder.listFiles();
	}
}
