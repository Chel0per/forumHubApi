package forumHub.api.domain.perfil;

import java.util.List;

public record DadosPerfisUsuario(String email, List<Perfil> perfis) {
}
