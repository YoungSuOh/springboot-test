package org.example.springjpa.domain.users.service;

import org.example.springjpa.domain.users.dto.request.UsersRequestDTO;
import org.example.springjpa.domain.users.dto.response.UsersResponseDTO;

public interface UsersService {
    public boolean join(UsersRequestDTO.UsersJoinDTO joinDTO);
    public boolean login(UsersRequestDTO.UsersLoginDTO loginDTO);
    public UsersResponseDTO.UsersMyPageDto getMyPage(Long userId);
}
