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
    private boolean sendNotification = true;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<GameScore> games;

    @Column(name = "send_notification", nullable = false)
    public boolean isSendNotification() {
        return sendNotification;
    }

    public void setSendNotification(boolean sendNotification) {
        this.sendNotification = sendNotification;
    }

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

    public List<GameScore> getGames() {
        return games;
    }

    public void setGames(List<GameScore> games) {
        this.games = games;
    }

    public User() {
    }

    public User(String nickname, String email, boolean sendNotification) {
        this.nickname = nickname;
        this.email = email;
        this.sendNotification = sendNotification;
    }
}

