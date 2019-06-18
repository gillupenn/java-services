package dev.gill.stocks.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.node.ObjectNode;

public enum  TimeSeries {

    @JsonProperty("Time Series (1min)")
    TS_1_MIN;
/*    TS_5_MIN("Time Series (5min)"),
    TS_15_MIN("Time Series (15min)"),
    TS_30_MIN("Time Series (30min)"),
    TS_60_MIN("Time Series (60min)");*/

    // declaring private variable for getting values
    private String timeSeries;

    TimeSeries() {

    }

    // getter method
    public String getTimeSeries()
    {
        return this.timeSeries;
    }

    // enum constructor - cannot be public or protected
    private TimeSeries(String timeSeries)
    {
        this.timeSeries = timeSeries;
    }
}
