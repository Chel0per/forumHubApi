package forumHub.api.domain.curso;

public record DadosDetalhamentoCurso(Long id, String nome, CategoriasCurso categoria) {
    public DadosDetalhamentoCurso(Curso curso){
        this(curso.getId(), curso.getNome(), curso.getCategoria());
    }
}
