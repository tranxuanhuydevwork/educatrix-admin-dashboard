package prj.educatrix.main.dto;

import lombok.Data;

@Data
public class StatisticsRequest {
    private Integer courseId;
    private String period;
}