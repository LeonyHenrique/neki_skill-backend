package com.neki_skill.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.neki_skill.entity.SkillUsuario;

public interface SkillUsuarioRepository extends JpaRepository<SkillUsuario, Long> {

    Optional<SkillUsuario> findByUsuarioId(Long usuarioId);
    Optional<SkillUsuario> findByUsuarioLogin(String login);
    @Query("SELECT su FROM SkillUsuario su WHERE su.usuario.id = :userId")
    List<SkillUsuario> findAllByUsuarioId(@Param("userId") Long userId);
    
}
