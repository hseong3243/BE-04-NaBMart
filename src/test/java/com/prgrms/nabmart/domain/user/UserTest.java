package com.prgrms.nabmart.domain.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.prgrms.nabmart.domain.user.exception.InvalidNicknameException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class UserTest {

    @Nested
    @DisplayName("User 생성 시")
    class NewUserTest {

        @ParameterizedTest
        @CsvSource({
                "가나다", "abc", "123", "가나다123", "abc123", "가나다abc123", "가나다abc123!@#"
        })
        @DisplayName("성공: 유효한 User 닉네임")
        void success(String validNickname) {
            //given
            //when
            User newUser = new User(validNickname, "provider", "123", UserRole.ROLE_USER);

            //then
            assertThat(newUser.getNickname()).isEqualTo(validNickname);
        }

        @Test
        @DisplayName("예외: 200자를 초과한 닉네임")
        void throwExceptionWhenInvalidNickname() {
            //given
            String invalidNickname = "a".repeat(201);
            //when
            //then
            assertThatThrownBy(() -> new User(invalidNickname, "provider", "123", UserRole.ROLE_USER))
                    .isInstanceOf(InvalidNicknameException.class);
        }
    }
}
