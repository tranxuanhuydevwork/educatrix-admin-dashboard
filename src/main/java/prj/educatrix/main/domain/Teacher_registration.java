package prj.educatrix.main.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Teacher_registration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "registration_id")
    int id;
    String first_name;
    String last_name;
    String reason;
    String cccd;
    String phone_number;
    String gender;
    String date_of_birth;
    Date created_time;
    Date processed_time;
    String bank_account;
    String status;

    @ManyToOne
    @JoinColumn(name = "account_id")
    Account account;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public String formatDate(Date date) {
        return sdf.format(date);
    }


    @JsonProperty("formattedCreateOn")
    public String getCreatedOnFormatted() {
        return formatDate(created_time);
    }
    @JsonProperty("formattedProcessOn")
    public String getProcessedOnFormatted() {
        return formatDate(processed_time);
    }
}
