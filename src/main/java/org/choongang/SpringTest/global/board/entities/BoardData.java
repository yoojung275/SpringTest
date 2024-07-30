package org.choongang.SpringTest.global.board.entities;

import jakarta.persistence.*;
import lombok.*;
import org.choongang.SpringTest.global.entities.BaseEntity;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class BoardData extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    @Lob
    private String content;

}
