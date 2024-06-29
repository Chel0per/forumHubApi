package forumHub.api.domain.usuario;

import forumHub.api.domain.perfil.PerfilRepository;
import forumHub.api.domain.perfil.Perfil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PerfilRepository perfilRepository;

    @PostMapping("/login")
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(dados.email(),dados.senha());
        Authentication authentication = manager.authenticate(token);

        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity cadastrarUsuario(@RequestBody @Valid DadosCadastroUsuario dados) {
        Usuario usuario = new Usuario(dados);
        usuario = usuarioRepository.save(usuario);

        Perfil perfilInicial = new Perfil(dados.nome(),usuario.getId());
        perfilRepository.save(perfilInicial);

        return ResponseEntity.ok()
                .body(new DadosDetalhamentoUsuario(usuario.getId(), usuario.getNome(), usuario.getEmail()));
    }

}
