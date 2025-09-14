package gr.aueb.cf.bankApp.authentification;

import gr.aueb.cf.bankApp.core.exceptions.ObjectNotFoundException;
import gr.aueb.cf.bankApp.model.User;
import gr.aueb.cf.bankApp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(()->
                new UsernameNotFoundException("User with username:" + username +" not found!"));
    }


}
