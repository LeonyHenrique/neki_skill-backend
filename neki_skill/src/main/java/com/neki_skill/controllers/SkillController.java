package com.neki_skill.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neki_skill.entity.Skill;
import com.neki_skill.entity.Usuario;
import com.neki_skill.exceptions.EmptyListException;
import com.neki_skill.exceptions.NoSuchElementFoundException;
import com.neki_skill.service.SkillService;
import com.neki_skill.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/skills")
@Validated
@CrossOrigin
@Tag(name = "Skill", description = "Endpoints para gerenciar Skills")
public class SkillController {

    @Autowired
    private SkillService skillService;
    
    @Autowired
    private UsuarioService usuarioService;


    @Operation(operationId = "createSkill", summary = "Cadastrar nova Skill", tags = { "Skill" }, responses = {
            @ApiResponse(responseCode = "201", description = "Skill criada com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Skill.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "403", description = "Acesso negado") })
    @PostMapping("/cadastrar")
    public ResponseEntity<Skill> createSkill(@Valid @RequestBody Skill skill, @RequestParam Long usuarioId) {
        Usuario usuario = usuarioService.findById(usuarioId)
                .orElseThrow(() -> new NoSuchElementFoundException("Usuário não encontrado."));
        skill.setUsuario(usuario);
        Skill newSkill = skillService.save(skill);
        return new ResponseEntity<>(newSkill, HttpStatus.CREATED);
    }

    @Operation(operationId = "getSkillsByUserId", summary = "Buscar Skills por ID do Usuário", tags = { "Skill" }, responses = {
            @ApiResponse(responseCode = "200", description = "Requisição bem-sucedida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Skill.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Nenhuma Skill encontrada para o usuário"),
            @ApiResponse(responseCode = "403", description = "Acesso negado") })
    @GetMapping("/user/{userId}/skills")
    public ResponseEntity<List<Skill>> getSkillsByUserId(@PathVariable Long userId) {
        Usuario usuario = usuarioService.findById(userId)
                .orElseThrow(() -> new NoSuchElementFoundException("Usuário não encontrado."));
        
        List<Skill> skills = skillService.findAllSkillsByUsuario(usuario);
        if (skills.isEmpty()) {
            throw new EmptyListException("Nenhuma skill disponível para o usuário.");
        }
        
        return new ResponseEntity<>(skills, HttpStatus.OK);
    }
}
