package com.prokarma.engineering.customer.publisher.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;


@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Value("${security.access-token-validity}")
  private int accessTokenValidity;

  @Value("${security.access-client}")
  private String securityClient;

  @Value("${security.access-password}")
  private String securitypassword;


  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    endpoints.authenticationManager(authenticationManager);
  }


  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    clients.inMemory().withClient(securityClient)
        .authorizedGrantTypes("client_credentials", "password")
        .authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT").scopes("read", "write", "trust")
        .resourceIds("oauth2-resource").accessTokenValiditySeconds(accessTokenValidity)
        .secret(passwordEncoder.encode(securitypassword));
  }


  @Override
  public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
    security.checkTokenAccess("isAuthenticated()");
  }


}
