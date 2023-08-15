package com.richards.promeescuous.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String description;
    @Enumerated(value = EnumType.STRING)
    @ElementCollection

    private List<Reaction> reactions;
    @Column(unique = false, columnDefinition = "MEDIUMTEXT", length = 1000)
    private String url;
    @ManyToOne
    private User user;
}
