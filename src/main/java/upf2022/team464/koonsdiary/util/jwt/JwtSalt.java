package upf2022.team464.koonsdiary.util.jwt;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

//@Configuration
@Getter
@PropertySource("classpath:/application-jwt.yml")
public class JwtSalt {

    @Value("$jwt.salt}")
    private String salt;

    private static JwtSalt instance = new JwtSalt();

    private JwtSalt(){}

    public static JwtSalt getInstance(){
        if(instance == null) instance = new JwtSalt();
        return instance;
    }
}
