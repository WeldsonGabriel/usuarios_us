package Cadastro.Cadastro_us.infrastructure.entitys;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "usuario")
@Entity

public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "email", unique = true, nullable = false, length = 150)
    private String email;

    @Column(name = "nome", nullable = false, length = 150)
    private String nome;
}
