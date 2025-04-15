package br.com.cotiinformatica.services;

import java.time.Instant;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cotiinformatica.components.JwtTokenComponent;
import br.com.cotiinformatica.components.SHA256Component;
import br.com.cotiinformatica.dtos.AutenticarUsuarioRequestDto;
import br.com.cotiinformatica.dtos.AutenticarUsuarioResponseDto;
import br.com.cotiinformatica.dtos.CriarUsuarioRequestDto;
import br.com.cotiinformatica.dtos.CriarUsuarioResponseDto;
import br.com.cotiinformatica.entities.Usuario;
import br.com.cotiinformatica.repositories.PerfilRepository;
import br.com.cotiinformatica.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	
	//Atributos inicilizados por injeção de dependência
	@Autowired UsuarioRepository usuarioRepository;
	@Autowired PerfilRepository perfilRepository;
	@Autowired SHA256Component sha256Component;
	@Autowired JwtTokenComponent jwtTokenComponent;

	/*
	 * Método para criar um usuário na API
	 */
	public CriarUsuarioResponseDto criarUsuario(CriarUsuarioRequestDto request) {
				
		//verificar se já existe um usuário com o email informado no banco de dados
		if(usuarioRepository.findByEmail(request.getEmail()) != null) {
			throw new IllegalArgumentException("O email informado já está cadastrado no sistema. Tente outro.");
		}
		
		var usuario = new Usuario(); //entidade
		
		//capturar os dados do usuário para que possamos grava-lo no banco de dados
		usuario.setId(UUID.randomUUID());
		usuario.setNome(request.getNome());
		usuario.setEmail(request.getEmail());
		usuario.setSenha(sha256Component.encrypt(request.getSenha()));
		usuario.setPerfil(perfilRepository.findByNome("OPERADOR"));
		
		//gravar o usuário no banco de dados
		usuarioRepository.save(usuario);
		
		//retornar os dados do usuário cadastrado
		var response = new CriarUsuarioResponseDto();
		response.setId(usuario.getId());
		response.setNome(usuario.getNome());
		response.setEmail(usuario.getEmail());
		response.setDataCriacao(Instant.now());
		response.setPerfil("OPERADOR");
		
		return response;
	}
	
	/*
	 * Método para implementar a autenticação do usuário na API
	 */
	public AutenticarUsuarioResponseDto autenticarUsuario(AutenticarUsuarioRequestDto request) {
	
		//buscar o usuário no banco de dados através do email e da senha
		var usuario = usuarioRepository.findByEmailAndSenha(request.getEmail(), sha256Component.encrypt(request.getSenha()));
		
		//verificar se o usuário não foi encontrado
		if(usuario == null)
			throw new IllegalArgumentException("Acesso negado. Usuário inválido.");
		
		//retornar os dados do usuário
		var response = new AutenticarUsuarioResponseDto();
		
		response.setId(usuario.getId());
		response.setNome(usuario.getNome());
		response.setEmail(usuario.getEmail());
		response.setPerfil(usuario.getPerfil().getNome());
		response.setToken(jwtTokenComponent.getToken(usuario));
		
		return response;
	}
	
}














