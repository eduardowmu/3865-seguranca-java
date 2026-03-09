package med.voll.web_application.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class ConfiguracoesSeguranca {
    @Bean
    public SecurityFilterChain filtrosSeguranca(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(req -> {
                            req.requestMatchers("/css/**", "/js/**", "/assets/**").permitAll();
                            req.anyRequest().authenticated();
                        }
                ).formLogin(f -> f.loginPage("/login")
                        .defaultSuccessUrl("/")
                        .permitAll())
                .logout(logout -> logout.logoutSuccessUrl("/login?logout")
                        .permitAll())
                .rememberMe(r -> r.key("rememberMe")
                        .alwaysRemember(true)
                //        .tokenValiditySeconds()
                ).csrf(Customizer.withDefaults())
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
