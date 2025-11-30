package Cadastro.Cadastro_us.business;

import Cadastro.Cadastro_us.infrastructure.entitys.Usuario;
import Cadastro.Cadastro_us.infrastructure.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public void salvarUsuario(Usuario usuario) {
        usuarioRepository.saveAndFlush(usuario);
    }

    public void buscarUsuarioPorId(Integer id) {
        usuarioRepository.findById(id);
    }

    public Usuario buscarUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email).orElseThrow(
            () -> new RuntimeException("Usuário não encontrado!"
        ));
    }



    public void deletarUsuarioPorId(Integer id) {
        usuarioRepository.deleteById(id);
    }

}