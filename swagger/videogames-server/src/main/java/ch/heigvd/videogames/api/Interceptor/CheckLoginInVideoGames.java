package ch.heigvd.videogames.api.Interceptor;

import ch.heigvd.videogames.api.exceptions.ApiException;
import ch.heigvd.videogames.utils.VideoGamesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CheckLoginInVideoGames implements HandlerInterceptor {

    VideoGamesUtils videoGamesUtils = new VideoGamesUtils();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ApiException {
        String header = request.getHeader("Authorization");
        System.out.println(header);

        if(header == null)
            throw new ApiException(3, "Please Log !");

        videoGamesUtils.parseJWT(header);


        return true;
    }
}
