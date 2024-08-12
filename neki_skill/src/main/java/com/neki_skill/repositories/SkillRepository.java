package com.neki_skill.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.neki_skill.entity.Skill;

public interface SkillRepository extends JpaRepository<Skill, Long>{
	
	@Query("SELECT s FROM Skill s WHERE s.usuario.id = :userId OR s.usuario IS NULL")
	List<Skill> findAllByUsuarioId(@Param("userId") Long userId);
	}


