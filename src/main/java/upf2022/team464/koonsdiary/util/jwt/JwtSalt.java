package upf2022.team464.koonsdiary.util.jwt;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtSalt {

    @Value("${jwt-salt}")
    private String salt;

    private static JwtSalt instance = new JwtSalt();

    private JwtSalt(){}

    public static JwtSalt getInstance(){
        if(instance == null) instance = new JwtSalt();
        return instance;
    }
}
