package jtf.community.community.contorller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexContorller {
    @GetMapping("/")
    public String index(){
        return  "index";
    }
}
