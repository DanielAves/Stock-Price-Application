package com.api;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Date;

/**
 * This ViewController class sets up the home page of the web page
 * Page load time is returned to the interface
 * @author Dan Aves
 */

@Controller
public class ViewController {

    @RequestMapping("/")
    public String index(Model model){

        model.addAttribute("datetime", new Date());
        return "index";
    }
}
