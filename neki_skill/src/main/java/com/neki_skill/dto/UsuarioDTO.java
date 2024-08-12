package com.neki_skill.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.neki_skill.entity.SkillUsuario;
import com.neki_skill.entity.Usuario;

public class UsuarioDTO {
    private Long id;
    private String login;
    private Set<SkillUsuario> skillUsuarios; 

    public UsuarioDTO(Usuario usuario) {
    	this.id= usuario.getId();
    	this.login = usuario.getLogin();
    	this.skillUsuarios = usuario.getSkills();
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

	public Set<SkillUsuario> getSkillUsuarios() {
		return skillUsuarios;
	}

	public void setSkillUsuarios(Set<SkillUsuario> skillUsuarios) {
		this.skillUsuarios = skillUsuarios;
	}

    
}
