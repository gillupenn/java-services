package dev.gill.stocks.resources;


import dev.gill.stocks.core.model.Stock;
import org.junit.Assert;
import org.junit.Test;

public class TradingResourceTest {

    @Test
    public void findAllStocksSP500() {
    }

    @Test
    public void findByTicker() {
        TradingResource tradingResource = new TradingResource();
        Stock stock = tradingResource.findByTicker("AAPL");
        Assert.assertNotNull(stock);

    }
}