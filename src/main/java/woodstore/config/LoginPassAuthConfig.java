package woodstore.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import woodstore.model.Profile;
import woodstore.service.impl.ProfileService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Viktor_Artemov on 3/3/2017.
 */
@Configuration
public class LoginPassAuthConfig extends GlobalAuthenticationConfigurerAdapter {

    @Autowired
    private ProfileService profileService;

    @Override
    public void init(AuthenticationManagerBuilder authenticationManagerBuilder) {

        authenticationManagerBuilder.authenticationProvider(new AuthenticationProvider() {
            @Override
            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                String login = authentication.getName();
                String password = authentication.getCredentials().toString();

                //check user from database
                Profile user = profileService.findByLogin(login);
                if (user != null && user.getPassword().compareTo(password) == 0) {
                    List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
                    grantedAuthorities.add(new SimpleGrantedAuthority("User"));
                    return new UsernamePasswordAuthenticationToken(login, password, grantedAuthorities);
                } else {
                    return null;
                }
            }

            @Override
            public boolean supports(Class<?> authentication) {
                return authentication.equals(UsernamePasswordAuthenticationToken.class);
            }
        });
    }
}