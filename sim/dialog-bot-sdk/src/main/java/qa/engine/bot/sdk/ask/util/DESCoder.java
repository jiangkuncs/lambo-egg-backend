package qa.engine.bot.sdk.ask.util;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.Key;

/**
 * 基于DES加密解密.
 * 
 * @author limm
 * 
 */
public class DESCoder {

    /** 加密算法 */
    public static final String KEY_ALGORTHM = "DES";

    private Key toKey(byte[] key) throws Exception {
        DESKeySpec dks = new DESKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEY_ALGORTHM);
        SecretKey secretKey = keyFactory.generateSecret(dks);
        return secretKey;
    }

    /**
     * 
     * @param key
     *            key(Base64编码）
     * @return
     * @throws Exception
     */
    public Key toKey(String key) throws Exception {
        byte[] keyBytes = Coder.decryptBASE64(key);
        Key keyObj = toKey(keyBytes);
        return keyObj;
    }

}