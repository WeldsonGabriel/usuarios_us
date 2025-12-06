package Cadastro.Cadastro_us.business;


import Cadastro.Cadastro_us.infrastructure.entitys.Produto;
import Cadastro.Cadastro_us.infrastructure.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Transactional
    public Produto salvarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

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

    @Transactional
    public void deletarProdutoPorId(Integer id) {
        if (!produtoRepository.existsById(id)) {
            throw new RuntimeException("Produto não encontrado!");
        }
        produtoRepository.deleteById(id);
    }

    @Transactional
    public Produto atualizarProdutoPorCategoria(String categoria, Produto produto) {
        Produto produtoEntity = buscarProdutoPorCategoria(categoria);

        if (produto.getCategoria() != null && !produto.getCategoria().isBlank()) {
            produtoEntity.setCategoria(produto.getCategoria());
        }

        if (produto.getNome() != null && !produto.getNome().isBlank()) {
            produtoEntity.setNome(produto.getNome());
        }

        if (produto.getQuantidade() != null) {
            produtoEntity.setQuantidade(produto.getQuantidade());
        }

        if (produto.getPreco() != 0) {
            produtoEntity.setPreco(produto.getPreco());
        }

        if (produto.getDataValidade() != null && !produto.getDataValidade().isBlank()) {
            produtoEntity.setDataValidade(produto.getDataValidade());
        }

        return produtoRepository.save(produtoEntity);
    }
    @Transactional
    public Produto atualizarProdutoPorId(Integer id, Produto produto) {
        Produto produtoEntity = buscarProdutoPorId(id);

        if (produto.getCategoria() != null && !produto.getCategoria().isBlank()) {
            produtoEntity.setCategoria(produto.getCategoria());
        }

        if (produto.getNome() != null && !produto.getNome().isBlank()) {
            produtoEntity.setNome(produto.getNome());
        }

        if (produto.getQuantidade() != null) {
            produtoEntity.setQuantidade(produto.getQuantidade());
        }

        if (produto.getPreco() != 0) {
            produtoEntity.setPreco(produto.getPreco());
        }

        if (produto.getDataValidade() != null && !produto.getDataValidade().isBlank()) {
            produtoEntity.setDataValidade(produto.getDataValidade());
        }

        return produtoRepository.save(produtoEntity);
    }
}
