package qa.engine.bot.sdk.ask;

public interface CloudService {

    /**
     * 初始化参数方法.
     *
     * @param paramCloudConfiguration
     *            发送请求参数配置对象
     */
    void init(CloudConfiguration paramCloudConfiguration);

    /**
     * 销毁实例化方法.
     */
    void destroy();
}
