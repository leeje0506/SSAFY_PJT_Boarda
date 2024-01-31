package site.gongtong.review.model;


import jakarta.persistence.*;
import lombok.*;
import site.gongtong.boardgame.model.BoardGame;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Tag {

    @Id
    @Column(name="tag_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="review_id")
    private Review review;

    @ManyToOne
    @JoinColumn(name="game_id")
    private BoardGame game;
}
