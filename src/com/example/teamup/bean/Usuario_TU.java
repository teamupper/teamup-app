package com.example.teamup.bean;

import java.io.Serializable;

import com.example.teamup.usuarioendpoint.model.Usuario;

public class Usuario_TU implements Serializable {
	private Usuario usuario;

	public Usuario_TU(Usuario usuario) {
		super();
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
