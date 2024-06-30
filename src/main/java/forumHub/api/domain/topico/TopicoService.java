package forumHub.api.domain.topico;

import forumHub.api.domain.ValidacaoException;
import forumHub.api.domain.curso.CursoRepository;
import forumHub.api.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    public Topico cadastrar(DadosCadastroTopico dados){
        if (!usuarioRepository.existsById(dados.autorId())) {
            throw new ValidacaoException("Id do usuário informado não existe!");
        }

        if (!cursoRepository.existsById(dados.cursoId())) {
            throw new ValidacaoException("Id do curso informado não existe!");
        }

        List<Topico> topicosRepetidos = topicoRepository.findAllByTitulo(dados.titulo());

        for (Topico topico : topicosRepetidos) {
            if(topico.getMensagem().equals(dados.mensagem())){
                throw new ValidacaoException("Não é permitido criar um tópico com título e mensagem iguais a um já existente!");
            }
        }

        return topicoRepository.save(new Topico(dados));
    }

}
