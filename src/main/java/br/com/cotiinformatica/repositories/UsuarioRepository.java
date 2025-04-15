package br.com.cotiinformatica.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.cotiinformatica.entities.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

	@Query("""
				select u from Usuario u
				where u.email = :email
			""")
	Usuario findByEmail(
			@Param("email") String email
			);
	
	@Query("""
				select u from Usuario u
				join u.perfil p
				where u.email = :email
				  and u.senha = :senha
			""")
	Usuario findByEmailAndSenha(
			@Param("email") String email,
			@Param("senha") String senha
			);
}
