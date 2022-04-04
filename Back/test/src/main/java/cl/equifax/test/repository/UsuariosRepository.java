package cl.equifax.test.repository;


import cl.equifax.test.entity.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuariosRepository extends JpaRepository <Usuarios,Long> {

    Optional<Usuarios> findByUsername(String usuario);
    //String findByUsername(String usuario);
}
