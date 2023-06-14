package com.developersview.webservices.restfulwebservices.HelloWorld;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

/**
 * @author pranoy.chakraborty
 * @Date 14/06/2023
 */
@RestController
public class HelloWorldController {

    private MessageSource messageSource;

    public HelloWorldController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @GetMapping(path = "/hello-world")
    public String helloWorld(){
        return "Hello World! Lets learn SpringBoot";
    }

    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean("Hello World! Lets learn SpringBoot");
    }

    @GetMapping(path = "/hello-world-bean/{name}")
    public HelloWorldBean helloWorldBeanWithPathVariable(@PathVariable String name){
        return new HelloWorldBean("Hello " + name);
    }

    @GetMapping(path = "/hello-world-i18n")
    public String helloWorldInternationalized(){
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(
                "good.morning.message",
                null,
                "Default Message",
                locale
        );
    }
}
