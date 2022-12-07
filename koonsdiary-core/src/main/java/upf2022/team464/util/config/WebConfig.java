package upf2022.team464.util.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import upf2022.team464.common.interceptor.JwtInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .maxAge(3000);
    }

    private static final String[] INTERCEPTOR_WHITE_LIST = {
            "/error",
            // /error 를 화이트리스트로 등록하지 않으면 서버 에러시 /error 호출하면서 인터셉터가 다시 실행된다
            // 2020-12-25 18:28 huiya
            "/", "/status",
            "/images/*",
            "/account/signup-check/email",
            "/account/signup",
            "/auth/signin",
            "/auth/refresh",
            "/user"
    };

    @Autowired
    JwtInterceptor jwtInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(INTERCEPTOR_WHITE_LIST);
    }
}
