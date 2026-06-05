package prj.educatrix.main.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnrolledCourseDTO {
    private Integer id;
    private String title;
    private int progressPercentage;
}

