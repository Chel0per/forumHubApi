package forumHub.api.domain.curso;

import jakarta.persistence.*;

import java.util.Objects;

@Table(name = "cursos")
@Entity
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Enumerated(EnumType.STRING)
    private CategoriasCurso categoria;

    public Curso() {}

    public Curso(CategoriasCurso categoria, Long id, String nome) {
        this.categoria = categoria;
        this.id = id;
        this.nome = nome;
    }

    public Curso(DadosCadastroCurso dados) {
        nome = dados.nome();
        categoria = dados.categoria();
    }

    public CategoriasCurso getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriasCurso categoria) {
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Curso curso = (Curso) o;
        return Objects.equals(id, curso.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
