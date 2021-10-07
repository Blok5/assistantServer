package ru.sberbank.assistant.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sberbank.assistant.model.pulse.*;

import javax.annotation.PostConstruct;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@Api(value = "Stubs for thirdparty systems")
@RequestMapping(path = "api/stub/v1")
public class StubThirdpartyController {


    private String url;
    private final Environment environment;

    public StubThirdpartyController(Environment environment) {
        this.environment = environment;
    }

    @PostConstruct
    private void postConstruct() {

        try {
            url = "http://" + InetAddress.getLocalHost().getHostName() + ":" +
                    environment.getProperty("server.port") +
                    environment.getProperty("server.servlet.context-path") + "/images";
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @ApiOperation(value = "Get tasks from pulse", produces = "application/json;charset=UTF-8")
    @GetMapping(value = "/pulse/my-tasks")
    public List<Task> getPulseTasks() {
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task.Builder().name("Сделать отчет по продажам")
                .assignee("Петровская Нина Александровна")
                .description("Отчет по продажам за 3 квартал")
                .deadline(LocalDateTime.of(2021, 10, 10, 14, 30))
                .tags(new String[]{"работа", "3 квартал"}).build());

        taskList.add(new Task.Builder().name("Забрать Васю из садика")
                .assignee("Петровская Нина Александровна")
                .description("-")
                .deadline(LocalDateTime.of(2021, 10, 9, 18, 30))
                .tags(new String[]{"личное"}).build());
        return taskList;
    }

    @ApiOperation(value = "Get calendar from pulse", produces = "application/json;charset=UTF-8")
    @GetMapping(value = "/pulse/my-calendar")
    public CalendarPulse getPulseCalendar() {
        List<MeetingPulse> meetings = new ArrayList<>();
        meetings.add(new MeetingPulse.Builder()
                .place("7.A.118")
                .time(LocalDateTime.of(2021, 10, 9, 14, 30))
                .name("Обсуждения отчета по продажам")
                .description("Коллеги, нужно обсудить отчет за 3 квартал")
                .build());
        meetings.add(new MeetingPulse.Builder()
                .place("7.A.120")
                .time(LocalDateTime.of(2021, 10, 9, 16, 30))
                .name("Встреча по ОС")
                .description("Обсуждение продуктивности с руководителем")
                .build());
        return new CalendarPulse.Builder()
                .date(LocalDate.of(2021, 10, 9))
                .meetings(meetings
                ).build();

    }

    @ApiOperation(value = "Get profile from pulse", produces = "application/json;charset=UTF-8")
    @GetMapping(value = "/pulse/my-profile")
    public ProfilePulse getPulseProfile() {

        return new ProfilePulse.Builder()
                .name("Петровская Нина Александровна")
                .address("Кутузовский проспект 32к1")
                .imageUrl(url + "/nina.png")
                .dateOfBirth(LocalDate.of(1991, 2, 1))
                .interests(new String[]{"семья", "волейбол"})
                .skills(new String[]{"js", "java"})
                .social(new SocialPulse[]{new SocialPulse.Builder()
                        .link("facebook.com/profile/123123")
                        .name("facebook").build(),
                        new SocialPulse.Builder()
                                .link("vk.com/profile/123123")
                                .name("vk").build()}).build();

    }

    @ApiOperation(value = "Get growth recommendations from pulse", produces = "application/json;charset=UTF-8")
    @GetMapping(value = "/pulse/my-growth")
    public List<GrowthPulse> getPulseGrowth() {
        List<GrowthPulse> growthPulses = new ArrayList<>();

        growthPulses.add(new GrowthPulse.Builder()
                .name("Онлайн курс по JS")
                .type("IT")
                .description("Обучающий курс по javascript и html5")
                .tags(new String[]{"js", "html"})
                .duration(520)
                .rating(4.7d)
                .numOfViews(27)
                .imageUrl(url + "/js.jpg")
                .build());

        growthPulses.add(new GrowthPulse.Builder()
                .name("Онлайн курс по Java")
                .type("IT")
                .description("Обучающий курс java для новичков")
                .tags(new String[]{"java", "программирование"})
                .duration(340)
                .rating(4.9d)
                .numOfViews(833)
                .imageUrl(url + "/java.jpg")
                .build());

        return growthPulses;


    }

    @ApiOperation(value = "Get profile for random coffee", produces = "application/json;charset=UTF-8")
    @GetMapping(value = "/random-coffee")
    public List<ProfilePulse> getRandomCoffee() {
        List<ProfilePulse> profilePulses = new ArrayList<>();
        profilePulses.add(new ProfilePulse.Builder()
                .name("Кривенко Кристина Юрьевна")
                .address("Кутузовский проспект 32к1")
                .imageUrl(url + "/kris.jpg")
                .dateOfBirth(LocalDate.of(1988, 4, 2))
                .interests(new String[]{"семья", "прогулки"})
                .skills(new String[]{"менеджмент"})
                .social(new SocialPulse[]{new SocialPulse.Builder()
                        .link("facebook.com/profile/123123")
                        .name("facebook").build(),
                        new SocialPulse.Builder()
                                .link("vk.com/profile/123123")
                                .name("vk").build()}).build());

        profilePulses.add(new ProfilePulse.Builder()
                    .name("Мегрим Абдула")
                .address("Кутузовский проспект 32к1")
                .imageUrl(url + "/abdula.jpg")
                .dateOfBirth(LocalDate.of(1975, 4, 2))
                .interests(new String[]{"поддержка АС", "путешествия"})
                .skills(new String[]{"английский","общение с людьми"})
                .social(new SocialPulse[]{new SocialPulse.Builder()
                        .link("facebook.com/profile/123123")
                        .name("facebook").build(),
                        new SocialPulse.Builder()
                                .link("vk.com/profile/123123")
                                .name("vk").build()}).build());

        profilePulses.add(new ProfilePulse.Builder()
                .name("Петров Алексей Иванович")
                .address("Кутузовский проспект 32к1")
                .imageUrl(url + "/alexsei.jpg")
                .dateOfBirth(LocalDate.of(1987, 4, 2))
                .interests(new String[]{"программирование", "путешествия"})
                .skills(new String[]{"java","общение с людьми"})
                .social(new SocialPulse[]{new SocialPulse.Builder()
                        .link("facebook.com/profile/123123")
                        .name("facebook").build(),
                        new SocialPulse.Builder()
                                .link("vk.com/profile/123123")
                                .name("vk").build()}).build());

        profilePulses.add(new ProfilePulse.Builder()
                .name("Козлов Иван Иванович")
                .address("Кутузовский проспект 32к1")
                .imageUrl(url + "/ivan.jpg")
                .dateOfBirth(LocalDate.of(1970, 4, 2))
                .interests(new String[]{"управление людьми", "семья"})
                .skills(new String[]{"английский","яхтинг"})
                .social(new SocialPulse[]{new SocialPulse.Builder()
                        .link("facebook.com/profile/123123")
                        .name("facebook").build(),
                        new SocialPulse.Builder()
                                .link("vk.com/profile/123123")
                                .name("vk").build()}).build());


        return profilePulses;


    }


}
