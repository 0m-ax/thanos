package monster.theundefinedavengers.thanos;

import monster.theundefinedavengers.thanos.auth.service.UserServiceDetailsImpl;
import monster.theundefinedavengers.thanos.auth.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

@SpringBootApplication
public class ThanosApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThanosApplication.class, args);
    }

}
