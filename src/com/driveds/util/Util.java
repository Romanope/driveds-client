package com.driveds.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

public class Util {

	public static String getHashFromFile(File file) throws FileNotFoundException {

		try {
			
			String texto = getContentFromFile(file);
			
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
	

	public static String getContentFromFile(File file) throws FileNotFoundException {
		
		StringBuffer text = new StringBuffer();
		
		Scanner scanner = new Scanner(file);
		
		while (scanner.hasNextLine()) {
			text.append(scanner.nextLine());
		}
		
		scanner.close();
		
		return text.toString();
			
	}
	
	public static boolean isNullOrEmpty(String text) {
		return text == null || text.length() == 0;
	}
	
	public static byte[] toBytes(File file) throws IOException {

		ByteArrayOutputStream ous = null;
		InputStream ios = null;
		try {
			byte[] buffer = new byte[4096];
			ous = new ByteArrayOutputStream();
			ios = new FileInputStream(file);
			int read = 0;
			while ((read = ios.read(buffer)) != -1) {
				ous.write(buffer, 0, read);
			}
		} finally {
			try {
				if (ous != null)
					ous.close();
			} catch (IOException e) {
			}

			try {
				if (ios != null)
					ios.close();
			} catch (IOException e) {
			}
		}
		return ous.toByteArray();
	}
	
	public static void writeFile(String path, byte[] bytes) throws IOException {
		
		FileOutputStream fileOuputStream = null;
		try { 
		    fileOuputStream = new FileOutputStream(path); 
		    fileOuputStream.write(bytes);
		 } catch (IOException e) {
			 
		 } finally {
			 if (fileOuputStream != null) {
				 fileOuputStream.close();
			 }
		 }
	}
	
	public static void main(String[] args) throws IOException {
		
		/*String origem = "C:\\Users\\Romano\\Google Drive\\Classificação de tráfego de rede - artigos\\03.pdf";
		
		String destino = "D:\\03.pdf";
		
		byte[] fileInBytes = toBytes(origem);
		
		writeFile(destino, fileInBytes);
		
		System.out.println("Finalizando cópia...");*/
	}
}
