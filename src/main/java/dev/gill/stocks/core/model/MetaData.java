package dev.gill.stocks.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;



public class MetaData {

    @JsonProperty("1. Information")
    private String information;

    @JsonProperty("2. Symbol")
    private String symbol;

    @JsonProperty("3. Last Refreshed")
    private String lastRefreshed;

    @JsonProperty("4. Interval")
    private String interval;

    @JsonProperty("5. Output Size")
    private String size;

    @JsonProperty("6. Time Zone")
    private String timeZone;

}
