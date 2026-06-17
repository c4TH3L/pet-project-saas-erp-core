package ru.cathel.saaserpcore.auth.filter;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.cathel.saaserpcore.auth.constant.JwtTokenType;
import ru.cathel.saaserpcore.auth.dto.EmployeeAccessTokenClaims;
import ru.cathel.saaserpcore.auth.dto.FounderAccessTokenClaims;
import ru.cathel.saaserpcore.auth.employee.dto.EmployeePrincipal;
import ru.cathel.saaserpcore.auth.exception.InvalidJwtTokenException;
import ru.cathel.saaserpcore.auth.service.FounderAccessTokenClaimsMapper;
import ru.cathel.saaserpcore.auth.service.JwtClaimsMapper;
import ru.cathel.saaserpcore.auth.service.JwtService;
import ru.cathel.saaserpcore.db.repository.PermissionRepository;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final JwtClaimsMapper<FounderAccessTokenClaims> founderClaimsMapper;
    private final JwtClaimsMapper<EmployeeAccessTokenClaims> employeeClaimsMapper;
    private final PermissionRepository permissionRepository;
    private final AuthenticationEntryPoint entryPoint;

    @Autowired
    public JwtAuthenticationFilter(JwtService jwtService,
                                   JwtClaimsMapper<FounderAccessTokenClaims> founderClaimsMapper,
                                   JwtClaimsMapper<EmployeeAccessTokenClaims> employeeClaimsMapper,
                                   PermissionRepository permissionRepository,
                                   AuthenticationEntryPoint entryPoint) {
        this.jwtService = jwtService;
        this.founderClaimsMapper = founderClaimsMapper;
        this.employeeClaimsMapper = employeeClaimsMapper;
        this.permissionRepository = permissionRepository;
        this.entryPoint = entryPoint;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        try {
            if (token == null || !token.startsWith("Bearer ")) {
                throw new AuthenticationCredentialsNotFoundException("Bearer token is not found");
            }
            token = token.substring(7);
            Claims claims = jwtService.parseClaimsFromToken(token);
            JwtTokenType tokenType = jwtService.getTokenTypeFromClaims(claims);

            Authentication authentication;
            if (tokenType == JwtTokenType.FOUNDER_ACCESS_TOKEN) {
                authentication = getFounderAuthentication(founderClaimsMapper.parseBodyFromClaims(claims));
            } else if (tokenType == JwtTokenType.EMPLOYEE_ACCESS_TOKEN) {
                authentication = getEmployeeAuthentication(employeeClaimsMapper.parseBodyFromClaims(claims));
            }
            else {
                throw new BadCredentialsException("Invalid token");
            }

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        catch (AuthenticationException e) {
            SecurityContextHolder.clearContext();
            entryPoint.commence(request, response, e);
        }
        filterChain.doFilter(request, response);
    }

    private Authentication getFounderAuthentication(FounderAccessTokenClaims body) {
        return new UsernamePasswordAuthenticationToken(
                body.id(),
                null,
                Collections.emptyList()
        );
    }

    private Authentication getEmployeeAuthentication(EmployeeAccessTokenClaims body) {
        return new UsernamePasswordAuthenticationToken(
                new EmployeePrincipal(
                        body.id(),
                        body.companyId(),
                        body.subCompanyId()
                ),
                null,
                permissionRepository.findAllByRoleId(body.roleId())
                        .stream()
                        .map(perm -> new SimpleGrantedAuthority(perm.getName()))
                        .collect(Collectors.toSet())
        );
    }
}
