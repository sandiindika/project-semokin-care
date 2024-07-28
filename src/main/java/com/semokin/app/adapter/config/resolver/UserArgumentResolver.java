package com.semokin.app.adapter.config.resolver;

import com.semokin.app.adapter.config.security.JwtUtil;
import com.semokin.app.application.contract.UserService;
import com.semokin.app.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserArgumentResolver implements HandlerMethodArgumentResolver {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return User.class.equals(parameter.getParameterType());

    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
//        HttpServletRequest servletRequest = (HttpServletRequest) webRequest.getNativeRequest();

        String headerAuth = webRequest.getHeader(HttpHeaders.AUTHORIZATION);
        if (headerAuth == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid API token");
        }
        String clientToken = null;
        if (headerAuth.startsWith("Bearer ")) {
            clientToken = headerAuth.substring(7);
        }

        if (clientToken != null && jwtUtil.verifyJwtToken(clientToken)) {
            Map<String, String> userInfo = jwtUtil.getUserInfoByToken(clientToken);
            UserDetails user = userService.loadUserById(userInfo.get("userId"));

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource());

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            var userLast = userRepository.findById(userInfo.get("userId")).orElseThrow(()-> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid API token"));
            return userLast;
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid API token");
    }
}
