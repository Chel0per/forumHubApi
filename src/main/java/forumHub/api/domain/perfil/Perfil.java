package forumHub.api.domain.perfil;

import jakarta.persistence.*;

import java.util.Objects;

@Table(name = "perfis")
@Entity
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Column(name = "usuario_id")
    private Long usuarioId;

    public Perfil() {}

    public Perfil(Long id, String nome, Long usuarioId) {
        this.id = id;
        this.nome = nome;
        this.usuarioId = usuarioId;
    }

    public Perfil(DadosCadastroPerfil dados) {
        nome = dados.nome();
        usuarioId = dados.usuarioId();
    }

    public Perfil(String nome, Long usuarioId) {
        this.nome = nome;
        this.usuarioId = usuarioId;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Perfil perfil = (Perfil) o;
        return Objects.equals(id, perfil.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
