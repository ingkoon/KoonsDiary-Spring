package upf2022.team464.user.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import upf2022.team464.common.Enum.Role;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user")
@Builder
@Getter
@ToString
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "user_id", nullable = false)
    private Long id;

    @Column(name = "user_account", unique = true, nullable = false)
    private String account;

    @Column(name = "user_nickname", nullable = false)
    private String nickname;

    @Column(name = "user_password", nullable = false)
    private String password;

    @Column(name = "user_email", nullable = false)
    private String email;

    @Column(name = "user_kakao")
    private Long kakaoToken;

    @Column(name = "user_fcm")
    private String fcmToken;

    @Column(name ="user_role")
    private Role role = Role.USER;

    @Column(name = "user_delete")
    private int delflag = 0;

//    @OneToMany(mappedBy = "user")
//    private List<RefreshToken> refreshToken = new ArrayList<>();

//    @OneToOne
//    @JoinColumn(name = "image_id")
//    private ImagePath imagePath;
//
//
//
//    @Builder.Default
//    @OneToMany(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL)
//    private List<Diary> diaryList = new ArrayList<Diary>();
//
//    @Builder.Default
//    @OneToMany(mappedBy = "user")
//    private List<ShareGroupUser> shareGroupUsers = new ArrayList<ShareGroupUser>();
//
//    @Builder.Default
//    @OneToMany(mappedBy = "user")
//    private List<ShareGroupDiaryComment> shareGroupDiaryComments = new ArrayList<ShareGroupDiaryComment>();

    public User() {
    }

//    public User(Long id, String account, String nickname, String password, String email, Long kakaoToken, String fcmToken) {
//        this.id = id;
//        this.account = account;
//        this.nickname = nickname;
//        this.password = password;
//        this.email = email;
//        this.kakaoToken = kakaoToken;
//        this.fcmToken = fcmToken;
//    }

    public User(Long id, String account, String nickname, String password, String email, Long kakaoToken, String fcmToken, Role role, int delflag) {
        this.id = id;
        this.account = account;
        this.nickname = nickname;
        this.password = password;
        this.email = email;
        this.kakaoToken = kakaoToken;
        this.fcmToken = fcmToken;
        this.role = role;
        this.delflag = delflag;
    }

    // ????????? ????????? ??????
    public void updateNickname(String nickname){
        this.nickname = nickname;
    }

    /*
    // ????????? ????????? ????????????
    public void updateImage(ImagePath imagePath){
        this.imagePath = imagePath;
    }*/

    // ????????? ???????????? ????????????
    public void updatePassword(String password){ this.password = password; }

    public void updateUserDelflag(){
        if(this.delflag == 0) {
            delflag = 1;
            return;
        }
        delflag = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(account, user.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
