package com.neki_skill.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neki_skill.dto.UsuarioDTO;
import com.neki_skill.entity.SkillUsuario;
import com.neki_skill.entity.Usuario;
import com.neki_skill.exceptions.EmptyListException;
import com.neki_skill.exceptions.NoSuchElementFoundException;
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
@RequestMapping("/api/usuario")
@Validated
@CrossOrigin
@Tag(name = "Usuário", description = "Endpoints")
public class UsuarioController {

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private UsuarioService service;

	/*
	 * @Operation(operationId = "findAllUsuario", summary =
	 * "Listar todos os Usuários", tags = { "Usuário" }, responses = {
	 * 
	 * @ApiResponse(responseCode = "200", description = "Operação bem sucedida",
	 * content = @Content(mediaType = "application/json", schema
	 * = @Schema(implementation = Usuario.class))),
	 * 
	 * @ApiResponse(responseCode = "400", description = "Informações Inválidas"),
	 * 
	 * @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
	 * 
	 * @ApiResponse(responseCode = "403", description = "Sem permissão") })
	 * 
	 * @GetMapping("/listarTodos") public ResponseEntity<List<SkillUsuario>>
	 * findAllUsuario() { List<SkillUsuario> usuarioList = service.findAll(); if
	 * (usuarioList.isEmpty()) { throw new
	 * EmptyListException("A lista de usuarios está vazia."); } else { return new
	 * ResponseEntity<List<SkillUsuario>>(usuarioList, HttpStatus.OK); } }
	 */

	@Operation(operationId = "findByLogin", summary = "Encontrar os usuários pelo login", tags = {
			"Usuário" }, parameters = {
					@Parameter(in = ParameterIn.PATH, name = "login", description = "Usuario Login") }, responses = {
							@ApiResponse(responseCode = "200", description = "Operação bem sucedida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class))),
							@ApiResponse(responseCode = "400", description = "Informações Inválidas"),
							@ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
							@ApiResponse(responseCode = "403", description = "Sem permissão") })
	@GetMapping("/usuarioAchado/{login}")
	public ResponseEntity<UsuarioDTO> findByLogin(@PathVariable String login) {
		System.out.println("Requisição recebida para login: " + login);
		Usuario usuario = service.findByLogin(login);
		if (usuario == null) {
			System.out.println("Usuário não encontrado para o login: " + login);
			throw new NoSuchElementFoundException("Não existe nenhum usuario com o login: " + login + ".");
		} else {
			System.out.println("Usuário encontrado: " + usuario.getLogin());
			return new ResponseEntity<>(new UsuarioDTO(usuario), HttpStatus.OK);
		}
	}
}
