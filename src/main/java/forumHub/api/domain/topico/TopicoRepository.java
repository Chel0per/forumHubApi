package forumHub.api.domain.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicoRepository extends JpaRepository<Topico,Long> {

    List<Topico> findAllByTitulo(String titulo);

    Page<Topico> findAllByOrderByDataCriacaoDesc(Pageable pageable);

}
