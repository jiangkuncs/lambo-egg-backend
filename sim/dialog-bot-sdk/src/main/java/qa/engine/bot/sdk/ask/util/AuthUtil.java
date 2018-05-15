package qa.engine.bot.sdk.ask.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.Key;
import java.util.Map;

/**
 * 生成访问智能问答程序的url.
 *
 */
public class AuthUtil {

    /**
     * 利用jwt生成token信息.
     * 
     * @param claims
     *            数据声明（Claim）其实就是一个Map，比如我们想放入用户名， 可以简单的创建一个Map然后put进去
     * @param secret
     *            用于进行签名的秘钥
     * @return
     * @throws Exception
     */
    public static String generateToken(Map<String, Object> claims, String secret) throws Exception {
        DESCoder desCoder = new DESCoder();
        Key key = desCoder.toKey(secret);
        // 设置过期时间为10分钟
        // Date ecpiration = new Date(System.currentTimeMillis()+600000L);
        return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, key) // 采用什么算法是可以自己选择的，不一定非要采用HS512
                .compact();
    }
}
