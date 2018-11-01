package com.hendisantika.personapplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by IntelliJ IDEA.
 * Project : person-application
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 01/11/18
 * Time: 07.00
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class SwaggerUIController {

    @RequestMapping(value = "/")
    public String index() {
        return "redirect:swagger-ui.html";
    }
}