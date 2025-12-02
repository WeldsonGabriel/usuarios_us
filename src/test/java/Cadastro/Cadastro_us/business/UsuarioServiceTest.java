package Cadastro.Cadastro_us.business;

import Cadastro.Cadastro_us.infrastructure.entitys.Usuario;
import Cadastro.Cadastro_us.infrastructure.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UsuarioServiceTest {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    void deveSalvarUsuario() {
        Usuario usuario = Usuario.builder()
                .email("service@test.com")
                .nome("Teste Service")
                .build();

        Usuario salvo = usuarioService.salvarUsuario(usuario);

        assertNotNull(salvo.getId());
        assertEquals("service@test.com", salvo.getEmail());
        assertEquals("Teste Service", salvo.getNome());
    }

    @Test
    void deveBuscarUsuarioPorId() {
        Usuario usuario = Usuario.builder()
                .email("buscar@test.com")
                .nome("Buscar")
                .build();

        Usuario salvo = usuarioRepository.save(usuario);

        Usuario encontrado = usuarioService.buscarUsuarioPorId(salvo.getId());

        assertEquals(salvo.getEmail(), encontrado.getEmail());
    }

    @Test
    void deveBuscarUsuarioPorEmail() {
        Usuario usuario = Usuario.builder()
                .email("email@test.com")
                .nome("Email")
                .build();

        usuarioRepository.save(usuario);

        Usuario encontrado = usuarioService.buscarUsuarioPorEmail("email@test.com");

        assertEquals("email@test.com", encontrado.getEmail());
    }

    @Test
    void deveAtualizarUsuarioPorId() {
        Usuario usuario = Usuario.builder()
                .email("antigo@test.com")
                .nome("Antigo")
                .build();

        Usuario salvo = usuarioRepository.save(usuario);

        Usuario novo = Usuario.builder()
                .email("novo@test.com")
                .nome("Novo Nome")
                .build();

        Usuario atualizado = usuarioService.atualizarUsuarioPorId(salvo.getId(), novo);

        assertEquals("novo@test.com", atualizado.getEmail());
        assertEquals("Novo Nome", atualizado.getNome());
    }

    @Test
    void deveDeletarUsuario() {
        Usuario usuario = Usuario.builder()
                .email("delete@test.com")
                .nome("Delete")
                .build();

        Usuario salvo = usuarioRepository.save(usuario);

        usuarioService.deletarUsuarioPorId(salvo.getId());

        assertFalse(usuarioRepository.existsById(salvo.getId()));
    }
}
