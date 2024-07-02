package forumHub.api.domain.topico;

import forumHub.api.domain.ValidacaoException;
import forumHub.api.domain.curso.CursoRepository;
import forumHub.api.domain.resposta.Resposta;
import forumHub.api.domain.resposta.RespostaRepository;
import forumHub.api.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private RespostaRepository respostaRepository;

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

    public Page<Topico> listarTopicos(Pageable paginacao) {
        return topicoRepository.findAllByOrderByDataCriacaoDesc(paginacao);
    }

    public DadosTopicoCompleto listarTopico(Long id) {
        Optional<Topico> topicoOptional = topicoRepository.findById(id);
        if(topicoOptional.isPresent()){
            List<Resposta> respostas = respostaRepository.findAllByTopicoId(id);
            return new DadosTopicoCompleto(topicoOptional.get(),respostas);
        }
        else {
            throw new ValidacaoException("Não existe um tópico cadastrado com o Id fornecido!");
        }
    }

    @Transactional
    public DadosTopicoCompleto atualizarTopico(DadosAtualizacaoTopico dados, Long id) {
        Optional<Topico> topicoOptional = topicoRepository.findById(id);
        if(topicoOptional.isPresent()){
            List<Resposta> respostas = respostaRepository.findAllByTopicoId(id);
            Topico topico = topicoOptional.get();
            topico.atualizarInformacoes(dados);
            topicoRepository.save(topico);
            return new DadosTopicoCompleto(topico,respostas);
        }
        else {
            throw new ValidacaoException("Não existe um tópico cadastrado com o Id fornecido!");
        }
    }

}
