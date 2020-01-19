package ch.heigvd.videogames.api.Interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class MainInterceptorInVideoGames implements WebMvcConfigurer {

    @Autowired
    CheckLoginInVideoGames checkLoginInVideoGames;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(checkLoginInVideoGames).addPathPatterns("/videogames");
    }
}
