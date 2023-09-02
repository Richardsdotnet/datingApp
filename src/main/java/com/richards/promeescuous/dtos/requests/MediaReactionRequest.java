package com.richards.promeescuous.dtos.requests;


import com.richards.promeescuous.models.Reaction;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class MediaReactionRequest {
    private Reaction reaction;
    private Long mediaId;
    private Long userId;
}