package to2.persistance;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    private String email;
    private String nickname;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<GameScore> games;


    @Column(name = "nickname", nullable = false, unique = true)
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }


    @Column(name = "email", nullable = false, unique = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User() {
    }

    public User(String nickname, String email) {
        this.nickname = nickname;
        this.email = email;
    }
}

