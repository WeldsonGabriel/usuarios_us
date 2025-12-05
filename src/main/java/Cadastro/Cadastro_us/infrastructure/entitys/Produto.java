package Cadastro.Cadastro_us.infrastructure.entitys;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

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

    @Column(name = "categoria", unique = true, nullable = false, length = 150)
    private String categoria;

    @Column(name = "nome", nullable = false, length = 150)
    private String nome;

    @Column(name = "quantidade", nullable = false, length = 12000)
    private Long quantidade;

    @Column(name = "preco", nullable = false, length = 50)
    private double preco;

    @Column(name = "dataValidade", nullable = false, length = 50)
    private String dataValidade;

    @Column(name = "criado_em", updatable = false)
    private LocalDateTime criadoEm;

    @PrePersist
    public void prePersist() {
        this.criadoEm = LocalDateTime.now();
    }
}

