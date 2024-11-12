package org.example.springjpa.domain.users.service;


import lombok.RequiredArgsConstructor;
import org.example.springjpa.domain.users.dto.request.UsersRequestDTO;
import org.example.springjpa.domain.users.dto.response.UsersResponseDTO;
import org.example.springjpa.domain.users.entity.Users;
import org.example.springjpa.domain.users.repository.UsersRepository;
import org.example.springjpa.global.ApiResponse;
import org.example.springjpa.global.code.resultCode.ErrorStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {
    private final UsersRepository usersRepository;

    @Override
    public boolean join(UsersRequestDTO.UsersJoinDTO joinDTO) {
        Optional<Users>isExist = usersRepository.findUsersByUserId(joinDTO.getUserId());
        if (isExist.isPresent()) {
            return false;
        }
        else{
            Users newUser = new Users();
            newUser.setUserId(joinDTO.getUserId());
            newUser.setPassword(joinDTO.getPassword());
            newUser.setEmail(joinDTO.getEmail());
            usersRepository.save(newUser);
            return true;
        }
    }

    @Override
    public boolean login(UsersRequestDTO.UsersLoginDTO loginDTO) {
        Optional<Users> userOptional = usersRepository.findUsersByUserId(loginDTO.getUserId());
        if (userOptional.isEmpty()) {
            return false; // 사용자 없음
        }
        Users user = userOptional.get();
        if(user.getPassword().equals(loginDTO.getPassword())){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public UsersResponseDTO.UsersMyPageDto getMyPage(Long userId) {
        // 1. 사용자 찾기
        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자를 찾을 수 없습니다."));

        // 2. UsersResponseDTO.UsersMyPageDto로 변환
        return UsersResponseDTO.UsersMyPageDto.builder()
                .userId(user.getUserId())
                .password(user.getPassword()) // 보안을 위해 암호화된 비밀번호 반환은 고려해볼 필요 있음
                .email(user.getEmail())
                .build();
    }
}
