package ru.sberbank.assistant.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sberbank.assistant.model.pulse.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@Api(value = "Stubs for thirdparty systems")
@RequestMapping(path = "api/stub/v1")
public class StubThirdpartyController {

    private String url;

    public StubThirdpartyController(Environment environment) {
        url = environment.getProperty("sb.assistant.image.url");
    }

//    @PostConstruct
//    private void postConstruct() {
//
//
//        url = "http://" + InetAddress.getLoopbackAddress().getHostName() + ":" +
//                environment.getProperty("server.port") +
//                environment.getProperty("server.servlet.context-path") + "/images";
//    }

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

        //Random Coffee
        growthPulses.add(new GrowthPulse.Builder()
                .name("Random Coffee")
                .type("growth")
                .description("Предлагаем тебе человека, близкого по интересам, а неловкость знакомства — берем на себя")
                .tags(new String[]{"java", "mock"})
                .duration(520)
                .rating(4.7d)
                .numOfViews(27)
                .imageUrl(url + "/randomcoffee.jpg")
                .courseUrl("https://sberbank-school.ru/programs/8627/about#")
                .knowledgeFor("Тем, кто ищет друзей или вдохновляющих собеседников " +
                        "Встречи с интересными людьми разных профессий" +
                        "Кто хочет быстро расширить список контактов" +
                        "Продуманные нетворкинг-сессии под руководством модератора" +
                        "Ищущим рост в своём деле")
                .build());

        //обучения
        growthPulses.add(new GrowthPulse.Builder()
                .name("SberMock - централизованный сервис управления эмуляциями")
                .type("growth")
                .description("Процесс интеграционного взаимодействия хорошо представлен в электронном курсе «Инструменты производственного процесса: META». С ним вы быстро и качественно научитесь проектировать системные решения.")
                .tags(new String[]{"java", "mock"})
                .duration(520)
                .rating(4.7d)
                .numOfViews(27)
                .imageUrl(url + "/education-sberMock.png")
                .courseUrl("https://sberbank-school.ru/programs/8627/about#")
                .knowledgeFor("На хакатоне пригодится! ;)")
                .build());

        //СберИдея
        growthPulses.add(new GrowthPulse.Builder()
                .name("Продуктовая мастерская")
                .type("growth")
                .description("программа развития продукта и команды продукта за счет внедрения лучших продуктовых практик, инструментов, интенсивной работы с продуктовыми треккерами, внешними экспертами и спикерами")
                        .tags(new String[]{"product", "agile"})
                        .duration(0)
                        .rating(4.8d)
                        .numOfViews(159)
                        .imageUrl(url + "/product-mastery.png")
                        .courseUrl("https://www.sbidea.ru/product-office/main")
                        .knowledgeFor("Эффективность — это делать wow‑продукты\n" +
                                "Выведи продуктовую команду на новый уровень. )")
                        .build());

        growthPulses.add(new GrowthPulse.Builder()
                .name("Модель компетенций 2023")
                .type("growth")
                .description("Что поможет при встрече с неизвестным? \n" +
                        "Модель проверена временем, но обновлена в соответствии с требованиями нового мира.")
                .tags(new String[]{"product", "agile"})
                .duration(0)
                .rating(4.9d)
                .numOfViews(199)
                .imageUrl(url + "/model2023.jpeg")
                .courseUrl("https://www.sbidea.ru/mk2023/promo")
                .knowledgeFor("Развивай важнейшие группы компетенций в рамках программы 2023")
                .build());

        growthPulses.add(new GrowthPulse.Builder()
                .name("Школы новых профессий ПЕРЕЗАПУСК 2.0")
                .type("growth")
                .description("Система переподготовки сотрудников на востребованные специальности уровня Junior. \n" +
                        "Сейчас проходит подготовка на направления Java Developer, Data Scientist, Data Analyst, Data Engineer и другие.\n" +
                        "Обучение проходит дистанционно в нерабочее время.")
                .tags(new String[]{"developer", "agile"})
                .duration(0)
                .rating(4.8d)
                .numOfViews(2419)
                .imageUrl(url + "/reboot2020.jpeg")
                .courseUrl("https://www.sbidea.ru/mk2023/promo")
                .knowledgeFor("Получишь основы востребованной профессии от ведущих внутренних экспертов.\n" +
                        "Освоишь языки программирования.\n" +
                        "Погрузишься в профессиональное сообщество.\n" +
                        "Сможешь претендовать на перспективные вакансии в Сбере в новой роли.")
                .build());

