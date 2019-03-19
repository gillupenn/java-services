package dev.gill.stocks.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

public class ExternalStockAPIClient {

    private static final Logger log = LoggerFactory.getLogger(ExternalStockAPIClient.class);

    private Client client;

    // Constructor
    @Inject
    public ExternalStockAPIClient(Client client) {
        this.client = client;
    }

    public <T, P> T get(final String url, final HashMap<String, P> queryParams, final Class<T> response) {
        return getInternal(url, queryParams, (request) -> request.get(response));
    }

    public <T, P> T get(final String url, final HashMap<String, P> queryParams, final GenericType<T> response) {
        return getInternal(url, queryParams, (request) -> request.get(response));
    }

    private <T, P> T getInternal(final String path,
                                          final HashMap<String, P> queryParams,
                                          final Function<Invocation.Builder, T> function) {

        long start = System.currentTimeMillis();
        final WebTarget webTarget = prepareWebTarget(path, queryParams);

        int retry = 0;

        int retryLimit = 2;
        while (retry < retryLimit) {
            final Invocation.Builder preparedRequest = webTarget.request().accept(APPLICATION_JSON);
            try {

                T response = function.apply(preparedRequest);
                log.debug(String.format("Get call: %s in (ms): %d", path, System.currentTimeMillis() - start ));
                return response;
            } catch (WebApplicationException wae) {
                log.error(String.format("WebApplicationException occured while calling external Stock API: %s",
                        webTarget.getUri().toString()), wae);
            }
        }
        throw new WebApplicationException(401);
    }

    private <P> WebTarget prepareWebTarget(final String url,
                                           HashMap<String, P> params) {

        WebTarget webTarget = client.target(url);
        if (params != null) {
            for (Map.Entry<String, P> entry : params.entrySet()) {
                if (entry.getKey() != null && entry.getValue() != null) {
                    webTarget = webTarget.queryParam(entry.getKey(), entry.getValue());
                }
            }
        }
        return webTarget;
    }

}
