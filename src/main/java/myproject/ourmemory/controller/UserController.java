package myproject.ourmemory.controller;

import lombok.RequiredArgsConstructor;
import myproject.ourmemory.domain.User;
import myproject.ourmemory.domain.UserRole;
import myproject.ourmemory.dto.user.*;
import myproject.ourmemory.exception.NotUser;
import myproject.ourmemory.jwt.JwtToken;
import myproject.ourmemory.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    /**
     * 로그인
     */
    @PostMapping("/users/login")
    public JwtToken login(@RequestBody Map<String, String> loginForm) {
        JwtToken token = userService.login(loginForm.get("email"), loginForm.get("password"));

        return JwtToken.builder()
                .grantType(token.getGrantType())
                .accessToken(token.getAccessToken())
                .refreshToken(token.getRefreshToken())
                .build();
    }

    /**
     * 회원 단건 조회
     */
    @GetMapping("/users/{userId}")
    public UserDto user(@PathVariable Long userId) {
        User findUser = userService.findOneUser(userId);

        return new UserDto(findUser);
    }

    /**
     * 회원 페이징 조회
     */
    @GetMapping("/users") //todo 페이지 수 추가했음
    public GetUserResponse users(@ModelAttribute GetUserRequest request) {
        List<User> findUsers = userService.findUsers(request);
        int totalPages = userService.getPages(request);

        List<UserDto> collect = findUsers.stream()
                .map(UserDto::new)
                .toList();

        GetUserResponse result = GetUserResponse.builder()
                .totalPages(totalPages)
                .users(collect)
                .build();

        return result;
    }

    /**
     * 회원 등록
     */
    @PostMapping("/users")
    public CreateUserResponse createUser(@Valid @RequestBody CreateUserRequest request) {
        Long userId = userService.join(request);

        return new CreateUserResponse(userId);
    }

    /**
     * 회원 수정
     */
    @PostMapping("/users/{userId}")
    public UpdateUserResponse updateUser(@PathVariable Long userId, @RequestBody @Valid UpdateUserRequest request) {
        userService.updateNickName(userId, request);

        return new UpdateUserResponse(userId);
    }

    /**
     * 회원 삭제
     */
    @DeleteMapping("/users/{userId}")
    public DeleteUserResponse deleteUser(@PathVariable Long userId) {
        userService.delete(userId);

        return new DeleteUserResponse(userId);
    }
}
