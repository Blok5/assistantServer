package ru.sberbank.assistantServer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/hello")
public class AssistantController {

    @GetMapping
    public String getAssistant() {
        return "Hello World";
    }

}
