package to2.persistance;

import javax.persistence.*;
import to2.persistance.User;


/**
 * DAO responsible for persisting GameScore
 */
@Entity
@Table(name="gamescores")
public class GameScore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="game_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private int score;


    @Column(name="score", nullable = false, unique = true)
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


    @Column(name="userId", nullable = false, unique = true)
    public User getUser(){
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}

