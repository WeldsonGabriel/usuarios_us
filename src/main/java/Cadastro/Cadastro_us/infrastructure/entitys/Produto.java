package Cadastro.Cadastro_us.infrastructure.entitys;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "produtos")
@Entity

public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "codigo_barras", unique = true, nullable = false, length = 150)
    private String codigoBarras;

    @Column(name = "numeroNotaFiscal", unique = true, nullable = false, length = 150)
    private String numeroNotaFiscal;

    @Column(name = "categoria", nullable = false, length = 150)
    private String categoria;

    @Column(name = "nome", nullable = false, length = 150)
    private String nome;

    @Column(name = "quantidade", nullable = false, length = 12000)
    private Long quantidade;

    @Column(name = "preco", nullable = false, length = 50)
    private Double preco;

    @Column(name = "dataValidade", nullable = false, length = 50)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataValidade;

    @Column(name = "fornecedor", nullable = false, length = 150)
    private String fornecedor;

    @Column(name = "descricao", nullable = false, length = 500)
    private String descricao;

    @Column(name = "unidadeMedida", nullable = false, length = 50)
    private String unidadeMedida;

    @Column(name = "isActive", nullable = false)
    private Boolean isActive;

    @Column(name = "criado_em", updatable = false)
    private LocalDateTime criadoEm;

    @PrePersist
    public void prePersist() {
        this.criadoEm = LocalDateTime.now();
        this.isActive = true;
    }
}

