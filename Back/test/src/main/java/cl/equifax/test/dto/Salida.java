package cl.equifax.test.dto;

import cl.equifax.test.entity.DetalleCoordenadas;
import lombok.Data;


@Data
public class Salida {

    private String latitud;
    private String longitud;
    private DetalleCoordenadas [] datos;

}
