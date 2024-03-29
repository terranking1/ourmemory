package myproject.ourmemory.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import myproject.ourmemory.domain.User;
import org.springframework.security.core.Authentication;

@Entity
@Getter @Setter
@Table(name = "refreshToken")
public class RefreshToken {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "refresh_token_id")
    private Long id;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    private String refreshToken;

    public RefreshToken() {
    }

    @Builder
    public RefreshToken(User user, String refreshToken) {
        this.user = user;
        this.refreshToken = refreshToken;
    }

    public void updateToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
