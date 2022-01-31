package com.grupolemon.ocarsionplus.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.grupolemon.ocarsionplus.model.Usuario;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long>{

	Optional<Usuario> findByEmail(String email);

}
