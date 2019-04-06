package dev.gill.stocks.core.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Stock {

    private String symbol;
    private DateTime lastRefreshedTime;
    private String interval;
    private StockState state;
    private Double stockPrice;
    private Long volume;
}
