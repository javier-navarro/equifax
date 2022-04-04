package cl.equifax.test.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "usuarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Usuarios {

    @Id
    @Column(name = "idUsuarios")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuarios;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

}
