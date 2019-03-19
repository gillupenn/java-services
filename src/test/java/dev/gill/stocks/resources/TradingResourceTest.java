package dev.gill.stocks.resources;


import org.junit.Assert;
import org.junit.Test;

public class TradingResourceTest {

    @Test
    public void findAllStocksSP500() {
    }

    @Test
    public void findByTicker() {
        TradingResource tradingResource = new TradingResource();
        String stock = tradingResource.findByTicker("AAPL");
        Assert.assertNotNull(stock);

    }
}