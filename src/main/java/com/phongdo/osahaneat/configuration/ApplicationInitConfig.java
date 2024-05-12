package com.phongdo.osahaneat.configuration;

import com.phongdo.osahaneat.entity.Roles;
import com.phongdo.osahaneat.entity.Users;
import com.phongdo.osahaneat.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApplicationInitConfig {

    PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository){
        return args -> {
            if(userRepository.findByUserName("admin").isEmpty()){
                var roles = new Roles();
                //roles.setId(1);

                Users users = Users.builder()
                        .fullname("admin")
                        .userName("admin")
                        .password(passwordEncoder.encode("admin"))
                        //.roles(roles)
                        .build();
                userRepository.save(users);
                log.warn("admin user has been created with default password: admin, please change it");
            }
        };
    }

}
