package prj.educatrix.main.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "`orders`")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;
    @ManyToOne
    @JoinColumn(name = "account_account_id")
    private Account account;
    private String email;
    private String phoneNumber;
    private Date createTime;
    private Double totalAmount;
    private String couponCode;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToMany
    @JoinTable(
            name = "order_item",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courses;

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", createTime=" + createTime +
                ", totalAmount=" + totalAmount +
                ", couponCode='" + couponCode + '\'' +
                '}';
    }
}
