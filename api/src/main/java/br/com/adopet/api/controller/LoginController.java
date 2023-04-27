package br.com.adopet.api.controller;

import br.com.adopet.api.domain.model.usuario.Usuario;
import br.com.adopet.api.domain.service.security.TokenService;
import br.com.adopet.api.dto.usuario.LoginDTO;
import br.com.adopet.api.dto.usuario.TokenDTO;
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
    private final TokenService tokenService;

    public LoginController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> efetuarLogin(@Valid @RequestBody LoginDTO dadosLogin) {
        var tokenAuth = new UsernamePasswordAuthenticationToken(dadosLogin.getUsername(), dadosLogin.getPassword());
        var authentication = authenticationManager.authenticate(tokenAuth);

        var token = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok().body(new TokenDTO(token));
    }

}