package prj.educatrix.main.config;


import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {
    // @Bean
    // public ViewResolver viewResolver() {
    // final InternalResourceViewResolver bean = new InternalResourceViewResolver();
    // bean.setViewClass(JstlView.class);
    // bean.setPrefix("/WEB-INF/view/");
    // bean.setSuffix(".html");
    // return bean;
    // }

    // @Override
    // public void configureViewResolvers(ViewResolverRegistry registry) {
    // registry.viewResolver(viewResolver());
    // }
    // --> Phù hợp với JSP
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**").addResourceLocations("file:src/main/resources/static/css/");
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");
        registry.addResourceHandler("/images/**").addResourceLocations("classpath:/static/images/");

    }

}
