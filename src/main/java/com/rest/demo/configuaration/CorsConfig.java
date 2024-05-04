//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//
//@EnableWebSecurity
//public class SecurityConfigFilterChain {
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//            .csrf().disable()
//            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//            .and()
//            .authorizeRequests().antMatchers("/api/login/**", "/api/register/**").permitAll()
//            .anyRequest().authenticated()
//            .and()
//            .addFilter(new JwtAuthenticationFilter(authenticationManagerBean()))
//            .addFilterBefore(new JwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
//        return http.build();
//    }
//
//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//}
