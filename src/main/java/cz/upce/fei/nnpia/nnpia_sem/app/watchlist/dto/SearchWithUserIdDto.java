package cz.upce.fei.nnpia.nnpia_sem.app.watchlist.dto;

import lombok.Data;

@Data
public class SearchWithUserIdDto {
    private String query;
    private Long userId;
}
