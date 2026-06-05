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
public class ProfileViewDTO {
    private String id;
    private String username;
    private String email;
    private String fullName;
    private String description;
    private String profileImage;
    private String role;
    private boolean active;
    private String accountId;
    private LocalDateTime createdDate;
    private String linkedin;
    private String twitter;
    private String github;
    private String website;
    private List<EnrolledCourseDTO> enrolledCourses;
    private List<AccountHistoryDTO> accountHistory;
}
