package forumHub.api.domain.resposta;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Table(name = "respostas")
@Entity
public class Resposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String solucao;
    private String mensagem;
    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;
    @Column(name = "autor_id")
    private Long autorId;
    @Column(name = "topico_id")
    private Long topicoId;

    public Resposta() {}

    public Resposta(Long autorId, LocalDateTime dataCriacao, Long id, String mensagem, String solucao, Long topicoId) {
        this.autorId = autorId;
        this.dataCriacao = dataCriacao;
        this.id = id;
        this.mensagem = mensagem;
        this.solucao = solucao;
        this.topicoId = topicoId;
    }

    public Resposta(DadosCadastroResposta dados) {
        autorId = dados.autorId();
        topicoId = dados.topicoId();
        mensagem = dados.mensagem();
        solucao = dados.solucao();
        dataCriacao = LocalDateTime.now();
    }

    public Long getAutorId() {
        return autorId;
    }

    public void setAutorId(Long autorId) {
        this.autorId = autorId;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getSolucao() {
        return solucao;
    }

    public void setSolucao(String solucao) {
        this.solucao = solucao;
    }

    public Long getTopicoId() {
        return topicoId;
    }

    public void setTopicoId(Long topicoId) {
        this.topicoId = topicoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resposta resposta = (Resposta) o;
        return Objects.equals(id, resposta.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
