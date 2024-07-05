package forumHub.api.domain.resposta;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/respostas")
@SecurityRequirement(name = "bearer-key")
public class RespostaController {

    @Autowired
    private RespostaService respostaService;

    @Transactional
    @PostMapping
    public ResponseEntity cadastrarResposta(@RequestBody @Valid DadosCadastroResposta dados){
        Resposta resposta = respostaService.cadastrarResposta(dados);
        return ResponseEntity.ok(resposta);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity deletarResposta(@PathVariable Long id) {
        Resposta resposta = respostaService.deletarResposta(id);
        return ResponseEntity.ok(resposta);
    }

}
