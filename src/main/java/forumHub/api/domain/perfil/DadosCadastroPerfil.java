package forumHub.api.domain.perfil;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroPerfil(@NotBlank String nome,@NotNull Long usuarioId) {
}
