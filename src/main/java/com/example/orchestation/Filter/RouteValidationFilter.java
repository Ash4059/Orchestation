package com.example.orchestation.Filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mapstruct.Qualifier;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.io.IOException;
import java.time.LocalDateTime;

@Order(Ordered.HIGHEST_PRECEDENCE + 1)
public class RouteValidationFilter extends OncePerRequestFilter {

    private final RequestMappingHandlerMapping handlerMapping;

    public RouteValidationFilter(RequestMappingHandlerMapping handlerMapping){
        this.handlerMapping = handlerMapping;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        HandlerExecutionChain handlerChain = null;
        try {
            // Checks if an active @RequestMapping / @RestController matches the request
            handlerChain = this.handlerMapping.getHandler(request);

            if(handlerChain == null){
                // No matching controller mapping found: return immediately
                sendNotFoundResponse(request, response);
                return;
            }

            // Mapping exists, proceed through the rest of the application
            filterChain.doFilter(request, response);
        } catch (Exception e) {

        }

    }

    private void sendNotFoundResponse(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        String jsonResponse = String.format("""
                {
                    "timestamp": "%s",
                    "statusCode": 404,
                    "message": "Not Found",
                    "error": "No endpoint mapped for request '%s'.",
                    "path": "%s"
                """,
                LocalDateTime.now(), request.getRequestURI(), request.getRequestURI());
        response.getWriter().write(jsonResponse);
    }
}
