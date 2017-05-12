package woodstore.utils;

import org.springframework.stereotype.Component;

import java.text.DecimalFormat;

/**
 * Created by Viktor_Artemov on 5/12/2017.
 */

@Component
public class DecimalFormatUtil {

    public String getFormattedValue(Double value) {
        DecimalFormat decimalFormat = new DecimalFormat("#.0");

        return decimalFormat.format(value);
    }

    public String getFormattedValue(Double value, String pattern) {
        DecimalFormat decimalFormat = new DecimalFormat(pattern);

        return decimalFormat.format(value);
    }
}