        growthPulses.add(new GrowthPulse.Builder()
                .name("SberUp")
                .type("growth")
                .description("Программа интенсивного обучения предпринимательству сотрудников экосистемы Сбера.")
                .tags(new String[]{"developer", "agile"})
                .duration(0)
                .rating(4.8d)
                .numOfViews(2419)
                .imageUrl(url + "/sberUp1.png")
                .courseUrl("https://sber-up.ru/")
                .knowledgeFor("Познакомишься с руководителями компаний Экосистемы Сбера. \n" +
                        "Получишь возможность запустить с ними пилот или коммерческий контракт. \n" +
                        "Получишь бесценную экспертизу по шагам развития стартапа от профессионалов рынка, которые помогут в решении разных вопросов по развитию бизнеса.\n" +
                        "Приобретёшь не только впечатляющие связи в предпринимательской среде, но и шанс получить инвестиции на свой стартап или ресурсы для развития проекта внутри Сбера.\n" +
                        "Прокачаешь свои навыки работы в команде, целеполагания и публичных выступлений.")
                .build());

        growthPulses.add(new GrowthPulse.Builder()
                .name("Лучший по профессии!")
                .type("growth")
                .description("\n" +
                        "Конкурс профессионального мастерства.\n" +
                        "Победители будут определены по итогам оценки корпоративных компетенций: профессиональных и «мягких навыков».\n" +
                        "Лучший по профессии - это возможность:\n" +
                        "развиваться в профессии и строить карьеру;\n" +
                        "ставить перед собой амбициозные цели;\n" +
                        "получить стимул для личного и профессионального роста.")
                .tags(new String[]{"developer", "agile"})
                .duration(0)
                .rating(4.8d)
                .numOfViews(2419)
                .imageUrl(url + "/bestProfi.jpeg")
                .courseUrl("https://sbidea.ru/lp2021/main\n")
                .knowledgeFor("Приобретешь веру в себя, признание коллег.\n" +
                        "Заведешь новые интересные знакомства.\n" +
                        "Прокачаешь свои навыки в хакатоне и бизнес-играх.\n" +
                        "\n" +
                        "Победители и призеры будут награждены:\n" +
                        "знаками отличия «Лучший по профессии»,\n" +
                        "сертификатом на программу обучения «Лидеры будущего»,\n" +
                        "денежной премией.")
                .build());

        growthPulses.add(new GrowthPulse.Builder()
                .name("Сбер Профи")
                .type("growth")
                .description("Профессиональные объединения сотрудников для обмена лучшими практиками, профессионального развития и построения экспертной карьеры.")
                .tags(new String[]{"developer", "agile"})
                .duration(0)
                .rating(4.8d)
                .numOfViews(2419)
                .imageUrl(url + "/sberProfi.png")
                .courseUrl("https://sbidea.ru/sberprofi/main")
                .knowledgeFor("Внесешь свой вклад в профессиональное развитие коллег и усиление бренда Сбера.\n" +
                        "Прокачаешь профессиональные и «мягкие навыки».\n" +
                        "Повысишь свою visibility.\n" +
                        " Расширишь свой круг профессиональных контактов.")
                .build());

        //Видео
        growthPulses.add(new GrowthPulse.Builder()
                .name("Как выиграть хакатон")
                .type("growth")
                .description("Секреты, как выигрывать хакатоны и рассказ про победу в международном хакатоне от McKinsey.")
                .tags(new String[]{"developer", "agile"})
                .duration(0)
                .rating(4.8d)
                .numOfViews(2419)
                .imageUrl(url + "/bestProfi.jpeg")
                .courseUrl("https://www.youtube.com/watch?v=AhBEBZRtpx0\n")
                .knowledgeFor("Из этого видео вы сможете узнать:\n" +
                        "- Что мечтает услышать каждый организатор хакатона в вашем решении\n" +
                        "- Как правильно работать над основными частями хакатона: бизнес частью, алгоритмами, презентацией решения.\n" +
                        "- Еще раз о пользе target encoding и cross-validation\n")
                .build());

