package Cadastro.Cadastro_us.business;

import Cadastro.Cadastro_us.infrastructure.entitys.Produto;
import Cadastro.Cadastro_us.infrastructure.repository.ProdutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProdutoServiceTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private ProdutoService produtoService;

    private Produto produto;

    @BeforeEach
    void setup() {
        produto = Produto.builder()
                .id(1)
                .codigoBarras("10000000000")
                .numeroNotaFiscal("211111111111")
                .categoria("Frios")
                .nome("Queijo")
                .quantidade(10L)
                .preco(1.56)
                .dataValidade(LocalDate.of(2026, 10, 25))
                .fornecedor("Tres Marias")
                .descricao("Valor nutricional")
                .unidadeMedida("kg")
                .isActive(true)
                .build();
    }

    // ===================== CREATE =====================

    @Test
    void deveSalvarProdutoComSucesso() {

        when(produtoRepository.save(any(Produto.class))).thenReturn(produto);

        Produto produtoSalvo = produtoService.salvarProduto(produto);

        assertNotNull(produtoSalvo);
        assertTrue(produtoSalvo.getIsActive());
        assertEquals("Queijo", produtoSalvo.getNome());
        verify(produtoRepository, times(1)).save(produto);
    }

    // ===================== READ =====================

    @Test
    void deveBuscarProdutoPorId() {

        when(produtoRepository.findById(1)).thenReturn(Optional.of(produto));

        Produto encontrado = produtoService.buscarProdutoPorId(1);

        assertNotNull(encontrado);
        assertEquals("Queijo", encontrado.getNome());
        verify(produtoRepository, times(1)).findById(1);
    }

    @Test
    void deveBuscarProdutoPorCategoria() {

        when(produtoRepository.findByCategoria("Frios")).thenReturn(Optional.of(produto));

        Produto encontrado = produtoService.buscarProdutoPorCategoria("Frios");

        assertEquals("Frios", encontrado.getCategoria());
        verify(produtoRepository, times(1)).findByCategoria("Frios");
    }

    @Test
    void deveBuscarProdutoPorNome() {

        when(produtoRepository.findByNome("Queijo")).thenReturn(Optional.of(produto));

        Produto encontrado = produtoService.buscarProdutoPorNome("Queijo");

        assertEquals("Queijo", encontrado.getNome());
        verify(produtoRepository, times(1)).findByNome("Queijo");
    }

    // ===================== DELETE LOGICO =====================

    @Test
    void deveDesativarProdutoPorId() {

        when(produtoRepository.findById(1)).thenReturn(Optional.of(produto));
        when(produtoRepository.save(any(Produto.class))).thenReturn(produto);

        Produto produtoDesativado = produtoService.desativarProdutoPorId(1);

        assertFalse(produtoDesativado.getIsActive());
        verify(produtoRepository, times(1)).save(produto);
    }

    // ===================== DELETE =====================

    @Test
    void deveDeletarProdutoPorId() {

        when(produtoRepository.existsById(1)).thenReturn(true);
        doNothing().when(produtoRepository).deleteById(1);

        produtoService.deletarProdutoPorId(1);

        verify(produtoRepository, times(1)).deleteById(1);
    }

    // ===================== UPDATE =====================

    @Test
    void deveAtualizarProdutoPorId() {

        Produto novoProduto = Produto.builder()
                .categoria("Laticínios")
                .nome("Queijo Minas")
                .quantidade(20L)
                .preco(5.0)
                .dataValidade(LocalDate.of(2027, 1, 1))
                .build();

        when(produtoRepository.findById(1)).thenReturn(Optional.of(produto));
        when(produtoRepository.save(any(Produto.class))).thenReturn(produto);

        Produto atualizado = produtoService.atualizarProdutoCompleto(1, novoProduto);

        assertNotNull(atualizado);
        assertEquals("Laticínios", atualizado.getCategoria());
        assertEquals("Queijo Minas", atualizado.getNome());
        assertEquals(20L, atualizado.getQuantidade());
        assertEquals(5.0, atualizado.getPreco());

        verify(produtoRepository, times(1)).save(produto);
    }

    // ===================== BUSCA NAO ENCONTRADO =====================

    @Test
    void deveLancarExcecaoQuandoNaoEncontrarProduto() {

        when(produtoRepository.findById(9)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> produtoService.buscarProdutoPorId(9));

        assertEquals("Produto não encontrado!", exception.getMessage());
    }
}
