package prj.educatrix.main.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class NotificationDTO {
    private Long id;
    private String type;
    private String message;
    private String iconClass;
    private String referenceId;
    private String status;
    private Date createdAt;
    private Date readAt;

    // Added for admin view
    private String userId;
    private String username;
}