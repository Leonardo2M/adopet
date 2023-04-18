package br.com.adopet.api.controller;

import br.com.adopet.api.dto.usuario.LoginDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final AuthenticationManager authenticationManager;

    public LoginController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> efetuarLogin(@Valid @RequestBody LoginDTO dadosLogin) {
        var tokenAuth = new UsernamePasswordAuthenticationToken(dadosLogin.getUsername(), dadosLogin.getPassword());
        var authentication = authenticationManager.authenticate(tokenAuth);

        return ResponseEntity.ok().body("logado");
    }

}