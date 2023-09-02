package com.richards.promeescuous.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.EAGER;

@Entity
@Setter
@Getter
public class Media {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;
        private String description;
        @OneToMany(cascade = ALL, fetch = EAGER)
        private List<MediaReaction> reactions;
        @Column(unique = true)
        private String url;
        @ManyToOne
        private User user;

        private boolean isLike;


        public Media(){
            reactions = new ArrayList<>();
        }
    }

