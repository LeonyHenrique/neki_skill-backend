package com.neki_skill.entity;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.neki_skill.enuns.UserRole;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "usuario")
@JsonIdentityInfo(scope = Usuario.class, generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Usuario implements UserDetails {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    


    @NotBlank(message = "Login não pode estar em branco.")
    @Size(min = 1, max = 12, message = "O login deve ter menos do que 12 caracteres.")
    @Column(name = "login", nullable = false, unique = true)
    private String login;

    @NotBlank(message = "A senha não pode estar em branco.")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "senha", nullable = true)
    private String senha;

    @Column(name = "last_login_date")
    private Date lastLoginDate = new Date();

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<SkillUsuario> skills = new HashSet<>();
    
    private UserRole role;
    public Usuario() {
		// TODO Auto-generated constructor stub
	}
    
    public  Usuario(String login, String senha, UserRole role) {
    	this.login = login;
    	this.senha = senha;
    	this.role = role;
    	
    }

  

    public Long getId() {
        return id;
    }

    public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public Set<SkillUsuario> getSkills() {
        return skills;
    }

    public void setSkills(Set<SkillUsuario> skills) {
        this.skills = skills;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
    	if(this.role == UserRole.ADMIN) {
			return 
					List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
		}else { 
			return List.of(new SimpleGrantedAuthority("ROLE_USER"));
		}
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "Usuario [id=" + id + ", login=" + login + ", senha=" + senha + ", lastLoginDate="
                + lastLoginDate + ", skills=" + skills + "]";
    }


	}

