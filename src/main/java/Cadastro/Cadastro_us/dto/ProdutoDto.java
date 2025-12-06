package Cadastro.Cadastro_us.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProdutoDto {

    private Integer codigoBarras;
    private Integer numeroNotaFiscal;
    private String categoria;
    private String nome;
    private Long quantidade;
    private Double preco;
    private String dataValidade;
    private String fornecedor;
    private String descricao;
    private Boolean isActive;
}
