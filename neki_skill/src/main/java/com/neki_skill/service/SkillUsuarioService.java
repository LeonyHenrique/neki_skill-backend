package com.neki_skill.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neki_skill.entity.SkillUsuario;
import com.neki_skill.exceptions.NoSuchElementFoundException;
import com.neki_skill.repositories.SkillUsuarioRepository;

@Service
public class SkillUsuarioService {
	
	 @Autowired
	    private SkillUsuarioRepository skillUsuarioRepository;

	    public List<SkillUsuario> findAll() {
	        return skillUsuarioRepository.findAll();
	    }

	    public Optional<SkillUsuario> findById(Long id) {
	        return skillUsuarioRepository.findById(id);
	    }

	    public SkillUsuario save(SkillUsuario skillUsuario) {
	        return skillUsuarioRepository.save(skillUsuario);
	    }

	    public void deleteById(Long id) {
	        skillUsuarioRepository.deleteById(id);
	    }
	    public List<SkillUsuario> findAllByUsuarioId(Long userId) {
	        return skillUsuarioRepository.findAllByUsuarioId(userId);
	    }

	    public SkillUsuario update(SkillUsuario skillUsuario) {
	        Optional<SkillUsuario> optionalSkillUsuario = skillUsuarioRepository.findById(skillUsuario.getId());
	        if (!optionalSkillUsuario.isPresent()) {
	            throw new NoSuchElementFoundException("SkillUsuario não encontrado com o ID: " + skillUsuario.getId());
	        }

	        SkillUsuario existingSkillUsuario = optionalSkillUsuario.get();
	        existingSkillUsuario.setLevel(skillUsuario.getLevel()); 
	        existingSkillUsuario.setUpdatedAt(LocalDateTime.now()); 

	        return skillUsuarioRepository.save(existingSkillUsuario);
	    }
}
