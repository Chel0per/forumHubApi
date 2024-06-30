package forumHub.api.domain.topico;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @PostMapping
    public ResponseEntity cadastrarTopico(@RequestBody @Valid DadosCadastroTopico dados){
        Topico topicoCadastrado = topicoService.cadastrar(dados);
        return ResponseEntity.ok(new DadosDetalhamentoTopico(topicoCadastrado));
    }

}
