package qa.engine.bot.sdk.ask;

public abstract class CloudServiceFactory {

    private static CloudServiceFactory INSTANCE = null;

    // 创建智能问答
    public abstract AskService createAskService();

    /**
     * 对cloud进行实例化
     *
     * @return
     */
    public static CloudServiceFactory getInstance() {

        // 如果CloudServiceFactory已经实例化，返回实例化的内容
        if (INSTANCE != null)
            return INSTANCE;

        CloudServiceFactory factory = null;

        // 创建智能问答的实例
        String clazz = System.getProperty(CloudServiceFactory.class.getName(),
                CloudServiceFactory.class.getName() + "Impl");
        try {
            factory = (CloudServiceFactory) Class.forName(clazz).newInstance();
            if (factory != null)
                INSTANCE = factory;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("CloudServiceFactory class not found.", e);
        }

        return factory;
    }
}
