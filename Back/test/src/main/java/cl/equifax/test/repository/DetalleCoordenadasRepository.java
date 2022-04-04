package cl.equifax.test.repository;

import cl.equifax.test.entity.DetalleCoordenadas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetalleCoordenadasRepository extends JpaRepository<DetalleCoordenadas,Long> {

    public List<DetalleCoordenadas> findAll();

}