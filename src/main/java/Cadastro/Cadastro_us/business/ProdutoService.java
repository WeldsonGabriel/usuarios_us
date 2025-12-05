package Cadastro.Cadastro_us.business;


import Cadastro.Cadastro_us.infrastructure.entitys.Usuario;
import Cadastro.Cadastro_us.infrastructure.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }
}
