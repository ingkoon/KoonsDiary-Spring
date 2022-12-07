package upf2022.team464.common.interceptor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import upf2022.team464.common.exception.UnAuthorizedException;
import upf2022.team464.util.jwt.JwtService;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtInterceptor implements HandlerInterceptor {

    private static final String HEADER = "Authorization";
    private final JwtService jwtService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final String token = request.getHeader(HEADER);
        if(token != null && jwtService.checkToken(token)){
            log.info("토큰 사용 가능 : {}", token);
            return true;
        }else{
            log.info("토큰 사용 불가능 : {}", token);
            throw new UnAuthorizedException();
        }
    }
}
