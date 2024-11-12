package org.example.springjpa.domain.users.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UsersRequestDTO {

    @Builder
    @Getter
    @AllArgsConstructor
    public static class UsersJoinDTO {
        @NotNull
        private String userId;
        @NotNull
        private String password;
        @NotNull
        private String email;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class UsersLoginDTO {
        @NotNull
        private String userId;
        @NotNull
        private String password;
    }

}
