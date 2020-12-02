package ws.common.demo.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import ws.common.demo.pojo.User;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Objects;

/**
 * @author WindShadow
 * @version 2020/12/2.
 */

@Slf4j
@Controller
public class LoginController {

    private static final String CURRENT_USER = "CURRENT_USER";

    @RequestMapping("/login")
    public String loginPage(Map<String,Object> map) {

        return "login";
    }

    @RequestMapping("/toLogin")
    public String toLogin(@Validated User user, HttpSession session, Model model) {

        log.info("欲登入：{}", user == null ? "null" : user.toString());
        User admin = new User("admin","123456");
        if (admin.equals(user)) {

            session.setAttribute(CURRENT_USER, user);
            return "redirect:/success";
        }else {
            // 简单处理
            model.addAttribute("msg","登录失败");
            return "login";
        }
    }

    /**
     * 仅demo演示，不做拦截
     * @return
     */
    @RequestMapping("/success")
    @ResponseBody
    public String success(@SessionAttribute(CURRENT_USER) User user) {

        return "登录成功：" + user.toString();
    }
}
