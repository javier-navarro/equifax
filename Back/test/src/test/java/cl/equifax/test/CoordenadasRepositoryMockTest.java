package cl.equifax.test;

import cl.equifax.test.entity.Coordenadas;
import cl.equifax.test.entity.DetalleCoordenadas;
import cl.equifax.test.repository.CoordenadasRepository;
import cl.equifax.test.repository.DetalleCoordenadasRepository;
import cl.equifax.test.repository.UsuariosRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CoordenadasRepositoryMockTest {

    @Autowired
    private CoordenadasRepository coordenadasRepository;

    @Autowired
    private DetalleCoordenadasRepository   detalleCoordenadasRepository;

    @Autowired
    private UsuariosRepository usuariosRepository;


    @Test
    public void whenFindCoordenadas(){

        DetalleCoordenadas detalleCoordenadas1 = DetalleCoordenadas.builder()
                .idDetalleCoordenadas(1L)
                .detalleLatitud("sin datos")
                .detalleLongitud("sin datos")
                .build();

        detalleCoordenadasRepository.save(detalleCoordenadas1);

        System.out.println(detalleCoordenadas1.toString());
        Coordenadas coordenadas1 = Coordenadas.builder()
                .latitud("10.234")
                .longitud("-8.234")
                .datos(DetalleCoordenadas.builder().idDetalleCoordenadas(1L).build())
                .build();
        coordenadasRepository.save(coordenadas1);

        List<Coordenadas> listaCoordenadas = coordenadasRepository.findAll();
        List<DetalleCoordenadas> listadetalle = detalleCoordenadasRepository.findAll();
        Assertions.assertThat(listaCoordenadas.size()).isEqualTo(1);
    }
}
