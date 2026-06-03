package prj.educatrix.main.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@Table(name = "transaction")
public class Transaction {
    @Id
    private String transactionId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private Order order;
    private String methodPayment;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date transactionDate;

    private String transactionStatus;

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", methodPayment='" + methodPayment + '\'' +
                ", transactionDate=" + transactionDate +
                ", transactionStatus='" + transactionStatus + '\'' +
                ", orderTotalAmount=" + (order != null ? order.getTotalAmount() : "N/A") +
                ", accountUsername=" + (order != null && order.getAccount() != null ? order.getAccount().getUsername() : "Unknown") +
                '}';
    }

    public Double getOrderTotalAmount() {
        return order != null ? order.getTotalAmount() : 0;
    }

}
