package com.driveds.model;

import java.io.Serializable;

public class Arquivo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String nome;
	
	private String hash;
	
	private byte[] conteudo;
	
	private boolean novoOrModificado;
	
	private boolean removido;

	private String usuario;
	
	public Arquivo(String nome, String hash, byte[] conteudo, boolean novoOrModificado, boolean removido, String usuario) {
		this.nome = nome;
		this.hash = hash;
		this.conteudo = conteudo;
		this.novoOrModificado = novoOrModificado;
		this.removido = removido;
		this.usuario = usuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public boolean isNovoOrModificado() {
		return novoOrModificado;
	}

	public void setNovoOrModificado(boolean novoOrModificado) {
		this.novoOrModificado = novoOrModificado;
	}

	public boolean isRemovido() {
		return removido;
	}

	public void setRemovido(boolean removido) {
		this.removido = removido;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public byte[] getConteudo() {
		return conteudo;
	}

	public void setConteudo(byte[] conteudo) {
		this.conteudo = conteudo;
	}
}
