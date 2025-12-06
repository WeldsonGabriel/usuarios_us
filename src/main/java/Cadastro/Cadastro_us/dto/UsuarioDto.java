package Cadastro.Cadastro_us.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioDto {

    private String email;
    private String nome;
    private Boolean isActive;

}
