package com.neki_skill.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neki_skill.entity.SkillUsuario;
import com.neki_skill.exceptions.NoSuchElementFoundException;
import com.neki_skill.service.SkillUsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/skillusuario")
@Validated
@Tag(name = "SkillUsuario", description = "Endpoints para gerenciamento das habilidades dos usuários")
public class SkillUsuarioController {

	@Autowired
	private SkillUsuarioService skillUsuarioService;

	@Operation(summary = "Buscar habilidade do usuário por ID", responses = {
			@ApiResponse(responseCode = "200", description = "Operação bem sucedida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SkillUsuario.class))),
			@ApiResponse(responseCode = "404", description = "Habilidade não encontrada") })
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<SkillUsuario>> findAllByUsuarioId(@PathVariable Long userId) {
		List<SkillUsuario> skillUsuarios = skillUsuarioService.findAllByUsuarioId(userId);
		if (skillUsuarios.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(skillUsuarios, HttpStatus.OK);
	}

	@Operation(summary = "Salvar nova habilidade para o usuário", responses = {
			@ApiResponse(responseCode = "201", description = "Habilidade criada com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SkillUsuario.class))),
			@ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos para criar habilidade") })
	@PostMapping
	public ResponseEntity<SkillUsuario> save(@Validated @RequestBody SkillUsuario skillUsuario) {
		return new ResponseEntity<>(skillUsuarioService.save(skillUsuario), HttpStatus.CREATED);
	}

	@Operation(summary = "Atualizar habilidade do usuário", responses = {
			@ApiResponse(responseCode = "200", description = "Habilidade atualizada com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SkillUsuario.class))),
			@ApiResponse(responseCode = "404", description = "Habilidade não encontrada") })
	@PutMapping("/{id}")
	public ResponseEntity<SkillUsuario> update(@Validated @RequestBody SkillUsuario skillUsuario) {
		SkillUsuario updatedSkillUsuario = skillUsuarioService.update(skillUsuario);
		return new ResponseEntity<>(updatedSkillUsuario, HttpStatus.OK);
	}

	@Operation(summary = "Deletar habilidade do usuário por ID", responses = {
			@ApiResponse(responseCode = "204", description = "Habilidade deletada com sucesso"),
			@ApiResponse(responseCode = "404", description = "Habilidade não encontrada") })
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(
			@Parameter(in = ParameterIn.PATH, name = "id", description = "ID da habilidade do usuário") @PathVariable Long id) {
		if (skillUsuarioService.findById(id).isEmpty()) {
			throw new NoSuchElementFoundException("SkillUsuario com o ID: " + id + " não encontrado.");
		}
		skillUsuarioService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
