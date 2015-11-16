package com.zhytnik.library.web.command.category;

import com.zhytnik.library.entity.Category;
import com.zhytnik.library.service.CategoryService;
import com.zhytnik.library.service.ServiceFactory;
import com.zhytnik.library.web.Request;
import com.zhytnik.library.web.command.SubmitCommand;
import com.zhytnik.library.web.parser.CategoryParser;
import com.zhytnik.library.web.view.RedirectView;
import com.zhytnik.library.web.view.View;

public class UpdateCategoryCommand extends SubmitCommand {
    @Override
    public View execute(Request request) {
        super.execute(request);

        Category category = new CategoryParser().parseFullCategory(request);

        CategoryService service = ServiceFactory.getInstance().getCategoryService();
        service.update(category);

        return new RedirectView("/categories/show");
    }
}