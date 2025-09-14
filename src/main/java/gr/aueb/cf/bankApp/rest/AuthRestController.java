package gr.aueb.cf.bankApp.rest;

import gr.aueb.cf.bankApp.authentification.AuthenticationService;
import gr.aueb.cf.bankApp.core.exceptions.AppObjectNotAuthorizedException;
import gr.aueb.cf.bankApp.dto.AuthenticationRequestDTO;
import gr.aueb.cf.bankApp.dto.AuthenticationResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/auth")
public class AuthRestController {

    private final AuthenticationService authenticationService;
    private final Logger logger = LoggerFactory.getLogger(AuthRestController.class);

    @PostMapping("/authentication")
    public ResponseEntity<AuthenticationResponseDTO> authenticate(@RequestBody AuthenticationRequestDTO dto)
            throws AppObjectNotAuthorizedException{
            AuthenticationResponseDTO authenticationResponseDTO = authenticationService.authenticate(dto);
            logger.info("User authenticated!");
            return new ResponseEntity<>(authenticationResponseDTO, HttpStatus.OK);

    }
}
