package myproject.ourmemory.controller;

import lombok.RequiredArgsConstructor;
import myproject.ourmemory.domain.RefreshToken;
import myproject.ourmemory.domain.User;
import myproject.ourmemory.dto.refreshToken.RefreshTokenRequest;
import myproject.ourmemory.dto.user.*;
import myproject.ourmemory.exception.RefreshTokenNotFound;
import myproject.ourmemory.exception.UserNotFound;
import myproject.ourmemory.jwt.JwtToken;
import myproject.ourmemory.jwt.JwtTokenProvider;
import myproject.ourmemory.repository.RefreshTokenRepository;
import myproject.ourmemory.repository.UserRepository;
import myproject.ourmemory.service.RefreshTokenService;
import myproject.ourmemory.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final RefreshTokenService refreshTokenService;


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
     * 로그아웃
     */
    @DeleteMapping("/users/logout/{userId}")
    public void logout(@PathVariable Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFound::new);
        refreshTokenService.deleteToken(user);
    }

    /**
     * 토큰 재발급
     */
    @PostMapping("/users/refresh/{userId}")
    public ResponseEntity<JwtToken> refresh(@PathVariable Long userId, @RequestBody RefreshTokenRequest request) {
        String refreshToken = request.getRefreshToken();
        RefreshToken savedToken = refreshTokenRepository.findRefreshTokenByRefreshToken(refreshToken)
                .orElseThrow(RefreshTokenNotFound::new);
        User user = savedToken.getUser();
        if (Objects.equals(user.getId(), userId)) {
            if (refreshToken != null && jwtTokenProvider.validateToken(refreshToken)) {
                Authentication authentication = jwtTokenProvider.getAuthentication(refreshToken);
                JwtToken newToken = jwtTokenProvider.generateToken(userId, authentication);
                String accessToken = newToken.getAccessToken();
                if (accessToken != null && jwtTokenProvider.validateToken(accessToken)) {
                    refreshTokenService.update(savedToken, newToken.getRefreshToken());
                    return new ResponseEntity<>(newToken, HttpStatus.OK);
                }
            }
        }

        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
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
    public DeleteUserResponse deleteUser(@PathVariable Long userId, @RequestParam String password) {
        userService.delete(userId, password);

        return new DeleteUserResponse(userId);
    }
}
