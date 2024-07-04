package forumHub.api.domain.perfil;

import forumHub.api.domain.ValidacaoException;
import forumHub.api.domain.usuario.Usuario;
import forumHub.api.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PerfilService {

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Perfil cadastrarPerfil(DadosCadastroPerfil dados){
        if(!usuarioRepository.existsById(dados.usuarioId())){
            throw new ValidacaoException("Não existe um usuário cadastrado com o Id fornecido!");
        }

        List<Perfil> perfisDoUsuario = perfilRepository.findAllByUsuarioId(dados.usuarioId());

        for(Perfil perfil : perfisDoUsuario){
            if(perfil.getNome().equals(dados.nome())){
                throw new ValidacaoException("Este usuário já possui um perfil com esse nome!");
            }
        }

        return perfilRepository.save(new Perfil(dados));

    }

    public Perfil atualizarPerfil(Long id,DadosAtualizacaoPerfil dados){

        Optional<Perfil> perfilOptional = perfilRepository.findById(id);

        if(perfilOptional.isPresent()){
           Perfil perfil = perfilOptional.get();

           List<Perfil> perfisDoUsuario = perfilRepository.findAllByUsuarioId(perfil.getUsuarioId());

           for(Perfil perfilUsuario : perfisDoUsuario){
               if(perfilUsuario.getNome().equals(dados.nome())){
                    throw new ValidacaoException("Este usuário já possui um perfil com esse nome!");
               }
           }

           perfil.setNome(dados.nome());
           return perfilRepository.save(perfil);
        }
        else {
            throw new ValidacaoException("Não existe um perfil cadastrado com o id informado");
        }

    }

    public Perfil deletarPerfil(Long id){
        Optional<Perfil> perfilOptional = perfilRepository.findById(id);
        if(perfilOptional.isPresent()){
            Perfil perfil = perfilOptional.get();

            List<Perfil> perfisDoUsuario = perfilRepository.findAllByUsuarioId(perfil.getUsuarioId());

            if(perfisDoUsuario.size() == 1){
                throw new ValidacaoException("Não é possível deletar o perfil de um usuário que só possui um perfil");
            }
            perfilRepository.deleteById(id);
            return perfil;
        }
        else {
            throw new ValidacaoException("Não existe um perfil cadastrado com o id informado");
        }
    }

    public DadosPerfisUsuario mostrarPerfisDeUmUsuario(Long id){
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

        if(usuarioOptional.isPresent()){
            Usuario usuario = usuarioOptional.get();
            List<Perfil> perfis = perfilRepository.findAllByUsuarioId(usuario.getId());
            return new DadosPerfisUsuario(usuario.getEmail(), perfis);
        }
        else {
            throw new ValidacaoException("Não existe um usuário cadastrado com o id informado");
        }
    }

}
