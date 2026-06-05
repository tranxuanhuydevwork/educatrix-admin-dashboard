package prj.educatrix.main.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public  class MetricItem {
    private String metricName;
    private String currentValue;
    private String formattedValue;
    private double growthRate;
    private boolean isPositive;
}