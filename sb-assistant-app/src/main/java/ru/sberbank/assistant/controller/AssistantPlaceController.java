package ru.sberbank.assistant.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import doublegis.client.DoubleGisClient;
import doublegis.model.place.Place;
import doublegis.model.response.SearchPlaceResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.sberbank.assistant.ref.PlaceType;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@Api(value = "Operations with places")
@RequestMapping(path = "api/place/v1")
public class AssistantPlaceController {

    private final DoubleGisClient doubleGisClient;

    @Autowired
    public AssistantPlaceController(DoubleGisClient doubleGisClient) {
        this.doubleGisClient = doubleGisClient;
    }


    @ApiOperation(value = "Get allowed values for enums")
    @GetMapping(value = "/allowedValues")
    public String getAllowedValues() {
        return "Location: "+ PlaceType.validValues();
    }


    @ApiOperation(value = "Search places in radius by type of place")
    @GetMapping(value = "/search-places", produces = "application/json;charset=UTF-8")
    public List<Place> searchEvents(@ApiParam(allowableValues="cinema,cafe-restaurant,theater,pub") @RequestParam(name="place_type") PlaceType placeType,
                                    @RequestParam(name="lon",required=false) Double lon,
                                    @RequestParam(name="lat",required=false) Double lat,
                                    @RequestParam(name="radius",required=false) Integer radius){
/* ЗАГЛУШКА
        ObjectMapper mapper=new ObjectMapper();
        try {
            SearchPlaceResponse placeResponse=mapper.readValue(ResourceUtils.getFile("classpath:gisApiExample.json"),SearchPlaceResponse.class);
            System.out.println("");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;*/
         return doubleGisClient.searchPlace(placeType.getApiVal(),lon,lat,radius);
    }

}
