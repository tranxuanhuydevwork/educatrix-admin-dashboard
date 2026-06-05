package prj.educatrix.main.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import prj.educatrix.main.domain.Account;
import prj.educatrix.main.domain.Course;
import prj.educatrix.main.domain.Profile;
import prj.educatrix.main.domain.Reviews;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsDTO {
    private String id;
    private String username;
    private String email;
    private String status;
    private Profile profile;
    private Object role;
    private Set<Course> courses;
    private List<Reviews> reviews;
    private Date createdDate;
    private Date lastLogin;

    public static UserDetailsDTO fromAccount(Account account) {
        UserDetailsDTO dto = new UserDetailsDTO();
        dto.setId(account.getId());
        dto.setUsername(account.getUsername());
        dto.setEmail(account.getEmail());
        dto.setStatus(account.getStatus());
        dto.setProfile(account.getProfile());
        dto.setRole(account.getRole());
        dto.setCourses(account.getCourses());
        dto.setReviews(account.getReviews());
        // Note: createdDate and lastLogin would need to be added to your Account entity
        return dto;
    }
}