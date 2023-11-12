package com.asset.obs.common.interceptor;

import com.asset.obs.common.logging.AuditionLogger;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

/**
 * Interceptor of Logging request/response.
 * It is used for rest template calls and also can be commonly used for other http client
 * Debug Level Log, so to test this, please change the logging level to 'DEBUG'
 */
@Component
public class ReqRespLogInterceptor implements ClientHttpRequestInterceptor {
    private static final Logger LOG = LoggerFactory.getLogger(ReqRespLogInterceptor.class);

    private final AuditionLogger logger;

    public ReqRespLogInterceptor(final AuditionLogger logger) {
        this.logger = logger;
    }

    /**
     * Call the methods to write logs of request and response.
     *
     * @param request the request, containing method, URI, and headers
     * @param body the body of the request
     * @param execution the request execution
     * @return ClientHttpResponse
     * @throws IOException if an I/O error occurs
     */
    @Override
    public ClientHttpResponse intercept(final HttpRequest request, final byte[] body, final ClientHttpRequestExecution execution) throws IOException {

        // logging request
        requestLogging(request, body);

        // execute the request
        final ClientHttpResponse response = execution.execute(request, body);

        // logging response
        responseLogging(response);

        return response;
    }


    /**
     * Logging request uses 'DEBUG' level configuration.
     *
     * @param request Interceptor caught HTTP Request
     * @param body Interceptor caught HTTP Request body
     * @throws IOException if an I/O error occurs
     */
    private void requestLogging(final HttpRequest request, final byte[] body) throws IOException {
        logger.debug(LOG, "===============request start==============");
        logger.debug(LOG, "URI         :  " + request.getURI().toString());
        logger.debug(LOG, "Method      :  " + request.getMethod());
        logger.debug(LOG, "Headers     :  " + request.getHeaders());
        logger.debug(LOG, "Request body:  " + new String(body, StandardCharsets.UTF_8));
        logger.debug(LOG, "===============request end==============");
    }


    /**
     * Logging response uses 'DEBUG' level configuration.
     *
     * @param response Interceptor caught HTTP Response
     * @throws IOException if an I/O error occurs
     */
    private void responseLogging(final ClientHttpResponse response) throws IOException {
        logger.debug(LOG, "===============response start==============");
        logger.debug(LOG, "Status code  :  " + response.getStatusCode());
        logger.debug(LOG, "Status text  :  " + response.getStatusText());
        logger.debug(LOG, "Headers      :  " + response.getHeaders());
        logger.debug(LOG, "Response body:  " + StreamUtils.copyToString(response.getBody(), Charset.defaultCharset()));
        logger.debug(LOG, "===============response end==============");
    }
}