        //Саморазвитие
        growthPulses.add(new GrowthPulse.Builder()
                .name("Если хочешь стать осознанным родителем, то присоединяйся\n")
                .type("growth")
                .description("Портал «Сбер для детей и родителей».\n" +
                        "Линейка обучающих программ по развитию родительских компетенций:")
                .tags(new String[]{"developer", "agile"})
                .duration(0)
                .rating(4.8d)
                .numOfViews(2419)
                .imageUrl(url + "/betterParent1.png")
                .courseUrl("https://www.sbidea.ru/self-realization#parents\n")
                .knowledgeFor("Зачем это мне?\n" +
                        "Сбер поддерживает сотрудников в разных ролях, и с особым вниманием мы относимся к нашим сотрудникам-родителям и тем, кто готовится ими стать. Семья, родительство, партнеры, дети, близкие, друзья – это то, что объединяет многих из нас и важно для каждого, ведь социальные связи и личные отношения - один из пяти элементов благополучия.\n" +
                        "\n" +
                        "Присутствие в нашей жизни понимания, поддержки и заботы со стороны семьи и друзей создаёт нам «подушку безопасности», благодаря которой мы более спокойны, рассудительны и креативны, легче принимаем рациональные решения, лучше управляем конфликтами (или взвешенно избегаем их), реже ошибаемся, быстрее решаем даже самые сложные задачи.")
                .build());

        growthPulses.add(new GrowthPulse.Builder()
                .name("Запишись на зеленый марафон")
                .type("growth")
                .description("\n" +
                        "Ежегодный проект Сбера, цель которого привлечь внимание к проблемам экологии и пригласить жителей России сделать несколько простых шагов, которые помогут в их решении.\n" +
                        "Сбер, БФ Сбербанка «Вклад в будущее» и СберВместе объединят жителей России, НКО и другие стороны для участия в экологических инициативах «Зелёного Марафона».")
                .tags(new String[]{"developer", "agile"})
                .duration(0)
                .rating(4.8d)
                .numOfViews(2419)
                .imageUrl(url + "/greenMarathon.jpeg")
                .courseUrl("https://greenmarathon.ru/app")
                .knowledgeFor("\n" +
                        "Человек живёт, не замечая масштабов своего потребления: питается, пользуется автомобилем, выбрасывает мусор и выдыхает углекислый газ. Для полноценной жизни нам нужны здоровая еда, чистая вода, свежий воздух. В течение всей жизни мы расходуем ресурсы планеты. Мера скорости и разрушительности воздействия человека на окружающую среду называется Экологический след. Единица измерения – глобальный гектар. Экослед человечества в 1,6 раз превышает возможности планеты. Любой из нас в силах внести свой вклад в решение этой проблемы.")
                .build());



        //Опросы
        growthPulses.add(new GrowthPulse.Builder()
                .name("Срочно нужна обратная связь!")
                .type("survey")
                .description("Отбор респондентов для интервью по клиентскому опыту. Ищем респондентов для опросов и интервью")
                .tags(new String[]{"survey", "agile"})
                .duration(0)
                .rating(4.6d)
                .numOfViews(2419)
                .imageUrl(url + "/helpSurvey.jpeg")
                .courseUrl("https://www.sbidea.ru/CJM")
                .knowledgeFor("")
                .build());

        growthPulses.add(new GrowthPulse.Builder()
                .name("Помоги сделать СитиМобил лучше!")
                .type("survey")
                .description("Вместе сделаем Ситимобил лучшим сервисом заказа такси на рынке!\n" +
                        "\n" +
                        "Мы – команда Клиентского опыта.\n" +
                        "Каждый квартал мы собираем и передаем продукту лучшие идеи по его развитию.\n")
                .tags(new String[]{"survey", "agile"})
                .duration(0)
                .rating(4.6d)
                .numOfViews(2419)
                .imageUrl(url + "/cityMobil.png")
                .courseUrl("https://sbidea.ru/improject-429085")
                .knowledgeFor("Если вы знаете, как закрыть конкретную «боль» клиента – скорее пишите нам.\n" +
                        "Важно не только предлагать свои идеи, но и поддерживать отличные идеи коллег: лайкайте другие предложения - вам несложно, коллегам приятно, а продукту понятно, что идея – топчик\uD83D\uDE0E.\n" +
                        "Увидели крутую идею и знаете как сделать еще лучше? Комментируйте! Ведь одна голова хороша, а все сотрудники Сбера лучше \uD83D\uDE0E.")
                .build());

