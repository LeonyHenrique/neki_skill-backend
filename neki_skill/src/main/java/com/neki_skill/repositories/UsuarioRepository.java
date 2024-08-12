package com.neki_skill.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.neki_skill.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
   // Optional<Usuario> findByLogi(String log );
	UserDetails findByLogin(String login);
	
	Usuario findUsuarioByLogin(String login);
}
