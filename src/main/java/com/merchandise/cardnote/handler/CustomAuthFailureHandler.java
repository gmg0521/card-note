package com.merchandise.cardnote.handler;

import java.io.IOException;
import java.net.URLEncoder;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {

        String eMsg;

        if (exception instanceof BadCredentialsException) {
            eMsg = "아이디 또는 비밀번호가 맞지 않습니다. 다시 확인해 주세요!";
        } else if (exception instanceof InternalAuthenticationServiceException) {
            eMsg = "내부적으로 발생한 시스템 문제로 인해 요청을 처리할 수 없습니다. 관리자에게 문의하세요.";
        } else if (exception instanceof UsernameNotFoundException) {
            eMsg = "계정이 존재하지 않습니다. 회원가입 진행 후 로그인 해주세요!";
        } else if (exception instanceof AuthenticationCredentialsNotFoundException) {
            eMsg = "인증 요청이 거부되었습니다. 관리자에게 문의하세요!";
        } else {
            eMsg = "알 수 없는 이유로 로그인에 실패햐였습니다. 관리자에게 문의하세요!";
        }
        eMsg = URLEncoder.encode(eMsg, "UTF-8");
        setDefaultFailureUrl("/user/login?error=true&exception=" + eMsg);

        super.onAuthenticationFailure(request, response, exception);
    }

}
