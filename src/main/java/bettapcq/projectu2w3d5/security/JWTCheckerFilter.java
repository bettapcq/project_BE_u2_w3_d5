package bettapcq.projectu2w3d5.security;

import bettapcq.projectu2w3d5.entities.User;
import bettapcq.projectu2w3d5.exceptions.UnauthorizedException;
import bettapcq.projectu2w3d5.services.UsersService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTCheckerFilter extends OncePerRequestFilter {


    private final JWTTools jwtTools;
    private final UsersService usersService;

    public JWTCheckerFilter(JWTTools jwtTools, UsersService usersService) {
        this.jwtTools = jwtTools;
        this.usersService = usersService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

//check se token valido nell'header:
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer "))
            throw new UnauthorizedException("Token in Header Authorization has invalid format");


        //prendo token dall'header
        String accessToken = authorizationHeader.replace("Bearer ", "");

        //check scadenza e firma
        jwtTools.verifyToken(accessToken);


        //recupero id da token:
        Long userId = jwtTools.extractIdFromToken(accessToken);

        User authenticatedUser = this.usersService.findById(userId);

        Authentication authentication = new UsernamePasswordAuthenticationToken(authenticatedUser, null, authenticatedUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //check su prossimo elemento
        filterChain.doFilter(request, response);
    }


    //escludo endpoint auth:

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return new AntPathMatcher().match("/auth/**", request.getServletPath());
    }


}
