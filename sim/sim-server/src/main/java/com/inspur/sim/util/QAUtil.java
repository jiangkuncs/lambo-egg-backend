package com.inspur.sim.util;

import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import qa.engine.bot.sdk.ask.*;
import qa.engine.bot.sdk.ask.util.Constant;
import qa.engine.bot.sdk.ask.util.UserUtil;

public class QAUtil {

    private static Logger logger = LoggerFactory.getLogger(QAUtil.class);

    public static JSONObject ask(String question) throws CloudNotInitializedException {
        // 应用id
        String appKey = "nvtkQJJPniw=";
        // 应用秘钥
        String appSecret = "SnDLPsLxMko=";
        // 输入会话Id
        String sessionId = UserUtil.getRndSessionId();

        // 初始化智能问答接口
        AskRequest askRequest = new AskRequest(appKey, appSecret, question, Constant.PRIMARY_TYPE, sessionId,
                Constant.CUSTOM_PLATFORM);

        // 创建问答服务
        AskService askService = CloudServiceFactory.getInstance().createAskService();

        // init中设置CloudConfiguration参数,
        CloudConfiguration cloudConfig = new CloudConfiguration();
        // 设置请求的url地址
        cloudConfig.setUrl("http://10.110.13.171:8080/dialog-bot/service/qa/bot/sdk/talk");
//       cloudConfig.setUrl("http://10.9.11.107:8080/dialog-bot/service/qa/bot/sdk/talk");

        // 设置服务调用的返回结果，true时，返回访问的参数，false为服务请求的结果，默认为false。
        cloudConfig.setInvokeServParam(true);
        askService.init(cloudConfig);

        // 询问返回的结果
        AskResponse askResponse = askService.ask(askRequest);
        // 返回所有结果的json
        System.out.println(askResponse.getContent());
        // 返回指向的状态码
        System.out.println(askResponse.getStatus());
        // 返回answer
        String answer = askResponse.getAnswer();
        JSONObject jsonobj = null;
        try {
            answer = answer.replace("{", "{\"");
            answer = answer.replace(":", "\":\"");
            answer = answer.replace(", ", "\",\"");
            answer = answer.replace("}", "\"}");
            answer = answer.replace("=", "\"=\"");
            System.out.println(answer);
            answer = answer.replace("\"[", "[");
            answer = answer.replace("]\"", "]");
            System.out.println(answer);
            jsonobj=JSONObject.fromObject(answer);
            System.out.println(jsonobj);
        } catch (Exception e) {
            logger.debug("json转换失败,大概是URL的锅.");
            jsonobj=JSONObject.fromObject(askResponse.getContent());
        }
        return jsonobj;
    }
}
