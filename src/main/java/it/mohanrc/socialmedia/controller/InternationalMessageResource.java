package it.mohanrc.socialmedia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class InternationalMessageResource {

    @Autowired
    private MessageSource messageSource;

    /*
    * http://localhost:8080/greet to Test the i18n
    * get request
    * if header have Accept-Language = it then it will fetch the message from messages_it.properties
    * if header have Accept-Language = fr then it will fetch the message from messages_fr.properties
    * if header don't have Accept-Language then it will fetch the default message from messages.properties
    * because we configured default locale as US in the InternationalConfig class
    * */
    @GetMapping("greet")
    public String greet(@RequestHeader(value = "Accept-Language", required = false) Locale locale) {
        //Second Param is used to pass custom argument to append with the message in the first argument
        return messageSource.getMessage("message.greet", null, locale);
    }

    /*
    * http://localhost:8080/greetagain to Test the i18n
    * if header have Accept-Language = it then it will fetch the message from messages_it.properties
    * if header have Accept-Language = fr then it will fetch the message from messages_fr.properties
    * if header don't have Accept-Language then it will fetch the default message from messages.properties
    * And in Controller for this method we don't need to get @RequestHeader annotation to get the locale,
    * instead spring will give us the incoming header locale value in the context holder
    * In InternationalConfig class we need to change the locale resolver from
    * SessionLocaleResolver to AcceptHeaderLocaleResolver, to make use of the incoming locale
    * without specifying the @RequestHeader annotation
    * */
    @GetMapping("greetagain")
    public String greetagain() {
        //Second Param is used to pass custom argument to append with the message in the first argument
        return messageSource.getMessage("message.greet", null, LocaleContextHolder.getLocale());
    }
}