        growthPulses.add(new GrowthPulse.Builder()
                .name("Помоги сделать Сбер лучше!")
                .type("survey")
                .description("Предлагаем вам принять участие в улучшении процессов и продуктов Сбера!")
                .tags(new String[]{"survey", "agile"})
                .duration(0)
                .rating(4.6d)
                .numOfViews(2419)
                .imageUrl(url + "/sberIdeaOctober.jpg")
                .courseUrl("https://sbidea.ru/improject-429103\n")
                .knowledgeFor("Поделитесь своими идеями по улучшению процессов и продуктов. Вместе мы можем улучшить, то, что мешает нашему движению вперёд, что можно и нужно внедрить уже сейчас.\n" +
                        "\n" +
                        "Предложите что-то новое для развития Сбера.\n" +
                        "\n" +
                        "Предлагайте любые идеи, соответствующие правилам")
                .build());
        growthPulses.add(new GrowthPulse.Builder()
                .name("Помоги сделать СберЗдоровье лучше!")
                .type("survey")
                .description("Вместе сделаем СберЗдоровье лучшим сервисом телемедицины на рынке!\n" +
                        "\n" +
                        "Мы – команда Клиентского опыта.\n" +
                        "Каждый квартал мы собираем и передаем продукту лучшие идеи по его развитию.\n")
                .tags(new String[]{"survey", "agile"})
                .duration(0)
                .rating(4.6d)
                .numOfViews(2419)
                .imageUrl(url + "/sberHealth.png")
                .courseUrl("https://sbidea.ru/improject-429082")
                .knowledgeFor("Если вы знаете, как закрыть конкретную «боль» клиента – скорее пишите нам.\n" +
                        "Важно не только предлагать свои идеи, но и поддерживать отличные идеи коллег: лайкайте другие предложения - вам несложно, коллегам приятно, а продукту понятно, что идея – топчик\uD83D\uDE0E.\n" +
                        "Увидели крутую идею и знаете как сделать еще лучше? Комментируйте! Ведь одна голова хороша, а все сотрудники Сбера лучше \uD83D\uDE0E.")
                .build());
        growthPulses.add(new GrowthPulse.Builder()
                .name("Помоги сделать СберЗвук лучше!")
                .type("survey")
                .description("Вместе сделаем СберЗвук лучшим музыкальным сервисом на рынке!!\n" +
                        "\n" +
                        "Мы – команда Клиентского опыта.\n" +
                        "Каждый квартал мы собираем и передаем продукту лучшие идеи по его развитию.\n")
                .tags(new String[]{"survey", "agile"})
                .duration(0)
                .rating(4.6d)
                .numOfViews(2419)
                .imageUrl(url + "/sberSound.png")
                .courseUrl("https://sbidea.ru/improject-429084")
                .knowledgeFor("Если вы знаете, как закрыть конкретную «боль» клиента – скорее пишите нам.\n" +
                        "Важно не только предлагать свои идеи, но и поддерживать отличные идеи коллег: лайкайте другие предложения - вам несложно, коллегам приятно, а продукту понятно, что идея – топчик\uD83D\uDE0E.\n" +
                        "Увидели крутую идею и знаете как сделать еще лучше? Комментируйте! Ведь одна голова хороша, а все сотрудники Сбера лучше \uD83D\uDE0E.")
                .build());

        //

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
                .skills(new String[]{"английский", "общение с людьми"})
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
                .skills(new String[]{"java", "общение с людьми"})
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
                .skills(new String[]{"английский", "яхтинг"})
                .social(new SocialPulse[]{new SocialPulse.Builder()
                        .link("facebook.com/profile/123123")
                        .name("facebook").build(),
                        new SocialPulse.Builder()
                                .link("vk.com/profile/123123")
                                .name("vk").build()}).build());


        return profilePulses;


    }


}
