package cl.equifax.test.service;
import cl.equifax.test.entity.Coordenadas;
import cl.equifax.test.entity.DetalleCoordenadas;
import cl.equifax.test.repository.CoordenadasRepository;
import cl.equifax.test.repository.DetalleCoordenadasRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
public class CoordenadasServiceImpl implements CoordenadasService {

    @Autowired
    private CoordenadasRepository coordenadasRepository;

    @Autowired
    private DetalleCoordenadasRepository detalleCoordenadasRepository;

    @Autowired
    private UsuariosService service;

    @Override
    public List<Coordenadas> getCoordenadas(String token) {
        service.validate(token);
        if(service.validate(token) ==null){
            return null;
        }
        return coordenadasRepository.findAll();
    }

    @Override
    public List<DetalleCoordenadas> listaDetalle() {
        return detalleCoordenadasRepository.findAll();
    }

    @Override
    public List<Coordenadas> findById(String token,Long id) {
        return coordenadasRepository.findByIdCoordenadas(id);
    }
}
