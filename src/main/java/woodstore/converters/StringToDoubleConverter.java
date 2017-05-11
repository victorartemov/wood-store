package woodstore.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by Viktor_Artemov on 5/11/2017.
 */
@Component
public class StringToDoubleConverter implements Converter<String, Double> {
    @Override
    public Double convert(String input) {

        input = input.replaceAll(",", ".");

        try {
            double result = Double.parseDouble(input);
            return result;
        } catch (Exception e){
            return 0.0;
        }
    }
}
