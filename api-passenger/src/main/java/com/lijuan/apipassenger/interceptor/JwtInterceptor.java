package com.lijuan.apipassenger.interceptor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.lijuan.internalcommon.dto.ResponseResult;
import com.lijuan.internalcommon.util.JwtUtils;
import net.sf.json.JSONObject;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class JwtInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        String resultStr = "";
        boolean result = true;
        try {
            JwtUtils.parseToken(token);
        } catch (SignatureVerificationException e) {
            resultStr = "token sign error";
            result = false;
        } catch (TokenExpiredException e){
            resultStr = "token is expired";
            result = false;
        } catch(AlgorithmMismatchException e){
            resultStr = "token invalid";
            result = false;
        }

        if(!result){
            PrintWriter out = response.getWriter();
            out.print(JSONObject.fromObject(ResponseResult.fail(resultStr)).toString());
        }

        return result;
    }
}
