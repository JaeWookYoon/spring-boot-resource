package com.jwyoon.www.oauth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.jwyoon.www.common.util.BCUtils;

/**
 * @author user API Server
 */
@Configuration // API �꽌踰� �씤利�, 沅뚰�? �꽕�젙
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    private String[] auths = new String[]{"ROLE_USER", "ROLE_ADMIN", "ROLE_SECOND_ON", "ROLE_SECOND_OFF", "ROLE_PHONE", "ROLE_OTP", "ROLE_EMAIL", "ROLE_ACCOUNT"};
    private String[] allowHeader = new String[] {"http://localhost:8081","http://localhost:8080"};
    private AccessDeniedHandler accessDeniedHandler;

    @Resource(name = "dataSource")
    private DataSource dataSource;

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        List<String> allowOrigin = new ArrayList<>();
        allowOrigin.addAll(Arrays.asList(allowHeader));
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(allowOrigin);
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }   

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        //resources.tokenServices(tokenService());
        resources.tokenStore(jdbcTokenStore(dataSource));
    }

    @Bean
    public JdbcTokenStore jdbcTokenStore(DataSource dataSource) {
        return new JdbcTokenStore(dataSource);
    }

	private static class OAuthRequestedMatcher implements RequestMatcher {
		public boolean matches(HttpServletRequest request) {
			System.out.println(BCUtils.nowTime() + "matches");
			String auth = request.getHeader("Authorization");
			// Determine if the client request contained an OAuth Authorization
			boolean haveOauth2Token = (auth != null) && auth.startsWith("Bearer");
			boolean haveAccessToken = request.getParameter("access_token") != null;
			return haveOauth2Token || haveAccessToken;
		}
	}

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable().and().cors().and().authorizeRequests()
                .antMatchers("/secured/**").permitAll()
                .antMatchers("/public/**").permitAll()
                .antMatchers("/api/token").permitAll()                
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/private/**").hasAnyAuthority(auths)
                .antMatchers("/403").permitAll()
                .anyRequest().authenticated();        

    }
}