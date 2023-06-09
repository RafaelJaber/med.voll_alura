package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.user.dto.AuthenticationDataDTO;
import med.voll.api.domain.user.model.User;
import med.voll.api.infrastructure.security.dto.ResponseTokenJwtDTO;
import med.voll.api.infrastructure.security.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity logIn(@RequestBody @Valid AuthenticationDataDTO dto) {
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(dto.login(), dto.password());
        Authentication authentication = manager.authenticate(authenticationToken);

        String tokenJWT = tokenService.generateToken((User) authentication.getPrincipal());

        return ResponseEntity.ok(new ResponseTokenJwtDTO(tokenJWT));

    }

}
