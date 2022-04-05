package cl.equifax.test.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "coordenadas")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Coordenadas {

    @Id
    @Column(name = "idCoordenadas")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCoordenadas;

    @Column(name = "latitud", nullable = false)
    private String latitud;

    @Column(name = "longitud", nullable = false)
    private String longitud;


    @ManyToOne
    @JoinColumn(name = "idDetalleCoordenadas")
    @JsonIgnoreProperties({"hibernateLazyInitializer,handler"})
    private DetalleCoordenadas datos;


}
