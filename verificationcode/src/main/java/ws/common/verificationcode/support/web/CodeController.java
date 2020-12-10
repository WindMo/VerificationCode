package ws.common.verificationcode.support.web;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author WindShadow
 * @since 1.0
 */

@Slf4j
@Controller
public class CodeController {

    @Autowired
    private Producer producer;
    @GetMapping("/verifyCode")
    public void getVerifyCodeImage(HttpSession session, HttpServletResponse response) throws IOException {

        // 设置响应头
        setHeaders(response);
        //生成验证码
        String capText = producer.createText();
        log.info("当前验证码：{}",capText);
        //向客户端写出
        BufferedImage bi = producer.createImage(capText);
        try (ServletOutputStream out = response.getOutputStream()){

            ImageIO.write(bi, "jpg", out);
            out.flush();
            session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
        }
    }

    /**
     * IO异常处理方法<br>
     * 处理请求的方法使用了{@link HttpServletResponse}作为参数，则此异常处理的返回值无法影响响应结果，
     * 即返回值为 void的异常处理方法得搭配{@link HttpServletResponse}入参数的mapping一起用
     * @param e 本控制层发生的IO异常
     */
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR,reason = "验证码图片响应异常")
    @ExceptionHandler(IOException.class)
    public void exceptionHandler(IOException e) {

        log.error("验证码写入客户端发生异常",e);
    }

    private void setHeaders(HttpServletResponse response) {

        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
    }
}
