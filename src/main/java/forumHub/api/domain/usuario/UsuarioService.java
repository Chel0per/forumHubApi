package forumHub.api.domain.usuario;

import forumHub.api.domain.ValidacaoException;
import forumHub.api.domain.perfil.Perfil;
import forumHub.api.domain.perfil.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PerfilRepository perfilRepository;

    public Usuario cadastrarUsuario(DadosCadastroUsuario dados){

        UserDetails usuarioJaCadastrado = usuarioRepository.findByEmail(dados.email());

        if(usuarioJaCadastrado != null){
            throw new ValidacaoException("Já existe um usuário cadastrado com esse e-mail");
        }

        Usuario usuario = new Usuario(dados);
        usuario = usuarioRepository.save(usuario);

        Perfil perfilInicial = new Perfil(dados.nome(),usuario.getId());
        perfilRepository.save(perfilInicial);

        return usuario;
    }

}
