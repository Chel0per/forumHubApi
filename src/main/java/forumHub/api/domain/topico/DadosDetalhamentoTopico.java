package forumHub.api.domain.topico;

import java.time.LocalDateTime;

public record DadosDetalhamentoTopico(Long id, String titulo, String mensagem, LocalDateTime dataCriacao, boolean status, Long autorId, Long cursoId) {

    public DadosDetalhamentoTopico(Topico topico){
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getDataCriacao(), topico.isStatus(), topico.getAutorId(), topico.getCursoId());
    }

}
