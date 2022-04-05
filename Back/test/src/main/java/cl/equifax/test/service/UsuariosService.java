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

        if(!usuarios.isPresent()){
            return null;
        }
        if(passwordEncoder.matches(usuariosDto.getPassword(),usuarios.get().getPassword())){
            return new TokenDto(jwtProvider.CreateToken(usuarios.get()));
        }
        return null;
    }


    public TokenDto validate(String token){
        if(!jwtProvider.validate(token)){
            return null;
        }
        String username = jwtProvider.getUserNameFromToken(token);
        if(!usuariosRepository.findByUsername(username).isPresent()){
            return null;
        }
        return new TokenDto(token);
    }

}
