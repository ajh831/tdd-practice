package com.example.demo.user.infrastructure;

import com.example.demo.user.domain.UserStatus;
import com.example.demo.user.infrastructure.UserEntity;
import com.example.demo.user.infrastructure.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/* ExtendWith는 DataJpaTest에 내장되어 있음 */
//@ExtendWith(SpringExtension.class)
/* TestPropertySource는 어차피 기본 설정을 따라도 되므로 필요 없음 */
//@TestPropertySource("classpath:test-application.properties")
@Sql("/sql/user-repository-test-data.sql")
@DataJpaTest(showSql = true)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

//    @Test
//    public void UserRepository_가_제대로_연결되었다() {
//        // given
//        UserEntity userEntity = new UserEntity();
//        userEntity.setEmail("kok202@naver.com");
//        userEntity.setAddress("Seoul");
//        userEntity.setNickname("kok202");
//        userEntity.setStatus(UserStatus.ACTIVE);
//        userEntity.setCertificationCode("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa");
//
//        // when
//        UserEntity result = userRepository.save(userEntity);
//
//        // then
//        assertThat(result.getId()).isNotNull();
//    }

    @Test
    public void findByIdAndStatus_로_유저_데이터를_찾아올_수_있다() {
        // given
        // when
        Optional<UserEntity> result = userRepository.findByIdAndStatus(1, UserStatus.ACTIVE);

        // then
        assertThat(result.isPresent()).isTrue();

    }

    @Test
    public void findByIdAndStatus_는_데이터가_없으면_Optional_empty_를_내려준다() {
        // given
        // when
        Optional<UserEntity> result = userRepository.findByIdAndStatus(1, UserStatus.PENDING);

        // then
        assertThat(result.isEmpty()).isTrue();

    }

    @Test
    public void findByEmailAndStatus_로_유저_데이터를_찾아올_수_있다() {
        // given
        // when
        Optional<UserEntity> result = userRepository.findByEmailAndStatus("kok202@naver.com", UserStatus.ACTIVE);

        // then
        assertThat(result.isPresent()).isTrue();

    }

    @Test
    public void findByEmailAndStatus_는_데이터가_없으면_Optional_empty_를_내려준다() {
        // given
        // when
        Optional<UserEntity> result = userRepository.findByEmailAndStatus("kok202@naver.com", UserStatus.PENDING);

        // then
        assertThat(result.isEmpty()).isTrue();

    }


}