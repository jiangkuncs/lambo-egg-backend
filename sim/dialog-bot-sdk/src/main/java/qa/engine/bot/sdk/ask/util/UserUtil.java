package qa.engine.bot.sdk.ask.util;

/**
 * 用于产生userId
 *
 * @author panxinbing
 */
public class UserUtil {
    /**
     * 随机生成sessionId
     * 
     * @return 返回字符串
     */
    public static String getRndSessionId() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 8; i++) {
            int index = (int) Math.floor(Math.random() * 26.0D);
            sb.append(Constant.ENCHARS.charAt(index));
        }
        return sb.toString();
    }

    /**
     * 随机生成userId
     * 
     * @return 返回字符串
     */
    public static String getRndUserId() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 8; i++) {
            int index = (int) Math.floor(Math.random() * 26.0D);
            sb.append(Constant.ENCHARS.charAt(index));
        }
        return sb.toString();
    }
}
