package forumHub.api.domain.curso;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroCurso(@NotBlank String nome, CategoriasCurso categoria) {
}
