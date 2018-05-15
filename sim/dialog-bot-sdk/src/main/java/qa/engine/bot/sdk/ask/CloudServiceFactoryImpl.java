package qa.engine.bot.sdk.ask;

import qa.engine.bot.sdk.ask.impl.AskServiceImpl;

public class CloudServiceFactoryImpl extends CloudServiceFactory {

    // 创建智能问答
    public AskService createAskService() {
        return new AskServiceImpl();
    }
}
