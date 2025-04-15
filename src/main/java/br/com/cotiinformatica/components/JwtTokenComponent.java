package br.com.cotiinformatica.components;

import java.util.Date;

import org.springframework.stereotype.Component;

import br.com.cotiinformatica.entities.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenComponent {

	/*
	 * Método para gerar o token JWT com os dados do usuário autenticado
	 */
	public String getToken(Usuario usuario) {
		
		//chave para criptografia do TOKEN: cce09a14-97f4-409e-bbed-182d4e1c3002
		var secretKey = "cce09a14-97f4-409e-bbed-182d4e1c3002";
		
		return Jwts.builder()
                .setSubject(usuario.getEmail()) //email do usuário
                .claim("perfil", usuario.getPerfil().getNome()) //perfil do usuário
                .setIssuedAt(new Date())  //data de geração do token
                .setExpiration(new Date(System.currentTimeMillis() + 1800000)) //data de expiração do token 
                .signWith(SignatureAlgorithm.HS256, secretKey) //chave para assinatura do token
                .compact();
	}
}
