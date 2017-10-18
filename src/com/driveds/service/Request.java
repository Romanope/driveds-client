package com.driveds.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.driveds.util.Constants;

public class Request {

	public static Response post(String url, String contentType, String args) throws IOException  {
		
		HttpURLConnection conn = null;
		StringBuilder response = new StringBuilder();
		Response res = new Response();
			
		URL url1 = new URL(url);
		conn = (HttpURLConnection) url1.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod(Constants.POST);
		conn.setRequestProperty("Content-Type", contentType);

		OutputStream os = conn.getOutputStream();
		os.write(args.getBytes());
		os.flush();
		os.close();
		
		if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
			throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
		}

		BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

		String output;
		while ((output = br.readLine()) != null) {
			response.append(output);
		}

		res.setStatusCode(conn.getResponseCode());
		res.setConteudo(response.toString());
		
		conn.disconnect();
		
		
		return res;
	}

	public static Response get(String url, String contentType) throws IOException  {
		
		HttpURLConnection conn = null;
		StringBuilder response = new StringBuilder();
		Response res = new Response();
		
		URL url1 = new URL(url);
		conn = (HttpURLConnection) url1.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod(Constants.GET);
		
		BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
		
		String output;
		while ((output = br.readLine()) != null) {
			response.append(output);
		}
		
		res.setStatusCode(conn.getResponseCode());
		res.setConteudo(response.toString());
		System.out.println("response " + response.toString());
		conn.disconnect();
		
		return res;
	}

	public static Response delete(String url, String contentType) throws IOException  {
		
		HttpURLConnection conn = null;
		StringBuilder response = new StringBuilder();
		Response res = new Response();
		
		URL url1 = new URL(url);
		conn = (HttpURLConnection) url1.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod(Constants.DELETE);
		
		if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
			throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
		}

		BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
		
		String output;
		while ((output = br.readLine()) != null) {
			response.append(output);
		}
		
		res.setStatusCode(conn.getResponseCode());
		res.setConteudo(response.toString());
		System.out.println("response " + response.toString());
		conn.disconnect();
		
		return res;
	}
}
