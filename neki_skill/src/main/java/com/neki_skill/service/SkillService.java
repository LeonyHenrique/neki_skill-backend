package com.neki_skill.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neki_skill.entity.Skill;
import com.neki_skill.entity.Usuario;
import com.neki_skill.repositories.SkillRepository;

@Service
public class SkillService {

    @Autowired
    private SkillRepository skillRepository;

    public List<Skill> findAll() {
        return skillRepository.findAll();
    }

    public Optional<Skill> findById(Long id) {
        return skillRepository.findById(id);
    }

    public Skill save(Skill skill) {
        return skillRepository.save(skill);
    }

 

	public List<Skill> findAllByUsuario(Usuario usuarioLogado) {
		// TODO Auto-generated method stub
		return null;
	}
	
	  public List<Skill> findAllSkillsByUsuario(Usuario usuario) {
	        return skillRepository.findAllByUsuarioId(usuario.getId());
	    }
}
