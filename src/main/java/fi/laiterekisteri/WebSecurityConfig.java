package fi.laiterekisteri;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import fi.laiterekisteri.service.UserDetailServiceImpl;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class WebSecurityConfig {
	@Autowired
	private UserDetailServiceImpl userDetailsService;
	
	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		return http.authorizeRequests(auth -> {
			
			// everyone can access css
			auth.antMatchers("/css/**").permitAll();
			// only admin can access admin-folder (not used)
			auth.antMatchers("/admin/**").hasAuthority("ADMIN");
			// only admin can access h2-console
			auth.antMatchers("/h2-console").hasAuthority("ADMIN");
			auth.anyRequest().authenticated();
		})
				// H2 login
				// below configuration is demanded if you want to use h2-console
				.headers().frameOptions().disable().and()
				// and this one for h2-console
				.csrf().ignoringAntMatchers("/h2-console/**").and()
				//default App login
				.formLogin().defaultSuccessUrl("/", true).and()
				.logout().permitAll().and()
				.httpBasic(Customizer.withDefaults()).build();		
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
}