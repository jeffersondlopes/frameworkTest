package br.com.framework.frameworkpost.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "posts", indexes = {
        @Index(columnList = "user_id", name = "user_id_idx")
})
@SequenceGenerator(name = "post_id_seq", allocationSize = 1)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "posts_id_seq")
    @Column(updatable = false)
    private Long id;

    @Column(name = "date_created")
    @JsonFormat(shape = JsonFormat.Shape.STRING,
            pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime dateCreate;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private boolean active;

    @PrePersist
    private void prePersist(){
        this.dateCreate = LocalDateTime.now();
        this.active = true;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns(value = {
            @JoinColumn(name = "user_id", referencedColumnName = "id")
    }, foreignKey = @ForeignKey(name = "user_fk"))
    @JsonIgnore
    private User users;

}
