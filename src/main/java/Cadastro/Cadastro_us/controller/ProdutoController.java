package Cadastro.Cadastro_us.controller;

import Cadastro.Cadastro_us.business.ProdutoService;
import Cadastro.Cadastro_us.dto.ProdutoDto;
import Cadastro.Cadastro_us.infrastructure.entitys.Produto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.hibernate.engine.transaction.internal.jta.JtaStatusHelper.isActive;

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
                .preco(dto.getPreco())
                .dataValidade(dto.getDataValidade())
                .fornecedor(dto.getFornecedor())
                .descricao(dto.getDescricao())
                .unidadeMedida(dto.getUnidadeMedida())
                .build();
        produto.setIsActive(true);
        return ResponseEntity.ok(produtoService.salvarProduto(produto));
    }
}
