package forumHub.api.domain.curso;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cursos")
@SecurityRequirement(name = "bearer-key")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @Transactional
    @PostMapping
    public ResponseEntity cadastrarCurso(@RequestBody @Valid DadosCadastroCurso dados){
        Curso cursoCadastrado = cursoService.cadastrarCurso(dados);
        return ResponseEntity.ok(new DadosDetalhamentoCurso(cursoCadastrado));
    }

}
