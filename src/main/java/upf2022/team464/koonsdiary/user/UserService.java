package upf2022.team464.koonsdiary.user;

import upf2022.team464.koonsdiary.user.dto.UserDto;

public interface UserService {
    public UserDto.Create.ResponseDto addUser(UserDto.Create.RequestDto requestDto);
    public UserDto.Read.ResponseDto findUser(UserDto.Read.RequestDto requestDto);
    public UserDto.Read.ResponseDto findUserAll();
    public UserDto.Update.ResponseDto modifyUser(UserDto.Update.RequestDto requestDto);
    public UserDto.Delete.ResponseDto removeUser(UserDto.Delete.ResponseDto responseDto);
}
