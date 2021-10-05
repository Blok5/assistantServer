package ru.sberbank.assistant.job;

import com.sb.api.kudago.client.KudagoClient;
import com.sb.api.kudago.model.ref.Location;
import com.sb.api.kudago.model.response.SearchEventResponse;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.error.Mark;
import ru.sberbank.assistant.component.KudagoCache;

import java.util.Date;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@Component
public class KudagoJob implements Job {

    @Autowired
    private  KudagoClient kudagoClient;
    @Autowired
    private  KudagoCache kudagoCache;

    Logger logger = LoggerFactory.getLogger(KudagoJob.class);

    public void execute(JobExecutionContext context) throws JobExecutionException {
        logger.warn("Kudago cache start getting...");
        SearchEventResponse response=kudagoClient.getEventList(new Date(),null, Location.MSK.getApiVal(),"", "",100);
        if(response!=null && response.getResults()!=null){
                while(response.getNext()!=null && !response.getNext().equals("")){

                    // фильтруем пустые места
                    kudagoCache.setEventNewList(new CopyOnWriteArrayList<>(response.getResults().stream().filter(e->e.getPlace()!=null)
                            .collect(Collectors.toList())));

                    response=kudagoClient.getNextPage(response.getNext());


                }
            if(response!=null && response.getResults()!=null){
                kudagoCache.setEventNewList(new CopyOnWriteArrayList<>(response.getResults()));
            }

        }
        kudagoCache.setEventCurrentList(kudagoCache.getEventNewList());
        logger.warn("Kudago cache end getting...");
    }
}
