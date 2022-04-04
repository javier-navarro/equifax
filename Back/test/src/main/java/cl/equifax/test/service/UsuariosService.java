package cl.equifax.test.service;

import cl.equifax.test.dto.TokenDto;
import cl.equifax.test.dto.UsuariosDto;
import cl.equifax.test.entity.Usuarios;
import cl.equifax.test.repository.UsuariosRepository;
import cl.equifax.test.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuariosService {

    @Autowired
    UsuariosRepository usuariosRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtProvider jwtProvider;


    public Usuarios save(UsuariosDto usuariosDto){

        Optional<Usuarios> usuarios = usuariosRepository.findByUsername(usuariosDto.getUsername());
        if(!usuarios.isPresent()){
            return null;
        }
        String password = passwordEncoder.encode(usuariosDto.getPassword());
        Usuarios usuarios1 = Usuarios.builder()
                .username(usuariosDto.getUsername())
                .password(password)
                .build();

        return usuariosRepository.save(usuarios1);
    }

    public TokenDto login(UsuariosDto usuariosDto){
        Optional<Usuarios> usuarios = usuariosRepository.findByUsername(usuariosDto.getUsername());
        System.out.println("salida service: "+usuarios);
        System.out.println("match: "+passwordEncoder.matches(usuariosDto.getPassword(),usuarios.get().getPassword()));
        System.out.println("Entity: "+usuarios.get().getPassword());

        System.out.println("Encode: "+passwordEncoder.encode(usuarios.get().getPassword()));
        System.out.println("DTO: "+usuariosDto.getPassword());
        if(!usuarios.isPresent()){
            return null;
        }
        if(passwordEncoder.matches(usuariosDto.getPassword(),usuarios.get().getPassword())){
            System.out.println("poraqui");
            return new TokenDto(jwtProvider.CreateToken(usuarios.get()));
        }
        return null;
    }


    public TokenDto validate(String token){
        System.out.println("token in: "+token);
        if(!jwtProvider.validate(token)){
            System.out.println("jwt - 1");
            return null;
        }
        String username = jwtProvider.getUserNameFromToken(token);
        if(!usuariosRepository.findByUsername(username).isPresent()){
            System.out.println("jwt - 2");
            return null;
        }
        return new TokenDto(token);
    }

}
