package dev.gill.stocks;

import dev.gill.stocks.health.ExternalAPIHealthCheck;
import dev.gill.stocks.resources.TradingResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class StockTradingApplication extends Application<StockTradingApplicationConfiguration> {

    public static void main(String[] args) throws Exception {
        new StockTradingApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<StockTradingApplicationConfiguration> bootstrap) {

    }

    @Override
    public void run(StockTradingApplicationConfiguration stockTradingApplicationConfiguration, Environment environment) throws Exception {

        final TradingResource tradingResource = new TradingResource();
        final ExternalAPIHealthCheck externalAPIHealthCheck = new ExternalAPIHealthCheck();

        environment.jersey().register(tradingResource);
        environment.healthChecks().register("uri", externalAPIHealthCheck);

    }
}
