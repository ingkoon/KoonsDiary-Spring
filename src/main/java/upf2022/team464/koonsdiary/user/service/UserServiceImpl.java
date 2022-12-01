package upf2022.team464.koonsdiary.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import upf2022.team464.koonsdiary.user.domain.User;
import upf2022.team464.koonsdiary.user.dto.UserDto;
import upf2022.team464.koonsdiary.user.repository.UserJpaRepository;


@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserJpaRepository userJpaRepository;

    @Override
    public UserDto.Create.ResponseDto addUser(UserDto.Create.RequestDto requestDto) {
        User user = requestDto.toEntity();
        userJpaRepository.save(user);
        return UserDto.Create.ResponseDto.of(user);
    }

    @Override
    public UserDto.Read.ResponseDtoV2 findUser(UserDto.Read.RequestDto requestDto) {
        User user = userJpaRepository.findByAccount(requestDto.getId()).orElse(null);
        return UserDto.Read.ResponseDtoV2.of(user);
    }

    @Override
    public UserDto.Read.ResponseDto findUserList() {
        return null;
    }

    @Override
    public UserDto.Update.ResponseDto modifyUser(UserDto.Update.RequestDto requestDto) {
        return null;
    }

    @Override
    public UserDto.Delete.ResponseDto removeUser(UserDto.Delete.ResponseDto responseDto) {
        return null;
    }

    @Override
    public UserDto.Search.ResponseDto checkId(UserDto.Search.RequestDto requestDto) {
        return null;
    }
}
