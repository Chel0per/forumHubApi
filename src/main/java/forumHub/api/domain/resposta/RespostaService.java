package forumHub.api.domain.resposta;

import forumHub.api.domain.ValidacaoException;
import forumHub.api.domain.topico.TopicoRepository;
import forumHub.api.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RespostaService {

    @Autowired
    private RespostaRepository respostaRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Resposta cadastrarResposta(DadosCadastroResposta dados){
        if(!usuarioRepository.existsById(dados.autorId())){
            throw new ValidacaoException("Id de autor informado não existe!");
        }
        if(!topicoRepository.existsById(dados.topicoId())){
            throw new ValidacaoException("Id de tópico informado não existe!");
        }
        return respostaRepository.save(new Resposta(dados));
    }

    public Resposta deletarResposta(Long id){
        Optional<Resposta> respostaOptional = respostaRepository.findById(id);
        if(respostaOptional.isPresent()){
            respostaRepository.deleteById(id);
            return respostaOptional.get();
        }
        else {
            throw new ValidacaoException("Não existe uma resposta cadastrada com o Id fornecido!");
        }
    }

}
