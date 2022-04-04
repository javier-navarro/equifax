package cl.equifax.test.repository;

import cl.equifax.test.entity.Coordenadas;
import cl.equifax.test.entity.DetalleCoordenadas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoordenadasRepository extends JpaRepository<Coordenadas,Long> {

    public List <Coordenadas> findAll();

}

