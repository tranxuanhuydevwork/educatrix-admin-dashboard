package prj.educatrix.main.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountHistoryDTO {
    private LocalDateTime date;
    private String event;
    private String details;
}