package forumHub.api.domain.topico;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @Transactional
    @PostMapping
    public ResponseEntity cadastrarTopico(@RequestBody @Valid DadosCadastroTopico dados){
        Topico topicoCadastrado = topicoService.cadastrar(dados);
        return ResponseEntity.ok(new DadosDetalhamentoTopico(topicoCadastrado));
    }

    @GetMapping
    public ResponseEntity listarTopicos(@PageableDefault(size = 10) Pageable paginacao){
       Page<Topico> topicos = topicoService.listarTopicos(paginacao);
       return ResponseEntity.ok(topicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity listarTopico(@PathVariable Long id){
        DadosTopicoCompleto topico = topicoService.listarTopico(id);
        return ResponseEntity.ok(topico);
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity atualizarTopico(@RequestBody DadosAtualizacaoTopico dados,@PathVariable Long id){
        DadosTopicoCompleto topico = topicoService.atualizarTopico(dados,id);
        return ResponseEntity.ok(topico);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity deletarTopico(@PathVariable Long id){
        DadosTopicoCompleto topico = topicoService.deletarTopico(id);
        return ResponseEntity.ok(topico);
    }

}
