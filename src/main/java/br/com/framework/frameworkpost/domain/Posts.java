package br.com.framework.frameworkpost.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "posts", indexes = {
        @Index(columnList = "email", name = "email_user_idx")
})
@SequenceGenerator(name = "user_id_seq", allocationSize = 1)
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "posts_id_seq")
    @Column(updatable = false)
    private Long id;

    @Column(name = "date_created")
    private LocalDateTime dateCreate;

    @Column(nullable = false)
    private String comment;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns(value = {
            @JoinColumn(name = "user_id", referencedColumnName = "id")
    }, foreignKey = @ForeignKey(name = "user_fk"))
    @JsonIgnore
    private User users;

}
