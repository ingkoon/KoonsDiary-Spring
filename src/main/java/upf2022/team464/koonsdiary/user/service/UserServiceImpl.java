package upf2022.team464.koonsdiary.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import upf2022.team464.koonsdiary.common.exception.UnAuthorizedException;

import upf2022.team464.koonsdiary.user.domain.User;
import upf2022.team464.koonsdiary.user.dto.UserDto;
import upf2022.team464.koonsdiary.user.repository.UserJpaRepository;
import upf2022.team464.koonsdiary.util.jwt.JwtService;

import java.util.List;


@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserJpaRepository userJpaRepository;
    private final JwtService jwtService;
    private final RedisTemplate<String, String> redisTemplate;

    @Override
    @Transactional
    public UserDto.Create.ResponseDto addUser(UserDto.Create.RequestDto requestDto) {
        User user = requestDto.toEntity(); // dto에서 refreshtoken 생성
        userJpaRepository.save(user);
        log.debug("save user {} success", user.getAccount());
        String refreshToken = jwtService.createRefreshToken("account", user.getAccount());
        ValueOperations<String, String> vop = redisTemplate.opsForValue();

        vop.set(user.getAccount(), refreshToken);

        return UserDto.Create.ResponseDto.of(user);
    }

    @Override
    public UserDto.Read.ResponseDtoV2 findUser(UserDto.Read.RequestDto requestDto) {
        User user = userJpaRepository.findByAccount(requestDto.getId()).orElse(null);
        return UserDto.Read.ResponseDtoV2.of(user);
    }

    @Override
    public UserDto.ReadAll.ResponseDto findUserList() {
        List<User> userList = userJpaRepository.findAll();
        return UserDto.ReadAll.ResponseDto.of(userList);
    }

    @Override
    @Transactional
    public UserDto.Update.ResponseDto modifyUser(UserDto.Update.RequestDto requestDto) {
        User user = userJpaRepository.findByNickname(requestDto.getNickname()).orElse(null);
        if (user == null) throw new UnAuthorizedException();

        user.updateNickname(requestDto.getNickname());
        user.updatePassword(requestDto.getPassword());
        userJpaRepository.save(user);

        return UserDto.Update.ResponseDto.of(user);
    }

    @Override
    @Transactional
    public UserDto.Update.ResponseDto modifyUser(UserDto.Update.RequestDto requestDto) {
        return null;
    }

    @Override
    public UserDto.Delete.ResponseDto removeUser(UserDto.Delete.ResponseDto responseDto) {

        return null;
    }

    @Override
    public UserDto.SearchId.ResponseDto checkId(UserDto.SearchId.RequestDto requestDto) {
        boolean result = userJpaRepository.existsByAccount(requestDto.getAccount());
        return UserDto.SearchId.ResponseDto.of(result);
    }

    @Override
    public UserDto.SearchEmail.ResponseDto checkEmail(UserDto.SearchEmail.RequestDto requestDto){
        boolean result = userJpaRepository.existsByEmail(requestDto.getEmail());
        return UserDto.SearchEmail.ResponseDto.of(result);

    }
}
