package woodstore.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import woodstore.converters.CategoryIdToCategoryConverter;
import woodstore.converters.StringToDoubleConverter;

import java.util.List;

/**
 * Created by Viktor_Artemov on 3/3/2017.
 */
@Configuration
public class MVCConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        final MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        converter.setObjectMapper(objectMapper);
        converters.add(converter);
        super.configureMessageConverters(converters);
    }

    @Autowired
    CategoryIdToCategoryConverter categoryIdToCategoryConverter;

    @Autowired
    StringToDoubleConverter stringToDoubleConverter;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(categoryIdToCategoryConverter);
        registry.addConverter(stringToDoubleConverter);
    }
}
