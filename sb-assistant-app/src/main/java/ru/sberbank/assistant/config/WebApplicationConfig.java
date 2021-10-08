package ru.sberbank.assistant.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.sberbank.assistant.converter.StringToKudagoCategoryConverter;
import ru.sberbank.assistant.converter.StringToKudagoLocationConverter;
import ru.sberbank.assistant.converter.StringToPlaceTypeConverter;

import java.util.Arrays;
import java.util.List;

@Configuration
@ComponentScan("ru.sberbank.assistant")

public class WebApplicationConfig implements WebMvcConfigurer {


    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(stringHttpMessageConverter());
        converters.add(byteArrayHttpMessageConverter());
        converters.add(resourceHttpMessageConverter());
        converters.add(mappingJackson2HttpMessageConverter());
    }

    private MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        return new MappingJackson2HttpMessageConverter();
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToKudagoLocationConverter());
        registry.addConverter(new StringToKudagoCategoryConverter());
        registry.addConverter(new StringToPlaceTypeConverter());
    }


    private ByteArrayHttpMessageConverter byteArrayHttpMessageConverter() {
        ByteArrayHttpMessageConverter arrayHttpMessageConverter = new ByteArrayHttpMessageConverter();
        arrayHttpMessageConverter.setSupportedMediaTypes(Arrays.asList(
                MediaType.IMAGE_JPEG,
                MediaType.IMAGE_PNG,
                MediaType.APPLICATION_OCTET_STREAM,
                MediaType.valueOf("application/vnd.ms-excel"),
                MediaType.valueOf("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"),
                MediaType.valueOf("application/zip")
        ));
        return arrayHttpMessageConverter;
    }

    private StringHttpMessageConverter stringHttpMessageConverter() {
        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter();
        stringHttpMessageConverter.setSupportedMediaTypes(Arrays.asList(
                MediaType.valueOf("image/svg+xml"),
                MediaType.TEXT_PLAIN,
                MediaType.TEXT_HTML,
                MediaType.TEXT_MARKDOWN,
                MediaType.APPLICATION_JSON
        ));
        return stringHttpMessageConverter;
    }

    private ResourceHttpMessageConverter resourceHttpMessageConverter() {
        ResourceHttpMessageConverter resourceHttpMessageConverter = new ResourceHttpMessageConverter();
        resourceHttpMessageConverter.setSupportedMediaTypes(Arrays.asList(
                MediaType.IMAGE_JPEG,
                MediaType.IMAGE_PNG,
                MediaType.APPLICATION_OCTET_STREAM,
                MediaType.valueOf("application/vnd.ms-excel"),
                MediaType.valueOf("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"),
                MediaType.valueOf("application/zip")
        ));
        return resourceHttpMessageConverter;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedHeaders("*").allowedMethods("*");
    }

    @Override
    public void configureContentNegotiation(
            ContentNegotiationConfigurer configurer
    ) {
        configurer.favorPathExtension(false).favorParameter(true)
                .parameterName("mediaType").ignoreAcceptHeader(true)
                .useRegisteredExtensionsOnly(false)
                .defaultContentType(MediaType.APPLICATION_JSON)
                .mediaType("xml", MediaType.APPLICATION_XML)
                .mediaType("json", MediaType.APPLICATION_JSON);
    }


}
