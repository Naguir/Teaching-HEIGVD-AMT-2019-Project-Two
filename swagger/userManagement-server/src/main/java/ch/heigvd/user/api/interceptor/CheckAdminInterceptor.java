package ch.heigvd.user.api.interceptor;


import ch.heigvd.user.api.exceptions.ApiException;
import ch.heigvd.user.utils.TokenJwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
public class CheckAdminInterceptor implements HandlerInterceptor {


    @Autowired
    TokenJwt tokenJwt;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ApiException {
        String token = request.getHeader("Authorization");


        if((request.getMethod().equals("DELETE") || request.getMethod().equals("POST")) && !tokenJwt.IsAdmin(token))
            throw new ApiException(0, "You are not an admin !");
        return true;
    }

}
