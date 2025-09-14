package gr.aueb.cf.bankApp.authentification;

import gr.aueb.cf.bankApp.core.exceptions.AppObjectNotAuthorizedException;
import gr.aueb.cf.bankApp.dto.AuthenticationRequestDTO;
import gr.aueb.cf.bankApp.dto.AuthenticationResponseDTO;
import gr.aueb.cf.bankApp.dto.ResponseMessageDTO;
import gr.aueb.cf.bankApp.model.User;
import gr.aueb.cf.bankApp.repository.UserRepository;
import gr.aueb.cf.bankApp.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthenticationResponseDTO authenticate (AuthenticationRequestDTO dto)throws AppObjectNotAuthorizedException{
        Authentication authentication =authenticationManager.
                authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(),dto.getPassword()));

        User user = userRepository.findByUsername(authentication.getName()).
                orElseThrow(()->new AppObjectNotAuthorizedException("User","User not authorized!"));

        String token = jwtService.generateToken(authentication.getName(), user.getRole().name());

        return new AuthenticationResponseDTO(user.getFirstname(), user.getLastname(),token);

    }
}
