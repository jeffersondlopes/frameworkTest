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
@SequenceGenerator(name = "user_id_seq", allocationSize = 1)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    @Column(updatable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String email;


}
