package woodstore.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import woodstore.model.Category;
import org.springframework.core.convert.converter.Converter;
import woodstore.service.impl.CategoryService;

/**
 * Created by Viktor_Artemov on 5/11/2017.
 */
@Component
public class CategoryIdToCategoryConverter implements Converter<String, Category> {

    @Autowired
    private CategoryService categoryService;

    @Override
    public Category convert(String id) {
        try {
            Long categoryId = Long.parseLong(id);
            return categoryService.findById(categoryId);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
