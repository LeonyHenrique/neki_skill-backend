package com.neki_skill.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SkillDTO {

	 @NotBlank(message = "Nome não pode estar em branco.")
	    private String nome;

	    @NotBlank(message = "Nível não pode estar em branco.")
	    private String nivel;

	    private String descricao;

	    @NotNull(message = "Usuário ID não pode estar em branco.")
	    private Long usuarioId;
	    
	    private String imageUrl;
	public SkillDTO(@NotBlank(message = "Nome não pode estar em branco.") String nome,
				@NotBlank(message = "Nível não pode estar em branco.") String nivel, String descricao,
				@NotNull(message = "Usuário ID não pode estar em branco.") Long usuarioId, String imageUrl) {
			super();
			this.nome = nome;
			this.nivel = nivel;
			this.descricao = descricao;
			this.usuarioId = usuarioId;
			this.imageUrl = imageUrl;
		}


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

}
