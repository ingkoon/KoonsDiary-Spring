package upf2022.team464.koonsdiary.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import upf2022.team464.koonsdiary.user.domain.User;
import upf2022.team464.koonsdiary.user.repository.UserJpaRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class repositoryTest {

    @Autowired
    UserJpaRepository userJpaRepository;

    @Test
    @DisplayName("user : create : success")
    void create_user_success(){
        //given
        User user = User.builder()
                .account("test")
                .email("test@test.com")
                .password("test")
                .nickname("test")
                .build();

        // when
        userJpaRepository.save(user);

        //then
        System.out.println("user.getId() = "+ user.getId());
        User findUser = userJpaRepository.findById(user.getId()).get();
        Assertions.assertEquals(user, findUser);
        System.out.println("insert User is " + user + "find User is" + findUser);
    }

    @Test
    @DisplayName("user : read : success")
    void read_user_success(){
        //given
        User user = User.builder()
                .account("test")
                .email("test@test.com")
                .password("test")
                .nickname("test")
                .build();

        // when
        userJpaRepository.save(user);

        //then
        System.out.println("user.getId() = "+ user.getId());
        User findUser = userJpaRepository.findById(user.getId()).get();
        Assertions.assertEquals(user, findUser);
        System.out.println("insert User is " + user + "find User is" + findUser);
    }

    @Test
    @DisplayName("user : update : success")
    void update_user_success(){
        // given
        User user = User.builder()
                .account("test")
                .email("test@test.com")
                .password("test")
                .nickname("test")
                .build();

        String updateNickName = "test2";
        // when
        userJpaRepository.save(user);
        user.updateNickname(updateNickName);
        userJpaRepository.save(user);

        // then
        System.out.println("user.getId() = "+ user.getId());
        User findUser = userJpaRepository.findById(user.getId()).get();
        List<User> list = userJpaRepository.findAll();
        Assertions.assertEquals(user, findUser);
        System.out.println("insert User is " + user + "find User is" + findUser);

        System.out.println(list);
    }

    @Test
    @DisplayName("user : delete : success")
    void delete_user_success(){
        // given
        User user = User.builder()
                .account("test")
                .email("test@test.com")
                .password("test")
                .nickname("test")
                .build();

        // when
        userJpaRepository.save(user);
//        userJpaRepository.delete(user);
        userJpaRepository.deleteById(user.getId());

        // then
        List<User> list = userJpaRepository.findAll();
        assertThat(list).isEmpty();
        System.out.println("list is empty " + list.size());
    }

    @Test
    @DisplayName("user: search by name success")
    void search_user_success(){
        // given
        User user = User.builder()
                .account("test")
                .email("test@test.com")
                .password("test")
                .nickname("test")
                .build();

        // when
        userJpaRepository.save(user);
        User findUser = userJpaRepository.findByAccount(user.getAccount()).orElse(null);

        // then
       assertThat(findUser).isNotNull();
    }
}
