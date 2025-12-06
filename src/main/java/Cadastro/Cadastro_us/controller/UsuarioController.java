package Cadastro.Cadastro_us.controller;

import Cadastro.Cadastro_us.business.UsuarioService;
import Cadastro.Cadastro_us.dto.UsuarioDto;
import Cadastro.Cadastro_us.infrastructure.entitys.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    // CREATE
    @PostMapping
    public ResponseEntity<Usuario> salvar(@RequestBody UsuarioDto dto) {

        Usuario usuario = Usuario.builder()
                .email(dto.getEmail())
                .nome(dto.getNome())
                .build();

        return ResponseEntity.ok(usuarioService.salvarUsuario(usuario));
    }

    // READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorId(id));
    }

    // READ BY EMAIL
    @GetMapping("/email/{email}")
    public ResponseEntity<Usuario> buscarPorEmail(@PathVariable String email) {
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorEmail(email));
    }

    // READ BY NOME
    @GetMapping("/nome/{nome}")
    public ResponseEntity<Usuario> buscarPorNome(@PathVariable String nome) {
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorNome(nome));
    }

    // READ BY ISACTIVE
    @GetMapping("/isActive/{isActive}")
    public ResponseEntity<Usuario> buscarPorIsActive(@PathVariable Boolean isActive) {
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorIsActive(isActive));
    }

    // UPDATE BY ID
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizar(
            @PathVariable Integer id,
            @RequestBody UsuarioDto dto
    ) {

        Usuario usuario = Usuario.builder()
                .email(dto.getEmail())
                .nome(dto.getNome())
                .build();

        return ResponseEntity.ok(usuarioService.atualizarUsuarioPorId(id, usuario));
    }

    // UPDATE BY EMAIL
    @PutMapping("/email/{email}")
    public ResponseEntity<Usuario> atualizarPorEmail(
            @PathVariable String email,
            @RequestBody UsuarioDto dto
    ) {
        Usuario usuario = Usuario.builder()
                .email(dto.getEmail())
                .nome(dto.getNome())
                .build();

        return ResponseEntity.ok(usuarioService.atualizarUsuarioPorEmail(email, usuario));
    }

    // DELETE LOGICO
    @PatchMapping("/delecao-logica/{id}")
    public ResponseEntity<Void> delecaoLogica(@PathVariable Integer id) {
        usuarioService.delecaoLogicaUsuarioPorId(id);
        return ResponseEntity.noContent().build();
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        usuarioService.deletarUsuarioPorId(id);
        return ResponseEntity.noContent().build();
    }
}
