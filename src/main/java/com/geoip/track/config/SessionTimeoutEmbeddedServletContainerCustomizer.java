package com.geoip.track.config;//package com.scrummater.tool.config;
//
//import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
//import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
//import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
//import org.springframework.boot.web.servlet.ErrorPage;
//import org.springframework.http.HttpStatus;
//
//import java.util.concurrent.TimeUnit;
//
///**
// * Created by Admin on 22.09.2016.
// */
////session management class
//public class SessionTimeoutEmbeddedServletContainerCustomizer implements EmbeddedServletContainerCustomizer {
//    @Override
//    public void customize(ConfigurableEmbeddedServletContainer configurableEmbeddedServletContainer) {
//        TomcatEmbeddedServletContainerFactory tomcat = (TomcatEmbeddedServletContainerFactory) configurableEmbeddedServletContainer;
//        tomcat.setSessionTimeout(1, TimeUnit.MINUTES);
//        tomcat.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/notfound.html"));
//    }
//}