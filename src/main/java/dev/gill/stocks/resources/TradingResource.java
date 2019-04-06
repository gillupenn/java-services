package dev.gill.stocks.resources;

import com.codahale.metrics.annotation.Timed;
import dev.gill.stocks.client.ExternalStockAPIClient;
import dev.gill.stocks.core.model.Stock;
import dev.gill.stocks.util.Json;

import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.List;

@Path("/stocks")
public class TradingResource {

    private static final String AV_URI = "https://www.alphavantage.co/query?";

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Stock> findAllStocksSP500 () {
        /* Todo */
        return null;
    }

    @GET
    @Timed
    @Path("/{ticker}")
    @Produces(MediaType.APPLICATION_JSON)
    public Stock findByTicker (@NotNull @PathParam("ticker") String ticker) {

        ExternalStockAPIClient externalStockAPIClient = new ExternalStockAPIClient(ClientBuilder.newClient());
        HashMap<String, String> queryParams = new HashMap<>();
        queryParams.put("function", "TIME_SERIES_INTRADAY");
        queryParams.put("symbol", ticker);
        queryParams.put("interval", "5min");
        queryParams.put("apikey", "GRHPW2H6KRP7MAU5");

        String data = externalStockAPIClient.get(AV_URI, queryParams, String.class);
        Stock stock = Json.fromJsonString(data, Stock.class);
        return stock;
    }

}
