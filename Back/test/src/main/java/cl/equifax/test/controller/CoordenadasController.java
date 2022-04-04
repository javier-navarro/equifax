package cl.equifax.test.controller;

import cl.equifax.test.dto.CoordenadasResponse;
import cl.equifax.test.dto.Salida;
import cl.equifax.test.dto.TokenDto;
import cl.equifax.test.dto.UsuariosDto;
import cl.equifax.test.entity.Coordenadas;
import cl.equifax.test.entity.DetalleCoordenadas;
import cl.equifax.test.entity.Usuarios;
import cl.equifax.test.service.CoordenadasService;
import cl.equifax.test.service.UsuariosService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.cfg.CreateKeySecondPass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "equifax-test")
public class CoordenadasController {

    @Autowired
    private CoordenadasService coordenadasService;

    @Autowired
    UsuariosService usuariosService;


    //ULTIMA VERSION FUNCIONANDO
    @GetMapping("listado")
    public ResponseEntity<List<Coordenadas>> listaCoordenadas(@RequestHeader String token){
        List <Coordenadas> coordenadas = coordenadasService.getCoordenadas(token);
        System.out.println("[CoordenadasController - listadoCoordenadas]: "+coordenadas);
        if(coordenadas == null){
            System.out.println("controller-token");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        if(coordenadas.isEmpty()){
            System.out.println("controller-en el empty");
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(coordenadas);
    }

    @GetMapping("detalle")
    public ResponseEntity<List<DetalleCoordenadas>> listaDetalleCoordenadas(@RequestHeader String token){
        List <DetalleCoordenadas> detalleCoordenadas = coordenadasService.listaDetalle();
        if(detalleCoordenadas.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(detalleCoordenadas);
    }


    @PostMapping("/auth/login")
    public ResponseEntity <TokenDto> login(@RequestBody UsuariosDto dto){
        System.out.println("entrada: "+dto);
        TokenDto tokenDto = usuariosService.login(dto);
        System.out.println("respuestax: "+tokenDto);
        if(tokenDto == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(tokenDto);

    }

    @GetMapping("/auth/validate")
    public ResponseEntity <TokenDto> validate(@RequestHeader String token){
        TokenDto tokenDto = usuariosService.validate(token);
        System.out.println("validate: "+tokenDto);
        if(tokenDto == null){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(tokenDto);
    }

    @PostMapping("/auth/create")
    public ResponseEntity <Usuarios> create(@RequestBody UsuariosDto dto){
        Usuarios usuarios = usuariosService.save(dto);
        if(usuarios == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(usuarios);

    }


}
