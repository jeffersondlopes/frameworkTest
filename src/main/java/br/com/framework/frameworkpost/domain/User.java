package br.com.framework.frameworkpost.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "users", indexes = {
        @Index(columnList = "email", name = "email_user_idx")
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    @Column(updatable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String email;


}
