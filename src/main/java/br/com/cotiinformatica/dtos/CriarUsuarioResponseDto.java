package br.com.cotiinformatica.dtos;

import java.time.Instant;
import java.util.UUID;

import lombok.Data;

@Data
public class CriarUsuarioResponseDto {

	private UUID id;
	private String nome;
	private String email;
	private Instant dataCriacao;
	private String perfil;
}
