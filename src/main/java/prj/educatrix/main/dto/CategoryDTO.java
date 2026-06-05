package prj.educatrix.main.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.NumberFormat;
import java.util.Locale;

@Data
@NoArgsConstructor
public class CategoryDTO {
    private Integer id;
    private String categoryName;
    private String description;
    private Integer courseCount;
    private Double totalRevenue;
    private Integer totalEnrolled;

    public CategoryDTO(Integer totalEnrolled, Double totalRevenue, Integer courseCount, String description, String categoryName, Integer id) {
        this.totalEnrolled = totalEnrolled;
        this.totalRevenue = totalRevenue;
        this.courseCount = courseCount;
        this.description = description;
        this.categoryName = categoryName;
        this.id = id;
    }

    public String getFormattedRevenue() {
        if (totalRevenue == null) {
            return "0 ₫";
        }
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        return currencyFormat.format(totalRevenue);
    }
}