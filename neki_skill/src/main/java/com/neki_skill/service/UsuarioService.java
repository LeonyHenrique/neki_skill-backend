package com.neki_skill.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.neki_skill.entity.SkillUsuario;
import com.neki_skill.entity.Usuario;
import com.neki_skill.exceptions.NoSuchElementFoundException;
import com.neki_skill.repositories.SkillUsuarioRepository;
import com.neki_skill.repositories.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private SkillUsuarioRepository skillUsuarioRepository;

    public List<SkillUsuario> findAll() {
        return skillUsuarioRepository.findAll();
    }

    public Optional<SkillUsuario> findSkillUsuarioById(Long id) {
        return skillUsuarioRepository.findById(id);
    }

    public SkillUsuario save(SkillUsuario skillUsuario) {
        return skillUsuarioRepository.save(skillUsuario);
    }

    public void deleteById(Long id) {
        skillUsuarioRepository.deleteById(id);
    }
    public Optional<Usuario> findById(Long id) {
        return usuarioRepository.findById(id);
    }


    
    public Usuario findByLogin(String login) {
        return usuarioRepository.findUsuarioByLogin(login);
    }
    
    public Usuario getUsuarioLogado() {
    
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();

        Usuario usuario = usuarioRepository.findUsuarioByLogin(login);
        if (usuario != null) {
            return usuario;
        } else {
            throw new UsernameNotFoundException("Usuário não encontrado: " + login);
        }
    }

    public Usuario update(Usuario usuario) {
        Optional<SkillUsuario> optionalSkillUsuario = skillUsuarioRepository.findById(usuario.getId());
        if (!optionalSkillUsuario.isPresent()) {
            throw new NoSuchElementFoundException("Não existe nenhum usuario com o ID: " + usuario.getId() + ".");
        }

        SkillUsuario skillUsuario = optionalSkillUsuario.get();
        skillUsuario.setUsuario(usuario); 

        SkillUsuario usuarioAtualizado = skillUsuarioRepository.save(skillUsuario);
        return usuarioAtualizado.getUsuario();
	}
}
