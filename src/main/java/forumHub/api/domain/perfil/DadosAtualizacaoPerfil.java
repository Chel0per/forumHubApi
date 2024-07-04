package forumHub.api.domain.perfil;

import jakarta.validation.constraints.NotBlank;

public record DadosAtualizacaoPerfil(@NotBlank String nome) {
}
