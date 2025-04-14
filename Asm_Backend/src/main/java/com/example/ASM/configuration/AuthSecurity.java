package com.example.ASM.configuration;

import java.util.NoSuchElementException;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.example.ASM.entity.User;
import com.example.ASM.exception.AppException;
import com.example.ASM.exception.ErrorCode;
import com.example.ASM.service.UserService;

@Configuration
@EnableWebSecurity
public class AuthSecurity {
    @Autowired
    UserService userService;

    @Autowired
    HttpSession session;

    //    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // ✅ GUEST: GET public
                        .requestMatchers(
                                HttpMethod.GET,
                                "/product/**",
                                "/product-type/**",
                                "/category/**",
                                "/product-specification/**",
                                "/specification-type/**")
                        .permitAll()

                        // ✅ GUEST: Auth đăng nhập
                        .requestMatchers("/auth/**")
                        .permitAll()

                        // ✅ USER: Profile, cart, address, order
                        .requestMatchers(
                                "/profile/**",
                                "/cart/**",
                                "/cart-detail/**",
                                "/address/**",
                                "/orders/**",
                                "/orders-detail/**",
                                "/order-status/**",
                                "/image/**")
                        .authenticated()

                        // ✅ USER: CRU của user (không DELETE)
                        .requestMatchers(HttpMethod.GET, "/user/**")
                        .authenticated()
                        .requestMatchers(HttpMethod.POST, "/user/**")
                        .authenticated()
                        .requestMatchers(HttpMethod.PUT, "/user/**")
                        .authenticated()

                        // ✅ ADMIN: Toàn quyền CRUD
                        .requestMatchers(
                                "/product/**",
                                "/productType/**",
                                "/product-specification/**",
                                "/category/**",
                                "/specificationType/**")
                        .hasRole("ADMIN")

                        // ✅ Admin: DELETE mọi thứ (nếu có)
                        .requestMatchers(HttpMethod.DELETE, "/**")
                        .hasRole("ADMIN")

                        // ❌ Còn lại thì không ai được phép
                        .anyRequest()
                        .denyAll())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                //                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:5173"); // Địa chỉ của frontend
        configuration.addAllowedMethod("*"); // Cho phép tất cả các phương thức HTTP (GET, POST, PUT, DELETE...)
        configuration.addAllowedHeader("*"); // Cho phép tất cả các header
        configuration.setAllowCredentials(true); // Cho phép gửi credentials (cookies, authorization headers)

        // Cấu hình các mappings cho CORS
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return email -> {
            try {
                User user = userService.findByEmail(email);

                //                String encodedPassword = passwordEncoder.encode(user.getPassword());
                // lưu ý: chỉ encode khi tạo user, không nên mỗi lần login

                String role = user.isRole() ? "ADMIN" : "USER";

                //                Map<String, Object> authentication = new HashMap<>();
                //                authentication.put("user", user);
                //
                //                byte[] token = (email + ":" + user.getPassword()).getBytes();
                //                authentication.put("token", "Basic " + Base64.getEncoder().encodeToString(token));
                //
                //                session.setAttribute("authentication", authentication);

                return org.springframework.security.core.userdetails.User.withUsername(email)
                        .password(user.getPassword())
                        .roles(role) // Spring sẽ tự thêm ROLE_ prefix
                        .build();

            } catch (NoSuchElementException e) {
                throw new AppException(ErrorCode.USER_NOT_EXISTED);
            }
        };
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // BCrypt sẽ tự động xử lý prefix {bcrypt}
    }
}
