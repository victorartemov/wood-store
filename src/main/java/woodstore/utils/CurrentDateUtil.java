package woodstore.utils;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Viktor_Artemov on 5/11/2017.
 */
@Component
public class CurrentDateUtil {

    public String getCurrentDate() {
        Date currentDate = new Date();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy");

        return dateFormatter.format(currentDate);
    }

    public String getCurrentDate(String pattern) {
        Date currentDate = new Date();
        SimpleDateFormat dateFormatter = new SimpleDateFormat(pattern);

        return dateFormatter.format(currentDate);
    }
}
