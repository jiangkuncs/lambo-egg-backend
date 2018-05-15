package qa.engine.bot.sdk.ask.util;

public class Constant {

    // 调用智能问答服务的uri
    public static final String ASK_URI = "service/qa/bot/sdk/talk";

    // 调用智能问答服务的url
    // public static final String ASK_URL =
    // "http://10.110.13.172:8080/dialog-bot/service/qa/bot/sdk/talk";
    public static final String ASK_URL = "http://10.110.13.171:8080/dialog-bot/service/qa/bot/sdk/talk";

    // 答案数量
    public static final String ANSWER_NUMBER = "1";

    public static final String ENCODING = "utf-8";
    public static final String WEIXIN_PLATFORM = "weixin";
    public static final String YIXIN_PLATFORM = "yixin";
    public static final String SINAWEIBO_PLATFORM = "wbfan";
    public static final String IOS_PLATFORM = "ios";
    public static final String ANDROID_PLATFORM = "android";
    public static final String CUSTOM_PLATFORM = "custom";
    public static final String POST_METHOD = "POST";
    public static final String GET_METHOD = "GET";
    public static final String PRIMARY_TYPE = "0";
    public static final String SENIOR_TYPE = "1";
    public static final String ENCHARS = "abcdefghijklmnopqrstuvwxyz";
    public static final boolean RETURNTYPE = false;

    // 连接超时时间
    public static final int CONNECTION_TIMEOUT = 3000;
    // 访问内容时间
    public static final int READ_TIMEOUT = 10000;

}