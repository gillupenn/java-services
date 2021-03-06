package dev.gill.stocks.resources;

import com.codahale.metrics.annotation.Timed;
import dev.gill.stocks.client.ExternalStockAPIClient;
import dev.gill.stocks.core.model.Stock;
import io.swagger.util.Json;

import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Path("/stocks")
public class TradingResource {

    private static final String AV_URI = "https://www.alphavantage.co/query?";

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Stock> findAllStocksSP500 () {
        //ToDo
        Stock stock = new Stock();
        stock.name = "APPL";
        List<Stock> stocks = new ArrayList<Stock>(Collections.singletonList(stock));
        return stocks;
    }

    @GET
    @Timed
    @Path("/{ticker}")
    @Produces(MediaType.APPLICATION_JSON)
    public String findByTicker (@NotNull @PathParam("ticker") String ticker) {

        ExternalStockAPIClient externalStockAPIClient = new ExternalStockAPIClient(ClientBuilder.newClient());
        HashMap<String, String> queryParams = new HashMap<>();
        queryParams.put("function", "TIME_SERIES_INTRADAY");
        queryParams.put("symbol", ticker);
        queryParams.put("interval", "5min");
        queryParams.put("apikey", "GRHPW2H6KRP7MAU5");

        Object obj = externalStockAPIClient.get(AV_URI, queryParams, String.class);
        return Json.pretty(obj);
    }

}
