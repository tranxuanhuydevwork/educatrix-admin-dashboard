package prj.educatrix.main.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import prj.educatrix.main.domain.Account;
import prj.educatrix.main.domain.Course;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDetailDTO {
    private String transactionId;
    private String methodPayment;
    private Date transactionDate;
    private String transactionStatus;
    private Double orderTotalAmount;
    private Account account;
    private String email;
    private String phoneNumber;
    private List<Course> courses;
    private String couponCode;
}