package br.com.cotiinformatica.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotiinformatica.dtos.AutenticarUsuarioRequestDto;
import br.com.cotiinformatica.dtos.AutenticarUsuarioResponseDto;
import br.com.cotiinformatica.dtos.CriarUsuarioRequestDto;
import br.com.cotiinformatica.dtos.CriarUsuarioResponseDto;
import br.com.cotiinformatica.services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

	//Atributos para injeção de dependência
	@Autowired UsuarioService usuarioService;
	
	@Operation(summary = "Serviço para criação de usuários.")
	@PostMapping("criar")
	public ResponseEntity<CriarUsuarioResponseDto> criar(@RequestBody @Valid CriarUsuarioRequestDto request) {
		
		//executar a camada de serviço
		var response = usuarioService.criarUsuario(request);		
		//retornar resposta de sucesso
		return ResponseEntity.ok(response);
	}

	@Operation(summary = "Serviço para autenticação de usuários.")
	@PostMapping("autenticar")
	public ResponseEntity<AutenticarUsuarioResponseDto> autenticar(@RequestBody @Valid AutenticarUsuarioRequestDto request) {

		//executar a camada de serviço
		var response = usuarioService.autenticarUsuario(request);
		//retornara  resposta de sucesso
		return ResponseEntity.ok(response);
	}
}












