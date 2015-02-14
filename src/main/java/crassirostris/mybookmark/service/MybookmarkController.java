package crassirostris.mybookmark.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by crassirostris on 15. 2. 7..
 */
@Controller
public class MybookmarkController {
    private static final String CODE = "cc";

    @Autowired
    private MybookmarkService service;

    @RequestMapping("/")
    public String index(@CookieValue(value = CODE, defaultValue = "") String code, Model model) {
        model.addAttribute("code", code);

        List<BookmarkItems> items = service.selectGetUrls(code);
        model.addAttribute("items", items);
        return "mybookmark/index";
    }

    @RequestMapping(value = "/code", method = RequestMethod.POST)
    public String insertCode(HttpServletResponse response, @ModelAttribute @Valid CollectorCode collector, BindingResult result) {
        service.insertCode(collector);
        response.addCookie(new Cookie(CODE, collector.getCode()));
        return "mybookmark/index";
    }

    @RequestMapping(value = "/url", method = RequestMethod.POST)
    public String insertUrl(@ModelAttribute @Valid BookmarkList url, BindingResult result) {
        service.insertUrl(url);
        return "mybookmark/index";
    }
}
