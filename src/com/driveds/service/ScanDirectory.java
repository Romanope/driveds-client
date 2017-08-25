package com.driveds.service;

import java.io.FileNotFoundException;

import com.driveds.controllers.ControllerDirectory;
import com.driveds.exceptions.InvalidDirectoryException;
import com.driveds.util.Constants;

public class ScanDirectory implements Runnable {

	public ScanDirectory() {
		
	}
	
	@Override
	public void run() {
		
		while (true) {
			
			try {
				ControllerDirectory.initScan();
			} catch (InvalidDirectoryException e) {
				System.out.println("Erro ao verificar diretório");
				e.printStackTrace();
			} catch (FileNotFoundException e) {
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
