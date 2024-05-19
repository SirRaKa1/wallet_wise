package ru.outeast.wallet_wise.app.common;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jdk.jfr.Timestamp;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

@Component
@RequiredArgsConstructor
public class LoggingFilter extends OncePerRequestFilter {
    private final KafkaSender kafkaSender;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        ZonedDateTime before = ZonedDateTime.now();

        filterChain.doFilter(request, response);

        String path = request.getRequestURI();

        int status = response.getStatus();
        ZonedDateTime after = ZonedDateTime.now();
        kafkaSender.sendMessage(
                after + ": " + path + " response time: " + ChronoUnit.MILLIS.between(before,after) + "ms | STATUS [" + status + "]",
                "request_topic");
    }
}
