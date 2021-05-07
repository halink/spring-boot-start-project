package com.halink.scaffold.modular.service.impl;

import com.halink.scaffold.common.constant.CodeConstants;
import com.halink.scaffold.common.constant.MessageConstants;
import com.halink.scaffold.common.constant.SystemConstants;
import com.halink.scaffold.common.exception.VerificationCodeException;
import com.halink.scaffold.core.util.VerificationCodeUtils;
import com.halink.scaffold.modular.service.VerificationCodeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.Duration;

/**
 * 验证码服务
 *
 * @author halink
 * @date 2020/9/29 4:00 下午
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class VerificationCodeServiceImpl implements VerificationCodeService {

    private final RedisTemplate<String, String> redisTemplate;

    /**
     * 生成图片验证码
     *
     * @param request  request
     * @param response response
     */
    @Override
    public void image(HttpServletRequest request, HttpServletResponse response) {
        String jSessionId = request.getSession().getId();
        VerificationCodeUtils instance = VerificationCodeUtils.getInstance();
        BufferedImage image = instance.getImage();
        redisTemplate.opsForValue().set(jSessionId, instance.getText(), Duration.ofSeconds(SystemConstants.FIVE_MINUTES_SECONDS));
        try {
            ImageIO.write(image, "jpeg", response.getOutputStream());
        } catch (IOException e) {
            throw new VerificationCodeException(CodeConstants.VERIFICATION_CODE_GENERATE_EXCEPTION, MessageConstants.VERIFICATION_CODE_GENERATE_EXCEPTION);
        }
    }
}
