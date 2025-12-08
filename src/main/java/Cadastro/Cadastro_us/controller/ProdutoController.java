package Cadastro.Cadastro_us.controller;

import Cadastro.Cadastro_us.business.ProdutoService;
import Cadastro.Cadastro_us.dto.ProdutoDto;
import Cadastro.Cadastro_us.infrastructure.entitys.Produto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    // CREATE
    @PostMapping
    public ResponseEntity<Produto> salvar(@RequestBody ProdutoDto dto) {

        Produto produto = Produto.builder()
                .codigoBarras(dto.getCodigoBarras())
                .numeroNotaFiscal(dto.getNumeroNotaFiscal())
                .categoria(dto.getCategoria())
                .nome(dto.getNome())
                .quantidade(dto.getQuantidade())
                .preco(dto.getPreco().doubleValue())
                .dataValidade(dto.getDataValidade())
                .fornecedor(dto.getFornecedor())
                .descricao(dto.getDescricao())
                .unidadeMedida(dto.getUnidadeMedida())
                .isActive(true)
                .build();

        return ResponseEntity.ok(produtoService.salvarProduto(produto));
    }

    // READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(produtoService.buscarProdutoPorId(id));
    }

    // READ BY CATEGORIA
    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<Produto> buscarPorCategoria(@PathVariable String categoria) {
        return ResponseEntity.ok(produtoService.buscarProdutoPorCategoria(categoria));
    }

    // READ BY NOME
    @GetMapping("/nome/{nome}")
    public ResponseEntity<Produto> buscarPorNome(@PathVariable String nome) {
        return ResponseEntity.ok(produtoService.buscarProdutoPorNome(nome));
    }

    // READ BY DATA VALIDADE
    @GetMapping("/data-validade/{dataValidade}")
    public ResponseEntity<Produto> buscarPorDataValidade(@PathVariable LocalDate dataValidade) {
        return ResponseEntity.ok(produtoService.buscarProdutoPorDataValidade(dataValidade));
    }

    // READ BY PREÇO
    @GetMapping("/preco/{preco}")
    public ResponseEntity<Produto> buscarPorPreco(@PathVariable Double preco) {
        return ResponseEntity.ok(produtoService.buscarProdutoPorPreco(preco));
    }

    // READ BY FORNECEDOR
    @GetMapping("/fornecedor/{fornecedor}")
    public ResponseEntity<Produto> buscarPorFornecedor(@PathVariable String fornecedor) {
        return ResponseEntity.ok(produtoService.buscarProdutoPorFornecedor(fornecedor));
    }

    // READ BY DESCRIÇÃO
    @GetMapping("/descricao/{descricao}")
    public ResponseEntity<Produto> buscarPorDescricao(@PathVariable String descricao) {
        return ResponseEntity.ok(produtoService.buscarProdutoPorDescricao(descricao));
    }

    // READ BY CODIGO DE BARRAS
    @GetMapping("/codigo-barras/{codigoBarras}")
    public ResponseEntity<Produto> buscarPorCodigoBarras(@PathVariable String codigoBarras) {
        return ResponseEntity.ok(produtoService.buscarProdutoPorCodigoBarras(codigoBarras));
    }

    // READ BY NOTA FISCAL
    @GetMapping("/nota-fiscal/{numeroNotaFiscal}")
    public ResponseEntity<Produto> buscarPorNumeroNotaFiscal(@PathVariable String numeroNotaFiscal) {
        return ResponseEntity.ok(produtoService.buscarProdutoPorNumeroNotaFiscal(numeroNotaFiscal));
    }

    // READ BY ISACTIVE
    @GetMapping("/isActive/{isActive}")
    public ResponseEntity<Produto> buscarPorIsActive(@PathVariable Boolean isActive) {
        return ResponseEntity.ok(produtoService.buscarProdutoPorIsActive(isActive));
    }

    // READ BY QUANTIDADE
    @GetMapping("/quantidade/{quantidade}")
    public ResponseEntity<Produto> buscarPorQuantidade(@PathVariable Long quantidade) {
        return ResponseEntity.ok(produtoService.buscarProdutoPorQuantidade(quantidade));
    }

    // READ BY UNIDADE DE MEDIDA
    @GetMapping("/unidade-medida/{unidadeMedida}")
    public ResponseEntity<Produto> buscarPorUnidadeMedida(@PathVariable String unidadeMedida) {
        return ResponseEntity.ok(produtoService.buscarProdutoPorUnidadeMedida(unidadeMedida));
    }

    // UPDATE BY ID
    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizar(
            @PathVariable Integer id,
            @RequestBody ProdutoDto dto
    ) {

        Produto produto = Produto.builder()
                .categoria(dto.getCategoria())
                .nome(dto.getNome())
                .quantidade(dto.getQuantidade())
                .preco(dto.getPreco().doubleValue())
                .dataValidade(dto.getDataValidade())
                .build();

        return ResponseEntity.ok(produtoService.atualizarProdutoCompleto(id, produto));
    }


    // DELETE LOGICO
    @PatchMapping("/delecao-logica/{id}")
    public ResponseEntity<Void> delecaoLogica(@PathVariable Integer id) {
        produtoService.desativarProdutoPorId(id);
        return ResponseEntity.noContent().build();
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        produtoService.deletarProdutoPorId(id);
        return ResponseEntity.noContent().build();
    }
}
