package br.com.framework.frameworkpost.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "comments_posts", indexes = {
        @Index(columnList = "post_id", name = "comment_post_id_idx"),
        @Index(columnList = "email,name", name = "comment_email_name_idx")
        })
@SequenceGenerator(name = "comments_id_seq", allocationSize = 1)
public class CommentsPosts {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comments_id_seq")
    @Column(updatable = false)
    private Long id;

    private String name;
    private String email;
    private String comment;

    @Column(name = "date_created")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
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
    private Post post;

}
