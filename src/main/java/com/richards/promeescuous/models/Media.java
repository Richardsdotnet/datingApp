package com.richards.promeescuous.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;
    private String description;

    @ElementCollection
    private List<Reaction> reactions;

    private String  url;
    @ManyToOne
    private User user;
}
