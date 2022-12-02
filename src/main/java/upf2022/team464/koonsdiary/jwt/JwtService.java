package upf2022.team464.koonsdiary.jwt;

import java.util.Map;

public interface JwtService {
    <T> String createAccessToken(String key, T data);
    <T> String createRefreshToken(String key, T data);
    <T> String create(String key, T data, String subject, long expire);
    Map<String, Object> get(String key);
    String getUserId();
    boolean checkToken(String jwt);
}
