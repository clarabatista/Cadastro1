package com.atvpoo.cadastro.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.atvpoo.cadastro.model.Usuario;

@Repository
public interface RepositorioUsuario extends CrudRepository<Usuario, Long> {
}
