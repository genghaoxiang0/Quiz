package com.ghx.config;

import com.ghx.interceptor.AdminInterceptor;
import com.ghx.interceptor.LoginInterceptor;
import com.ghx.interceptor.QuizInterceptor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Data
@NoArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;

    @Autowired
    private AdminInterceptor adminInterceptor;

    @Autowired
    private QuizInterceptor quizInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor).excludePathPatterns("/login", "/img/**", "/register", "WEB-INF/js/**", "/");
        registry.addInterceptor(quizInterceptor).excludePathPatterns("/login", "/img/**", "/register", "WEB-INF/js/**", "/quiz/question", "/quiz/question/**", "/quiz/submit", "/quiz/update-answer", "/quiz/check-finish", "/quiz/mark-question", "/quiz/unmark-question");
        registry.addInterceptor(adminInterceptor).addPathPatterns("/admin/**");
    }

}
