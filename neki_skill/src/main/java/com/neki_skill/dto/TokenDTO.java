package com.neki_skill.dto;

import com.neki_skill.entity.Usuario;

public class TokenDTO {
	private String token;
	private UsuarioDTO usuario;
	
	public TokenDTO() {
		// TODO Auto-generated constructor stub
	}

	public TokenDTO(String token, Usuario usuario) {
		super();
		this.token = token;
		this.usuario = new UsuarioDTO (usuario);
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}

}