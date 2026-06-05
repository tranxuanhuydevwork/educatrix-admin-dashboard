package prj.educatrix.main.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ReviewDTO {
    private Long id;
    private String userName;
    private boolean verifiedPurchase;
    private Date reviewDate;
    private double rating;
    private String comment;
}