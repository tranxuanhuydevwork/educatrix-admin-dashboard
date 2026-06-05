package prj.educatrix.main.dto;

import lombok.Data;

@Data
public class ReviewSummaryDTO {
    private double averageRating;
    private int totalReviews;
    private int fiveStarCount;
    private int fourStarCount;
    private int threeStarCount;
    private int twoStarCount;
    private int oneStarCount;
}