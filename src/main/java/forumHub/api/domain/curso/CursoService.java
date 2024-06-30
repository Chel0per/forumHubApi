package forumHub.api.domain.curso;

import forumHub.api.domain.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public Curso cadastrarCurso(DadosCadastroCurso dados){
        Curso cursoJaCriado = cursoRepository.findByNome(dados.nome());

        if(cursoJaCriado != null){
            throw new ValidacaoException("Um curso com esse nome já foi criado");
        }

        return cursoRepository.save(new Curso(dados));
    }

}
