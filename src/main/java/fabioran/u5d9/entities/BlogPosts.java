package fabioran.u5d9.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "blogposts")
public class BlogPosts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String category;
    private String title;
    private String cover;
    private String content;
    private double readingTime;

    @ManyToOne
    @JoinColumn(name = "authorId")
    private Authors authors;
}

/*
{
"authorId": 1,
        "category": "Attualità",
        "content": "lorem ipsum dolor sit amen",
        "readingTime": 4.5,
        "title": "Prova"
        }
 */

