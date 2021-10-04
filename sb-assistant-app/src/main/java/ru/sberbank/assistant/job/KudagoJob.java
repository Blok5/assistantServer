package ru.sberbank.assistant.job;

import com.sb.api.kudago.client.KudagoClient;
import com.sb.api.kudago.model.Event;
import com.sb.api.kudago.model.ref.Category;
import com.sb.api.kudago.model.ref.Location;
import com.sb.api.kudago.model.response.SearchResponse;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.sberbank.assistant.component.KudagoCache;

import java.util.Date;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class KudagoJob implements Job {

    @Autowired
    private  KudagoClient kudagoClient;
    @Autowired
    private  KudagoCache kudagoCache;


    public void execute(JobExecutionContext context) throws JobExecutionException {

        SearchResponse response=kudagoClient.getEventList(new Date(),null, Location.MSK.getApiVal(),"", "",100);
        if(response!=null && response.getResults()!=null){
                while(response.getNext()!=null && !response.getNext().equals("")){

                    kudagoCache.setEventNewList(new CopyOnWriteArrayList<>(response.getResults()));

                    response=kudagoClient.getNextPage(response.getNext());


                }
            if(response!=null && response.getResults()!=null){
                kudagoCache.setEventNewList(new CopyOnWriteArrayList<>(response.getResults()));
            }

        }
        kudagoCache.setEventCurrentList(kudagoCache.getEventNewList());

    }
}
