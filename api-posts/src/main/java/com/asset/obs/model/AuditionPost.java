package com.asset.obs.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class AuditionPost {
    private int userId;
    private int id;
    private String title;
    private String body;
    private List<AuditionPostComment> auditionPostComments;

    public AuditionPost(final int userId, final int id, final String title, final String body) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }
}
