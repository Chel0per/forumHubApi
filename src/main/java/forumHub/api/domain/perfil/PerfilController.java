package forumHub.api.domain.perfil;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/perfis")
@SecurityRequirement(name = "bearer-key")
public class PerfilController {

    @Autowired
    private PerfilService perfilService;

    @Transactional
    @PostMapping
    public ResponseEntity cadastrarPerfil(@RequestBody @Valid DadosCadastroPerfil dados){
        Perfil perfil = perfilService.cadastrarPerfil(dados);
        return ResponseEntity.ok(perfil);
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity alterarPerfil(@PathVariable Long id,@RequestBody @Valid DadosAtualizacaoPerfil dados){
        Perfil perfil = perfilService.atualizarPerfil(id,dados);
        return ResponseEntity.ok(perfil);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity deletarPerfil(@PathVariable Long id){
        Perfil perfil = perfilService.deletarPerfil(id);
        return ResponseEntity.ok(perfil);
    }

    @GetMapping("/per_user/{id}")
    public ResponseEntity mostrarPerfisDeUmUsuario(@PathVariable Long id){
        DadosPerfisUsuario dadosPerfis = perfilService.mostrarPerfisDeUmUsuario(id);
        return ResponseEntity.ok(dadosPerfis);
    }

}
