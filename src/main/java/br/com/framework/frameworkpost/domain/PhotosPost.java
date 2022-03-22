package br.com.framework.frameworkpost.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "photo_posts", indexes = {
        @Index(columnList = "post_id", name = "photo_post_id_idx")
})
public class PhotosPost {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "photo_post_id_seq")
    @Column(updatable = false)
    private Long id;
    private String nameFile;
    private String description;
    private String contentType;

    private Long size;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns(value = {
            @JoinColumn(name = "post_id", referencedColumnName = "id")
    }, foreignKey = @ForeignKey(name = "photo_post_fk"))
    @JsonIgnore
    private Post post;
}
