package com.asset.obs.web.advice;

import io.opentelemetry.api.trace.Span;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;


/**
 * It is a 'ControllerAdvice' implementing interface 'ResponseBodyAdvice'. Before response body 'write', 'inject' the
 * span Id and trace id in the response header
 */
@ControllerAdvice
public class ResponseHeaderInjector implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(final MethodParameter returnType,
        final Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    /**
     * Get span Id and trace Id from current Context and add them to the response header.
     *
     * @param body                  the body to be written
     * @param returnType            the return type of the controller method
     * @param selectedContentType   the content type selected through content negotiation
     * @param selectedConverterType the converter type selected to write to the response
     * @param request               the current request
     * @param response              the current response
     * @return body
     */
    @Override
    public Object beforeBodyWrite(
        final Object body,
        final MethodParameter returnType,
        final MediaType selectedContentType,
        final Class<? extends HttpMessageConverter<?>> selectedConverterType,
        final ServerHttpRequest request,
        final ServerHttpResponse response) {

        /*
        // Check if response is committed
        if (response..isCommitted()) {
            // Response has already been committed, you cannot modify headers at this point.
            // Handle the situation accordingly.
            // ...
            System.out.println("-----------");
        }
        */

        // Get span Id and trace Id from current Context
        final String spanId = Span.current().getSpanContext().getSpanId();
        final String traceId = Span.current().getSpanContext().getTraceId();

        // Add trace id and span id to the response headers
        response.getHeaders().set("X-Trace-ID", traceId);
        response.getHeaders().set("X-Span-ID", spanId);

        return body;
    }
}