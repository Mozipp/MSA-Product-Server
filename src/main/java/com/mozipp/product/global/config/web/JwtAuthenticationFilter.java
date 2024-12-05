package com.mozipp.product.global.config.web;

import com.mozipp.product.domain.product.service.UserFindService;
import com.mozipp.product.global.util.CookieUtil;
import com.mozipp.product.global.util.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final CookieUtil cookieUtil;
    private final UserFindService userFindService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String accessToken = cookieUtil.getCookieValue(request, "access_token");

        if (accessToken != null) {
            try {
                // JWT에서 Claims 추출
                Claims claims = jwtUtil.getClaimsFromToken(accessToken);
                String username = claims.getSubject();

                // UserFindService를 통해 userId 조회
                Long userId = userFindService.getUserIdByUsername(username);

                // 인증 정보 설정 (principal을 userId로 설정)
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userId, null, null);
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // SecurityContext에 인증 정보 설정
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            } catch (Exception e) {
                // 토큰이 유효하지 않은 경우 처리 (로그 등)
                logger.error("Failed to set user authentication: {}");
            }
        }

        filterChain.doFilter(request, response);
    }
}