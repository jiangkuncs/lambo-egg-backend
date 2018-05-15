package qa.engine.bot.sdk.ask.impl;

import org.apache.commons.lang.StringUtils;
import qa.engine.bot.sdk.ask.*;
import qa.engine.bot.sdk.ask.util.AuthUtil;
import qa.engine.bot.sdk.ask.util.Constant;
import qa.engine.bot.sdk.ask.util.HttpClientUtil;
import qa.engine.bot.sdk.ask.util.UserUtil;

import java.util.HashMap;
import java.util.Map;

public class AskServiceImpl implements AskService {
    private String askUrl;
    private String askUri;
    private int connectionTimeout;
    private int readTimeout;
    private Boolean isInited = false;
    private String answerNumber;
    private String topic;
    private boolean returnType;

    /**
     * 初始化智能问答的发送post请求前端的参数
     */
    public void init(CloudConfiguration config) {
        // 设置默认的请求方法参数
        this.isInited = true;
        // 请求的url
        this.askUrl = Constant.ASK_URL;
        // 请求的uri
        this.askUri = Constant.ASK_URI;
        // 设置最长请求连接时间
        this.connectionTimeout = Constant.CONNECTION_TIMEOUT;
        // 设置最长读取时间
        this.readTimeout = Constant.READ_TIMEOUT;
        // 当前的主题
        this.topic = "";
        // 答案的数量
        this.answerNumber = Constant.ANSWER_NUMBER;
        //返回值类型
        this.returnType = Constant.RETURNTYPE;
        
        

        if (config != null) {
            if (StringUtils.isNotBlank(config.getUrl()))
                this.askUrl = config.getUrl();
            if (StringUtils.isNotBlank(config.getUri()))
                this.askUri = config.getUri();
            if (config.getConnectionTimeout() > 0)
                this.connectionTimeout = config.getConnectionTimeout();
            if (config.getReadTimeout() > 0) {
                this.readTimeout = config.getReadTimeout();
            }
            if (StringUtils.isNotBlank(config.getAnswer_number())) {
                this.answerNumber = config.getAnswer_number();
            }
            if (StringUtils.isNotBlank(config.getTopic())) {
                this.topic = config.getTopic();
            }
            if(config.isInvokeServParam()){
                this.returnType = config.isInvokeServParam();
            }
            
        }

    }

    /**
     * 调用智能问答程序
     */
    public AskResponse ask(AskRequest request) throws CloudNotInitializedException {

        // 判断智能问答程序是否初始化
        if (!this.isInited.booleanValue()) {
            throw new CloudNotInitializedException();
        }

        String userId = request.getUserId();
        String appKey = request.getAppKey();
        String appSecret = request.getAppSecret();
        String question = request.getQuestion();
        String type = request.getType();
        String platform = request.getPlatform();
        String sessionId = request.getSessionId();

        // 判断是否具有userId,如果不存在userId，则随机生成一个userId
        if (StringUtils.isBlank(userId))
            userId = UserUtil.getRndSessionId();

        // 判断是否具有sessionId,如果不存在sessionId，则随机生成一个sessionId
        if (StringUtils.isBlank(sessionId))
            sessionId = UserUtil.getRndSessionId();

        // 判断用户的级别，如果没有设置，则默认为普通用户
        if (StringUtils.isBlank(type)) {
            type = "0";
        }

        // 设置参数
        Map<String, Object> params = new HashMap<>();
        params.put("question", question);
        params.put("appKey", appKey);
        params.put("type", type);
        params.put("platform", platform);
        params.put("answerNumber", this.answerNumber);
        params.put("topic", this.topic);
        params.put("sessionId", sessionId);
        //添加是否转url的参数
        params.put("returnType", this.returnType);

        String token;
        AskResponse askResponse = null;

        // 设置请求参数
        Map<String, String> param = new HashMap<>();
        try {
            token = AuthUtil.generateToken(params, appSecret);
            param.clear();
            param.put("appKey", appKey);
            param.put("token", token);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            return askResponse = new AskResponse(404, "tocken获取失败");
        }

        // 发送post请求，得到返回的结果
        // Map<String, Object> result = HttpClientUtil.doPost(this.httpClient,
        // this.askUrl, params, null, xauthHeader);
        Map<String, Object> result = HttpClientUtil.doPost(this.askUrl, param);

        // 对请求得到的结果进行处理
        if (result != null)
            askResponse = new AskResponse(Integer.parseInt(result.get("status").toString()),
                    (String) result.get("responseBody"));
        else {
            askResponse = new AskResponse(404, "");
        }
        return askResponse;
    }

    /**
     * 销毁实例化的智能问答
     */
    public void destroy() {
    }

}
