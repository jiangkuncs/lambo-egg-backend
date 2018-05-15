package qa.engine.bot.sdk.ask.util;

import net.sf.json.JSONObject;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class HttpClientUtil {

    /**
     * 智能问答机器人的访问post请求
     * 
     * @param url
     *            智能问答机器人的访问路径
     * @param paramsMap
     *            key为[token] [appKey]
     * @return Map [status] 调用返回的状态码 [responseBody] 后台返回的json数据
     */
    public static Map<String, Object> doPost(String url, Map<String, String> paramsMap) {
        CloseableHttpClient client = HttpClients.createDefault();
        String responseText = "";
        CloseableHttpResponse response = null;
        try {

            HttpPost httpPost = new HttpPost(url);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");

            String appKey = paramsMap.get("appKey");
            String token = paramsMap.get("token");

            // 设置发送请求的参数
            JSONObject jsonParam = new JSONObject();

            // 将userId设置为sessionId
            jsonParam.put("appKey", appKey);
            jsonParam.put("token", token);

            StringEntity entity = new StringEntity(jsonParam.toString(), Constant.ENCODING);
            entity.setContentEncoding(Constant.ENCODING);
            entity.setContentType("application/json");
            httpPost.setEntity(entity);
            response = client.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == 200) {
                StringWriter writer = new StringWriter();
                IOUtils.copy(response.getEntity().getContent(), writer, Constant.ENCODING);
                responseText = writer.toString();
            }

            // 获取post请求的返回数据
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("status", response.getStatusLine().getStatusCode());
            resultMap.put("responseBody", responseText);
            return resultMap;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;

    }

}
