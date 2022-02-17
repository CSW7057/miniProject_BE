package com.sparta.miniproject0212.controller;


import com.sparta.miniproject0212.dto.*;
import com.sparta.miniproject0212.security.UserDetailsImpl;
import com.sparta.miniproject0212.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController //rest컨트롤러는 json으로 데이터 주고받을때 사용
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    // 홈페이지 이동 API
    @GetMapping ("/")
    public urlDto home (@AuthenticationPrincipal UserDetailsImpl userDetails) {
        if(userDetails ==null) {
            return new urlDto("/");

        }else{
            //model.addAttribute("userId", userDetails.getUser().getId());
            return new urlDto("/");

        }
    }

    // 회원 로그인 페이지 이동
//    @GetMapping("/user/loginView")
//    public String login(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
//        if (userDetails == null) {
//            //return new urlDto("/user/login");
//            return "login";
//        }else {
//            model.addAttribute("userId", userDetails.getUser().getId());
//            //return new urlDto("/user/login");
//            return "login";
//        }
//    }

    @GetMapping("/user/loginView")
    public urlDto login() {
        return new urlDto("/user/loginView");

    }

    // 회원가입 페이지 이동
    @GetMapping("/move/signup")
    public urlDto signup(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails == null) {
            return new urlDto("/move/signup");

        }else {
            //model.addAttribute("userId", userDetails.getUser().getId());
            return new urlDto("/move/signup");

        }
    }


    // 상세페이지 이동 API
    @GetMapping ("/move/detail")
    public urlDto moveUserDetails ( @AuthenticationPrincipal UserDetailsImpl userDetails) {

        if(userDetails ==null) {
            return new urlDto("/move/detail");
        } else{
            //Long userId = userDetails.getUser().getId();
            //model.addAttribute("userId", userDetails.getUser().getId());
            return new urlDto("/move/detail");
        }
    }


    // 회원 가입 요청 처리
    @PostMapping("/user/signup")
    public ErrorMessageDto registerUser(@RequestBody SignupRequestDto signupRequestDto ) {
        System.out.println(signupRequestDto.getNickname());
        System.out.println(signupRequestDto.getPassword());
        System.out.println(signupRequestDto.getUsername());

        try{
            userService.registerUser(signupRequestDto);

        } catch (Exception e) {
            String error = e.getMessage();

            ErrorMessageDto errorMessageDto = new ErrorMessageDto("NG",error,"signup");
            System.out.println("signup NG");
            return errorMessageDto;

        }
        ErrorMessageDto errorMessageDto = new ErrorMessageDto("OK","null","signup");
        System.out.println("signup OK");
        return errorMessageDto;

    }

    // 로그인 유저 정보 확인
    @PostMapping ("/check/user")
    public UserCheckDto userLoginCheck (@AuthenticationPrincipal UserDetailsImpl userDetails) {
        System.out.println(userDetails);

        if (userDetails!=null) {
            String username = userDetails.getUsername();
            String nickname = userDetails.getNickname();
            Long userId = userDetails.getUserid();
            boolean check = true;

            return new UserCheckDto(username,nickname, userId,check);

        } else {
            String username = "null";
            String nickname = "null";
            Long userId = 0L;
            boolean check = false;
            return new UserCheckDto(username,nickname, userId,check);
        }
    }

    // 회원 가입시 유저네임 중복 체크 버튼
    @PostMapping("/user/checkid")
    public String checkName(@RequestBody SignupRequestDto signupRequestDto) {
        String username = signupRequestDto.getUsername();
        System.out.println("중복확인, 유저네임은 : " + username);
        String check = userService.usernameCheck(username);
        return check;
    }

    // 회원 가입시 닉네임 중복 체크 버튼
    @PostMapping("/user/checknik")
    public String checkNik(@RequestBody SignupRequestDto signupRequestDto) {
        String nickname = signupRequestDto.getNickname();
        System.out.println("중복확인, 닉네임은 : " + nickname);
        String check = userService.nicknameCheck(nickname);
        return check;
    }




//    // 회원 관련 정보 받기
//    @PostMapping("/user/userinfo")
//    @ResponseBody
//    public UserInfoDto getUserInfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
//        String username = userDetails.getUser().getUsername();
//
//        return new UserInfoDto(username);
//    }

    }








