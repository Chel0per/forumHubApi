package forumHub.api.domain.usuario;

import forumHub.api.infra.security.DadosTokenJWT;
import forumHub.api.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
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
    private TokenService tokenService;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(dados.email(),dados.senha());
        Authentication authentication = manager.authenticate(token);

        String tokenString = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new DadosTokenJWT(tokenString));
    }

    @Transactional
    @PostMapping
    public ResponseEntity cadastrarUsuario(@RequestBody @Valid DadosCadastroUsuario dados) {
        Usuario usuario = usuarioService.cadastrarUsuario(dados);
        return ResponseEntity.ok()
                .body(new DadosDetalhamentoUsuario(usuario.getId(), usuario.getNome(), usuario.getEmail()));
    }

}
