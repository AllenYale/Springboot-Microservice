package com.travischenn.commonweb.web.service.impl.authentication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Date;

@Service("jwtAuthenticationServer")
public class JwtAuthenticationServer {

    private static Key KEY = MacProvider.generateKey();

    /**
     * 生成 JWT 标签
     * @param username JWT 标签签发对象
     * @return         JWT
     */
    public static String getJWT(String username) {

        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + 2 * 60 * 60 * 1000))
                .signWith(SignatureAlgorithm.HS512, KEY)
                .compact();

    }

    /**
     * 解析 JWT
     * @return true 解析成功 false 解析失败
     */
    public static String parserJWT(String compactJws) {

        try{
            Jwts.parser().setSigningKey(KEY).parseClaimsJws(compactJws).getBody().getSubject();
        }catch (Exception e){
            throw new RuntimeException("JWT 解析失败,请引导用户重新获取新的凭证");
        }
        return Jwts.parser().setSigningKey(KEY).parseClaimsJws(compactJws).getBody().getSubject();
    }

    public static String getCurrentUsername(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        if (!StringUtils.isBlank(token)) {
            return JwtAuthenticationServer.parserJWT(StringUtils.substring(token , 6 , token.length()));
        }
        return null;
    }

}
