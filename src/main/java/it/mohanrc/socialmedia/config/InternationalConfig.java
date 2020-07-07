package it.mohanrc.socialmedia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Locale;

@Configuration
public class InternationalConfig {

    /*
     * Reason for changing from SessionLocaleResolver to AcceptHeaderLocaleResolver, is
     * to make use of the incoming header locale value without specifying the @RequestHeader
     * annotation in the Controller InternationalMessageResource class
     * */
    @Bean
    public LocaleResolver localeResolver() {
        /*SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
        sessionLocaleResolver.setDefaultLocale(Locale.US);
        return sessionLocaleResolver;*/
        AcceptHeaderLocaleResolver acceptHeaderLocaleResolver = new AcceptHeaderLocaleResolver();
        acceptHeaderLocaleResolver.setDefaultLocale(Locale.US);
        return acceptHeaderLocaleResolver;
    }

    /*
    * 1) messageSource name should be the same name while injecting in
    *   InternationalMessageResource class
    * 2) We can remove this config and can able to set the basename in
    *  the application.properties in case of property file name changes
    *  by default spring will have the base name as messages.properties
    *  If the property name is ok, we don't even need to set the base
    *  name in application.properties as well.
    * */
    /*@Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
        resourceBundleMessageSource.setBasename("message");
        return resourceBundleMessageSource;
    }*/
}
