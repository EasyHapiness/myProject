package com.ya.song.zuulserver.common.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenUtil implements Serializable {

    private static final long serialVersionUID = -3301605591108950415L;
    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_CREATED = "created";
    private static Logger log = LoggerFactory.getLogger(JwtTokenUtil.class);

    /**
     * 设置token秘钥key
     */
    @Value("${jwt.secret}")
    private String secret;
    /**
     * 设置token过期时间单位小时
     */
    @Value("${jwt.expiration}")
    private Long expiration;
    /**
     * 设置token失效时间单位小时
     */
    @Value("${jwt.notBefore}")
    private Long notBefore;


    /**
     * 生成token
     *
     * @param userId
     * @return
     */
    public String generateToken(String userId) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, userId);
        claims.put(CLAIM_KEY_CREATED, new Date());
        return initToken(claims);
    }

    /**
     * 刷新token
     *
     * @param token
     * @return
     */
    public String refreshToken(String token) {
        String refreshedToken;
        final Claims claims = getClaimsFromToken(token);
        claims.put(CLAIM_KEY_CREATED, new Date());
        refreshedToken = initToken(claims);
        return refreshedToken;
    }

    //              .setId(id)                                      // JWT_ID
//                .setAudience("")                                // 接受者
//                .setClaims(null)                                // 自定义属性
//                .setSubject("")                                 // 主题
//                .setIssuer("")                                  // 签发者
//                .setIssuedAt(new Date())                        // 签发时间
//            .setNotBefore(new Date())                       // 失效时间
//            .setExpiration(long)                                // 过期时间
//                .signWith(signatureAlgorithm, secretKey);           // 签名算法以及密匙
    protected String initToken(Map<String, Object> claims) {
        log.info("secret-------------------->{}", secret);
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .setNotBefore(new Date())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 生成token失效时间
     *
     * @return
     */
    private Date generateNotBeforeDate() {
        return new Date(System.currentTimeMillis() + notBefore * 1000 * 60 * 60);
    }

    /**
     * 生成token过期时间
     *
     * @return
     */
    protected Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000 * 60 * 60);
    }

    /**
     * 校验token是否有效（在有效期内合法）
     *
     * @param token
     * @param userId
     * @return
     */
    public Boolean validateToken(String token, String userId) {
        String jwtUserId = getJwtUserIdFromToken(token);
        Date createDate = getCreatedDateFromToken(token);
        return (userId.equals(jwtUserId)
                && !isTokenExpired(token));
    }

    /**
     * 校验token是否合法
     *
     * @param token
     * @param userId
     * @return
     */
    public Boolean isValidateToken(String token, String userId) {
        String jwtUserId = getJwtUserIdFromToken(token);
        return userId.equals(jwtUserId);
    }

    /**
     * 判断token是否过期
     *
     * @param token
     * @return
     */
    protected Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    /**
     * 根据token获取用户id
     *
     * @param token
     * @return
     */
    public String getJwtUserIdFromToken(String token) {
        String userId = null;
        final Claims claims = getClaimsFromToken(token);
        userId = claims.getSubject();
        return userId;
    }

    /**
     * 获取token创建时间
     *
     * @param token
     * @return
     */
    public Date getCreatedDateFromToken(String token) {
        Date created = null;
        final Claims claims = getClaimsFromToken(token);
        created = new Date((Long) claims.get(CLAIM_KEY_CREATED));
        return created;
    }

    /**
     * 获取token过期时间
     *
     * @param token
     * @return
     */
    public Date getExpirationDateFromToken(String token) {
        Date expiration;
        final Claims claims = getClaimsFromToken(token);
        expiration = claims.getExpiration();
        return expiration;
    }

    /**
     * 获取token失效时间
     *
     * @param token
     * @return
     */
    public Date getNotBeforeDateFromToken(String token) {
        Date notBefore;
        final Claims claims = getClaimsFromToken(token);
        notBefore = claims.getNotBefore();
        return notBefore;
    }

    /**
     * 根据token获取Claims
     *
     * @param token
     * @return
     */
    public Claims getClaimsFromToken(String token) {
        Claims claims = null;
        claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }
}

