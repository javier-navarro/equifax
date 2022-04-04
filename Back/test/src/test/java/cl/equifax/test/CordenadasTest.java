package cl.equifax.test;

import cl.equifax.test.entity.DetalleCoordenadas;
import cl.equifax.test.repository.CoordenadasRepository;
import cl.equifax.test.repository.DetalleCoordenadasRepository;
import cl.equifax.test.service.CoordenadasServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CordenadasTest {

    @Mock
    private CoordenadasRepository coordenadasRepository;

    @InjectMocks
    private CoordenadasServiceImpl coordenadasService;

    @Mock
    private DetalleCoordenadasRepository   detalleCoordenadasRepository;


    @BeforeEach
    public void setup(){
        DetalleCoordenadas detalleCoordenadas1 = DetalleCoordenadas.builder()
                .idDetalleCoordenadas(1L)
                .detalleLatitud("sin datos")
                .detalleLongitud("sin datos")
                .build();

        detalleCoordenadasRepository.save(detalleCoordenadas1);

        List<DetalleCoordenadas> detalleCoordenadas = detalleCoordenadasRepository.findAll();
        Mockito.when(detalleCoordenadasRepository.findAll())
                .thenReturn(listaDetalle());
    }

    @Test
    public void whenCallDetalleCoordenadas(){
        List<DetalleCoordenadas> detalleCoordenadas  = coordenadasService.listaDetalle();
        Assertions.assertThat(detalleCoordenadas.size()).isEqualTo(1);
    }

    private List <DetalleCoordenadas > listaDetalle(){
        List <DetalleCoordenadas> lista = new ArrayList<>();
        lista.add(new DetalleCoordenadas(1L,"sin datos","sin datos"));
        return lista;
    }


     


}
