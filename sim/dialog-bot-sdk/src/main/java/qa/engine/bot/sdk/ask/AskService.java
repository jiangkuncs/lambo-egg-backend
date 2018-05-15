package qa.engine.bot.sdk.ask;

public interface AskService extends CloudService {

    /**
     * 智能问答发送请求接口方法.
     *
     * @param request
     *            发送请求参数对象
     * @return
     * @throws CloudNotInitializedException
     */
    AskResponse ask(AskRequest request) throws CloudNotInitializedException;

}
