package com.example.orchestation.Filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RequestResponseLoggingFilter extends OncePerRequestFilter {

    public static final Logger log = LoggerFactory.getLogger(RequestResponseLoggingFilter.class);
    public static final int MAX_PAYLOAD_LENGTH = 1000;
    public static final int REQUEST_CACHE_LIMIT = 1024 * 1024; // 1 MB

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(request, REQUEST_CACHE_LIMIT);
        ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper(response);

        long startTime = System.currentTimeMillis();

        try {
            // Process the request through the downstream filter chain and controllers
            filterChain.doFilter(wrappedRequest, wrappedResponse);
        } finally {
            long duration = System.currentTimeMillis() - startTime;
            logRequest(wrappedRequest);
            logResponse(wrappedResponse, duration);

            // CRITICAL: Copies the cached response content back to the real output stream
            wrappedResponse.copyBodyToResponse();
        }

    }

    private void logRequest(ContentCachingRequestWrapper request){
        String queryString = request.getQueryString() == null ? "" : "?" + request.getQueryString();
        String payload = getPayload(request.getContentAsByteArray(), request.getCharacterEncoding());
        log.info("REQ [{} {}{}] Payload: {}",
                request.getMethod(),
                request.getRequestURI(),
                queryString,
                payload);
    }

    private void logResponse(ContentCachingResponseWrapper response, long duration){
        String payload = getPayload(response.getContentAsByteArray(), response.getCharacterEncoding());
        log.info("RESP [{}] Duration: {}ms | Payload: {}",
                response.getStatus(),
                duration,
                payload);
    }

    private String getPayload(byte [] buf, String characterEncoding){
        if(buf == null || buf.length == 0){
            return "[empty]";
        }
        int length = Math.min(buf.length, MAX_PAYLOAD_LENGTH);
        try {
            String charset = (characterEncoding != null) ? characterEncoding : StandardCharsets.UTF_8.name();
            String content = new String(buf, 0, length, charset);
            return  (buf.length > MAX_PAYLOAD_LENGTH) ? content + "... [TRUNCATED]" : content;
        } catch (Exception ex) {
            return "[binary data or unsupported encoding]";
        }
    }

}
