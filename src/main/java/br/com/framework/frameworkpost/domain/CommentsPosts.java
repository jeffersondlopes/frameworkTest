package br.com.framework.frameworkpost.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "comments_posts", indexes = {
        @Index(columnList = "post_id", name = "post_id_idx")
})
@SequenceGenerator(name = "user_id_seq", allocationSize = 1)
public class CommentsPosts {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comments_id_seq")
    @Column(updatable = false)
    private Long id;

    @Column(name = "date_created")
    @JsonFormat(shape = JsonFormat.Shape.STRING,
            pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime dateCreate;

    @PrePersist
    private void prePersist(){
        this.dateCreate = LocalDateTime.now();
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns(value = {
            @JoinColumn(name = "post_id", referencedColumnName = "id")
    }, foreignKey = @ForeignKey(name = "posts_fk"))
    @JsonIgnore
    private Posts post;

}
