package prj.educatrix.main.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String fileName;
    @NotNull
    String contentType;
    Long size;

    @CreationTimestamp
    Date uploadedDate;

    public String getFileSize() {
        double bytes = size;
        double kilobytes = bytes / 1024f;
        if (kilobytes > 10240f) {
            double megabytes = bytes / 1024f / 1024f;
            return String.format("%.2f", megabytes) + " MB";
        }
        return (bytes > 10240f)
            ? String.format("%.2f", kilobytes) + " KB"
            : String.format("%.2f", bytes) + " B";
    }

    public String getURI() {
        return "/resources/" + id + "/" + fileName;
    }
}
