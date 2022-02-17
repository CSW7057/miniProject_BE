package com.sparta.miniproject0212.service;


import com.sparta.miniproject0212.dto.SignupRequestDto;
import com.sparta.miniproject0212.model.User;
import com.sparta.miniproject0212.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    // 회원가입 시 유저네임 중복 및 닉네임 중복, 패스워드 암호화 함
    public void registerUser(SignupRequestDto signupRequestDto ) {

        String username = checkUsername(signupRequestDto);
        String nickname = checkNickname(signupRequestDto);

        // 패스워드 암호화
        String encodepassword = passwordEncoder.encode(signupRequestDto.getPassword());

        User user = new User(username,nickname,encodepassword);
        userRepository.save(user);
    }


    // username 중복 함수
    public String checkUsername(SignupRequestDto signupRequestDto) {
        String username = signupRequestDto.getUsername();
        Optional<User> found = userRepository.findByUsername(username);

        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자 ID 가 존재합니다.");
        }
        return username;
    }

    // nickname 중복 함수
    public String checkNickname(SignupRequestDto signupRequestDto) {
        String nickname = signupRequestDto.getNickname();
        Optional<User> found = userRepository.findByNickname(nickname);

        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자 닉네임이 존재합니다.");
        }
        return nickname;
    }



    //회원가입 시 유저네임 중복 체크버튼
    public String usernameCheck(String  username) {
        if(userRepository.findByUsername(username).isPresent()) {
            return "false";
        } else {
            return "true";
        }

    }

    //회원가입 시 닉네임 중복 체크
    public String nicknameCheck(String nicknameCheck) {
        if(userRepository.findByNickname(nicknameCheck).isPresent()) {
            return "false";
        } else {
            return "true";
        }

    }


}
