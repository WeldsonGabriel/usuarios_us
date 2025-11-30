package Cadastro.Cadastro_us.infrastructure.repository;

import Cadastro.Cadastro_us.infrastructure.entitys.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

}
