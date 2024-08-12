package com.neki_skill.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "skill")
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_skill", unique = true)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "versao")
    private String versao;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "image_url")
    private String imageUrl;

    @JsonIgnore
    @OneToMany(mappedBy = "skill", cascade = CascadeType.ALL)
    private List<SkillUsuario> usuarios;
    
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario; 

   
 public Skill() {
	// TODO Auto-generated constructor stub
}

    public Skill(String nome, String descricao, String imageUrl) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.imageUrl = imageUrl;
	}
    public Long getId() {
    	return id;
    }

	public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getVersao() {
        return versao;
    }

    public void setVersao(String versao) {
        this.versao = versao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<SkillUsuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<SkillUsuario> usuarios) {
        this.usuarios = usuarios;
    }
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

	@Override
	public String toString() {
		return "Skill [id=" + id + ", nome=" + nome + ", versao=" + versao + ", descricao=" + descricao + ", imageUrl="
				+ imageUrl + ", usuarios=" + usuarios + ", usuario=" + usuario + "]";
	}

}
