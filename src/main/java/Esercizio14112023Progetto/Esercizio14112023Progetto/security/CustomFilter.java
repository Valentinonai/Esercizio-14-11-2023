package Esercizio14112023Progetto.Esercizio14112023Progetto.security;

import Esercizio14112023Progetto.Esercizio14112023Progetto.entities.User;
import Esercizio14112023Progetto.Esercizio14112023Progetto.exceptions.Unauthorized;
import Esercizio14112023Progetto.Esercizio14112023Progetto.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class CustomFilter extends OncePerRequestFilter {
    @Autowired
    private JWTTools jwtTools;
    @Autowired
    private UserService userService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader=request.getHeader("Authorization");
        if(authorizationHeader==null || !authorizationHeader.startsWith("Bearer"))
            throw new Unauthorized("Token non valido");
        else{
            String token=authorizationHeader.substring(7);
            jwtTools.verificaToken(token);
            String id=jwtTools.getId(token);
            User u=userService.getSingleUser(Integer.parseInt(id));
            Authentication a=new UsernamePasswordAuthenticationToken(u,null,u.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(a);
            filterChain.doFilter(request,response);
        }

    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return new AntPathMatcher().match("/auth/**",request.getServletPath());
    }


}
