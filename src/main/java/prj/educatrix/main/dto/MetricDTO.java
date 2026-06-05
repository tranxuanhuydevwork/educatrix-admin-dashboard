package prj.educatrix.main.dto;


import lombok.Builder;
import lombok.Data;

@Data
    @Builder
    public class MetricDTO {
    private Integer courseId;
    private MetricItem totalStudents;
    private MetricItem totalRevenue;
    private MetricItem completionRate;
    private MetricItem avgRating;
    }


