package ws.common.demo.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import ws.common.demo.pojo.User;
import ws.common.verificationcode.support.ConstantOfVerifyCode;

import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * 简单示例，登录验证控制层
 * @author WindShadow
 * @version 2020/12/2
 * @since 1.0
 */

@Slf4j
@Controller
public class LoginController {

    private static final String CURRENT_USER = "CURRENT_USER";

    @RequestMapping("/login")
    public String loginPage() {

        return "login";
    }

    /**
     * 处理登录请求
     * @param user 用户名，密码
     * @param verifyCode 前端输入的验证码
     * @param relVerifyCode 真实的验证码，若系统重新启动或session过期，此值为null，因为设置了{@link SessionAttribute#required()}属性为false，如设置为true，可能会发生解析异常
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("/toLogin")
    public String toLogin(@Validated User user, String verifyCode,
                          @SessionAttribute(value = ConstantOfVerifyCode.VERIFY_CODE_SESSION_KEY, required = false) String relVerifyCode,
                          HttpSession session, Model model) {

        log.info("欲登入者：{} 输入的验证码：{}", user == null ? "null" : user.toString(), verifyCode);
        String errMsg;
        if (relVerifyCode == null) {

            errMsg = "验证码已过期";
        }else {
            User admin = new User("admin","123456");
            // 比较验证码，通过了再简单验证用户名和密码
            boolean isVerifyCode = verifyCode != null && Objects.equals(verifyCode.toUpperCase(), relVerifyCode.toUpperCase());
            if (!isVerifyCode) {

                errMsg = "验证码错误";
            }else if (admin.equals(user)) {

                session.setAttribute(CURRENT_USER, user);
                return "redirect:/success";
            }else {

                errMsg = "登录失败";
            }
        }
        // 简单处理
        log.info("验证失败：{}",errMsg);
        model.addAttribute("msg",errMsg);
        return "login";
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
