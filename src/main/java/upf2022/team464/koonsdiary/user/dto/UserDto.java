package upf2022.team464.koonsdiary.user.dto;

import lombok.*;
import upf2022.team464.koonsdiary.user.domain.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UserDto {

    public static class Create{
        @AllArgsConstructor
        @NoArgsConstructor
        @ToString
        @Builder
        @Getter
        public static class RequestDto{
            @NotBlank(message = "아이디를 올바르게 입력해 주세요")
            private String account;
            @NotBlank(message = "비밀번호를 제대로 입력해 주세요")
            private String password;
            @NotBlank(message = "이메일을 제대로 입력해 주세요")
            private String email;
            @NotBlank(message = "닉네임을 제대로 입력해 주세요")
            private String nickname;
            @NotNull(message = "이미지를 제대로 설정해 주세요.")
            private Long imageId;

//            public User toEntity(String password, ImagePath imagePath){
//                User user = User.builder().account(account)
//                        .password(password)
//                        .email(email)
//                        .nickname(nickname)
////                        .imagePath(imagePath)
//                        .build();
//                return user;
//            }

            public User toEntity(String password){
                User user = User.builder().account(account)
                        .password(password)
                        .email(email)
                        .nickname(nickname)
                        .build();
                return user;
            }
        }

        @AllArgsConstructor
        @NoArgsConstructor
        @ToString
        @Builder
        @Getter
        public static class ResponseDto{
            private Long id;
            private String userId;
            private String refreshToken;
            private String accessToken;

            public static ResponseDto of(User user, String accessToken){
                return ResponseDto.builder()
                        .id(user.getId())
                        .userId(user.getAccount())
                        .accessToken(accessToken)
                        .build();
            }
        }
    }

    public static class Read{
        @AllArgsConstructor
        @NoArgsConstructor
        @ToString
        @Builder
        @Getter
        public static class RequestDto{
            private String id;
            private String password;
        }


        @AllArgsConstructor
        @NoArgsConstructor
        @ToString
        @Builder
        @Getter
        public static class ResponseDto{
            private String account;
            private String accessToken;
            private String refreshToken;

            public static ResponseDto of(User user, String accessToken){
                return ResponseDto.builder()
                        .account(user.getAccount())
                        .accessToken(accessToken)
                        .build();
            }
        }

        @AllArgsConstructor
        @NoArgsConstructor
        @ToString
        @Builder
        @Getter
        public static class ResponseDtoV2{
            private String account;
            private String nickname;
            private String email;

            public static ResponseDtoV2 of(User user){
                return ResponseDtoV2.builder()
                        .account(user.getAccount())
                        .nickname(user.getNickname())
                        .email(user.getEmail())
                        .build();
            }
        }
    }
    public static class Update{

        @AllArgsConstructor
        @NoArgsConstructor
        @ToString
        @Builder
        @Getter
        public static class RequestDto{
            private String nickname;
            private String password;
            private Long imageId;
        }

        @AllArgsConstructor
        @NoArgsConstructor
        @ToString
        @Builder
        @Getter
        public static class ResponseDto{
            private String nickname;
//            private Long imageId;

//            public static ResponseDto of(User user){
//                return ResponseDto.builder()
//                        .nickname(user.getNickname())
//                        .imageId(user.getImagePath().getId())
//                        .build();
//            }

            public static ResponseDto of(User user){
                return ResponseDto.builder()
                        .nickname(user.getNickname())
                        .build();
            }
        }
    }
    public  static class Delete{
        @AllArgsConstructor
        @NoArgsConstructor
        @ToString
        @Builder
        @Getter
        public static class ResponseDto{
            private String message;
            public static ResponseDto of(){
                return ResponseDto.builder().message("탈퇴 성공").build();
            }
        }
    }

    public static class Search{

        @AllArgsConstructor
        @NoArgsConstructor
        @ToString
        @Builder
        @Getter
        public static class ResponseDto{
            private String userList;

            public static ResponseDto of(String userList){
                return ResponseDto.builder().userList(userList).build();
            }
        }
    }
}

