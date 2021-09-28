package ru.sberbank.assistant.controller;

import com.sb.api.kudago.client.KudagoClient;
import com.sb.api.kudago.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1")
public class AssistantController {

    private final KudagoClient kudagoClient;

    @Autowired
    public AssistantController(KudagoClient kudagoClient) {
        this.kudagoClient = kudagoClient;
    }

    @GetMapping(value = "/hello")
    public String getAssistant() {
        return "Hello World";
    }

    @GetMapping(value = "/lucky", produces = "application/json;charset=UTF-8")
    public Event getLuckyEvent() {
        return kudagoClient.lucky();
    }

    @GetMapping(value = "/search-events", produces = "application/json;charset=UTF-8")
    public List<Event> searchEvents( @RequestParam(name="dateTo",required=false) Date dateTo,
                                     @RequestParam(name="dateFrom",required=false) Date dateFrom,
                                     @RequestParam(name="isFree",required=false) String isFree,
                                     @RequestParam(name="categories",required=false) String categories){
                                     return kudagoClient.searchEvents(dateTo,dateFrom,"msk",isFree,categories);
    }

}
