package Cadastro.Cadastro_us.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProdutoDto {

    private String codigoBarras;
    private String numeroNotaFiscal;
    private String categoria;
    private String nome;
    private Long quantidade;
    private Double preco;
    private LocalDate dataValidade;
    private String fornecedor;
    private String descricao;
    private String unidadeMedida;
    private Boolean isActive;
}
