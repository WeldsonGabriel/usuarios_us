package Cadastro.Cadastro_us.infrastructure.repository;

import Cadastro.Cadastro_us.infrastructure.entitys.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    Optional<Produto> findByDataValidade(LocalDate dataValidade);
    Optional<Produto> findByCategoria(String categoria);
    Optional<Produto> findByNome(String nome);
    Optional<Produto> findByPreco(double preco);
    Optional<Produto> findByFornecedor(String fornecedor);
    Optional<Produto> findByDescricao(String descricao);
    Optional<Produto> findByCodigoBarras(String codigoBarras);
    Optional<Produto> findByNumeroNotaFiscal(String numeroNotaFiscal);
    Optional<Produto> findByIsActive(Boolean isActive);
    Optional<Produto> findByQuantidade(Long quantidade);
    Optional<Produto> findByUnidadeMedida(String unidadeMedida);


}
