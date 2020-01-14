package to2.persistance;

import javax.persistence.*;
import java.util.List;

/**
 * DAO for persisting all users
 */
@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = {"nickname", "email"})})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    private String email;
    private String nickname;
    @Column(name = "send_emails", nullable = false)
    private boolean sendNotification = true;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<GameScore> games;

    public boolean isSendNotification() {
        return sendNotification;
    }

    public void setSendNotification(boolean sendNotification) {
        this.sendNotification = sendNotification;
    }

    @Column(name = "nickname", unique = true, nullable = false)
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Column(name = "email", unique = true, nullable = false)
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

    public int getUserId() {
        return id;
    }

    @Transient
    static public User LOGGED_USER = null;
}

