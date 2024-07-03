package forumHub.api.domain.resposta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroResposta(
        @NotBlank
        String mensagem,
        @NotBlank
        String solucao,
        @NotNull
        Long autorId,
        @NotNull
        Long topicoId) {
}
