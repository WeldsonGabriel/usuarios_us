package Cadastro.Cadastro_us.business;

import Cadastro.Cadastro_us.infrastructure.entitys.Usuario;
import Cadastro.Cadastro_us.infrastructure.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public Usuario salvarUsuario(Usuario usuario) {
        // incluir o isActive como true ao criar um novo usuário
        usuario.setIsActive(true);
        return usuarioRepository.save(usuario);
    }
    // READ BY ID
    @Transactional(readOnly = true)
    public Usuario buscarUsuarioPorId(Integer id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
    }
    // READ BY EMAIL
    @Transactional(readOnly = true)
    public Usuario buscarUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
    }
    // READ BY NOME
    @Transactional(readOnly = true)
    public Usuario buscarUsuarioPorNome(String nome) {
        return usuarioRepository.findByNome(nome)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
    }
    // READ BY ISACTIVE
    @Transactional(readOnly = true)
    public Usuario buscarUsuarioPorIsActive(Boolean isActive) {
        return usuarioRepository.findByIsActive(isActive)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
    }
    @Transactional
    public void deletarUsuarioPorId(Integer id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuário não encontrado!");
        }
        usuarioRepository.deleteById(id);
    }

    @Transactional
    public void delecaoLogicaUsuarioPorId(Integer id) {
        Usuario usuario = buscarUsuarioPorId(id);
        usuario.setIsActive(false);
        usuarioRepository.save(usuario);
    }

    @Transactional
    public Usuario atualizarUsuarioPorEmail(String email, Usuario usuario) {
        Usuario usuarioEntity = buscarUsuarioPorEmail(email);

        if (usuario.getEmail() != null && !usuario.getEmail().isBlank()) {
            usuarioEntity.setEmail(usuario.getEmail());
        }

        if (usuario.getNome() != null && !usuario.getNome().isBlank()) {
            usuarioEntity.setNome(usuario.getNome());
        }

        return usuarioRepository.save(usuarioEntity);
    }

    @Transactional
    public Usuario atualizarUsuarioPorId(Integer id, Usuario usuario) {
        Usuario usuarioEntity = buscarUsuarioPorId(id);

        if (usuario.getEmail() != null && !usuario.getEmail().isBlank()) {
            usuarioEntity.setEmail(usuario.getEmail());
        }

        if (usuario.getNome() != null && !usuario.getNome().isBlank()) {
            usuarioEntity.setNome(usuario.getNome());
        }

        return usuarioRepository.save(usuarioEntity);
    }
}
