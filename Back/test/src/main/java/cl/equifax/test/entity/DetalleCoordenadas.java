package cl.equifax.test.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "DetalleCoordenadas")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DetalleCoordenadas {

    @Id
    @Column(name = "idDetalleCoordenadas")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long idDetalleCoordenadas;


    @Column(name = "detalleLatitud")
    private String detalleLatitud;

    @Column(name = "detalleLongitud")
    private String detalleLongitud;
}
