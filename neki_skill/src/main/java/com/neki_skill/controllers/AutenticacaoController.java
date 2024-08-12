package com.neki_skill.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neki_skill.dto.AithenticationDTO;
import com.neki_skill.dto.ResterDTO;
import com.neki_skill.dto.TokenDTO;
import com.neki_skill.entity.Usuario;
import com.neki_skill.repositories.UsuarioRepository;
import com.neki_skill.service.TokenService;
import com.neki_skill.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@Validated
@CrossOrigin
@Tag(name = "Autenticação", description = "Endpoints")
public class AutenticacaoController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenService tokenService;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Operation(operationId = "auth", summary = "Fazer login", tags = { "Autenticação" }, responses = {
			@ApiResponse(responseCode = "200", description = "Operação bem sucedida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TokenDTO.class))),
			@ApiResponse(responseCode = "400", description = "Informações Inválidas"),
			@ApiResponse(responseCode = "404", description = "Autenticação não encontrada"),
			@ApiResponse(responseCode = "403", description = "Sem permissão") })
	@PostMapping("/login")
	public ResponseEntity<TokenDTO> login(@Valid @RequestBody AithenticationDTO data) {

		var usernamePassword = new UsernamePasswordAuthenticationToken(data.getLogin(), data.getSenha());
		var auth = this.authenticationManager.authenticate(usernamePassword);

		Usuario usuario = (Usuario) auth.getPrincipal();

		String token = tokenService.generateToken(usuario);

		return ResponseEntity.ok(new TokenDTO(token, usuario));
	}

	@Operation(operationId = "registrar", summary = "Cadastrar Usuário", tags = { "Autenticação" }, responses = {
			@ApiResponse(responseCode = "200", description = "Operação bem sucedida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TokenDTO.class))),
			@ApiResponse(responseCode = "400", description = "Informações Inválidas"),
			@ApiResponse(responseCode = "404", description = "Autenticação não encontrada"),
			@ApiResponse(responseCode = "403", description = "Sem permissão") })
	@PostMapping("/register")
	public ResponseEntity registrar(@Valid @RequestBody ResterDTO data) {

		if (this.repository.findByLogin(data.login()) != null)
			return ResponseEntity.badRequest().build();

		String encryptedPassword = new BCryptPasswordEncoder().encode(data.senha());
		Usuario newUser = new Usuario(data.login(), encryptedPassword, data.role());

		this.repository.save(newUser);

		return ResponseEntity.ok().build();
	}

}
