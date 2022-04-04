package cl.equifax.test.service;

import cl.equifax.test.entity.Coordenadas;
import cl.equifax.test.entity.DetalleCoordenadas;

import java.util.List;

public interface CoordenadasService {
    public List<Coordenadas> getCoordenadas(String token);
    public List<DetalleCoordenadas> listaDetalle();

}
