package com.driveds.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

public class Util {

	public static String getHashFromFile(File file) throws FileNotFoundException {

		try {
			
			String texto = contentFromFile(file);
			
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(texto.getBytes());
			
			return new HexBinaryAdapter().marshal(messageDigest.digest());
		
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException();
		}
		
		return null;
	}
	

	public static String contentFromFile(File file) throws FileNotFoundException {
		
		StringBuffer text = new StringBuffer();
		
		Scanner scanner = new Scanner(file);
		
		while (scanner.hasNextLine()) {
			text.append(scanner.nextLine());
		}
		
		scanner.close();
		
		return text.toString();
			
	}
}
