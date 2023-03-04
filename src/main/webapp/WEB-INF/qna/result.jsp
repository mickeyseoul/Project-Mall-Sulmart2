<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
SESSION : ${sessionScope['CAPTCHA']}
PARAM : ${param.captchaInput}
 
통과 : ${ sessionScope['CAPTCHA'] eq param.captchaInput ? 'O' : 'X'}