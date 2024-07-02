package forumHub.api.domain.topico;

import forumHub.api.domain.resposta.Resposta;

import java.time.LocalDateTime;
import java.util.List;

public record DadosTopicoCompleto(Long id, String titulo, String mensagem, LocalDateTime dataCriacao, boolean status, Long autorId, Long cursoId, List<Resposta> respostas) {

    public DadosTopicoCompleto(Topico topico,List<Resposta> respostas){
        this(topico.getId(),
        topico.getTitulo(),
        topico.getMensagem(),
        topico.getDataCriacao(),
        topico.isStatus(),
        topico.getAutorId(),
        topico.getCursoId(),
        respostas);
    }

}
