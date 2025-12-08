package Cadastro.Cadastro_us.business;


import Cadastro.Cadastro_us.infrastructure.entitys.Produto;
import Cadastro.Cadastro_us.infrastructure.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Transactional
    public Produto salvarProduto(Produto produto) {
        produto.setIsActive(true);
        return produtoRepository.save(produto);
    }

    // Leituras
    @Transactional(readOnly = true)
    public Produto buscarProdutoPorId(Integer id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado!"));
    }

    @Transactional(readOnly = true)
    public Produto buscarProdutoPorCategoria(String categoria) {
        return produtoRepository.findByCategoria(categoria)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado!"));
    }

    @Transactional(readOnly = true)
    public Produto buscarProdutoPorNome(String nome) {
        return produtoRepository.findByNome(nome)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado!"));
    }

    @Transactional(readOnly = true)
    public Produto buscarProdutoPorDataValidade(LocalDate dataValidade) {
        return produtoRepository.findByDataValidade(dataValidade)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado!"));
    }

    @Transactional(readOnly = true)
    public Produto buscarProdutoPorPreco(double preco) {
        return produtoRepository.findByPreco(preco)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado!"));
    }

    @Transactional(readOnly = true)
    public Produto buscarProdutoPorFornecedor(String fornecedor) {
        return produtoRepository.findByFornecedor(fornecedor)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado!"));
    }

    @Transactional(readOnly = true)
    public Produto buscarProdutoPorDescricao(String descricao) {
        return produtoRepository.findByDescricao(descricao)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado!"));
    }

    @Transactional(readOnly = true)
    public Produto buscarProdutoPorCodigoBarras(String codigoBarras) {
        return produtoRepository.findByCodigoBarras(codigoBarras)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado!"));
    }

    @Transactional(readOnly = true)
    public Produto buscarProdutoPorNumeroNotaFiscal(String numeroNotaFiscal) {
        return produtoRepository.findByNumeroNotaFiscal(numeroNotaFiscal)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado!"));
    }

    @Transactional(readOnly = true)
    public Produto buscarProdutoPorIsActive(Boolean isActive) {
        return produtoRepository.findByIsActive(isActive)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado!"));
    }

    @Transactional(readOnly = true)
    public Produto buscarProdutoPorQuantidade(Long quantidade) {
        return produtoRepository.findByQuantidade(quantidade)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado!"));
    }

    @Transactional(readOnly = true)
    public Produto buscarProdutoPorUnidadeMedida(String unidadeMedida) {
        return produtoRepository.findByUnidadeMedida(unidadeMedida)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado!"));
    }

    // Deleções
    @Transactional
    public void deletarProdutoPorId(Integer id) {
        if (!produtoRepository.existsById(id)) {
            throw new RuntimeException("Produto não encontrado!");
        }
        produtoRepository.deleteById(id);
    }

    @Transactional
    public void deletarProdutoPorCategoria(String categoria) {
        Produto produto = buscarProdutoPorCategoria(categoria);
        produtoRepository.delete(produto);
    }

    @Transactional
    public void deletarProdutoPorNome(String nome) {
        Produto produto = buscarProdutoPorNome(nome);
        produtoRepository.delete(produto);
    }

    //deleção lógica
    @Transactional
    public Produto desativarProdutoPorId(Integer id) {
        Produto produto = buscarProdutoPorId(id);
        produto.setIsActive(false);
        return produtoRepository.save(produto);
    }

    // Atualizações parciais

    @Transactional
    public Produto atualizarPrecoProduto(Integer id, Double novoPreco) {
        Produto produto = buscarProdutoPorId(id);
        produto.setPreco(novoPreco);
        return produtoRepository.save(produto);
    }

    @Transactional
    public Produto atualizarQuantidadeProduto(Integer id, Long novaQuantidade) {
        Produto produto = buscarProdutoPorId(id);
        produto.setQuantidade(novaQuantidade);
        return produtoRepository.save(produto);
    }

    // Atualizações completas
    @Transactional
    public Produto atualizarProdutoCompleto(Integer id, Produto produtoAtualizado) {
        Produto produtoExistente = buscarProdutoPorId(id);
        produtoExistente.setCodigoBarras(produtoAtualizado.getCodigoBarras());
        produtoExistente.setNumeroNotaFiscal(produtoAtualizado.getNumeroNotaFiscal());
        produtoExistente.setCategoria(produtoAtualizado.getCategoria());
        produtoExistente.setNome(produtoAtualizado.getNome());
        produtoExistente.setQuantidade(produtoAtualizado.getQuantidade());
        produtoExistente.setPreco(produtoAtualizado.getPreco());
        produtoExistente.setDataValidade(produtoAtualizado.getDataValidade());
        produtoExistente.setFornecedor(produtoAtualizado.getFornecedor());
        produtoExistente.setDescricao(produtoAtualizado.getDescricao());
        produtoExistente.setUnidadeMedida(produtoAtualizado.getUnidadeMedida());
        return produtoRepository.save(produtoExistente);
    }

}
