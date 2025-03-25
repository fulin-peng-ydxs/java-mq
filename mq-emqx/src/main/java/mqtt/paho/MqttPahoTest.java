package mqtt.paho;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.junit.Test;

public class MqttPahoTest {

    @Test
    public void receiveMessage() throws MqttException {
        // 定义链接相关参数
        String serverURI = "tcp://10.100.101.158:1883" ;
        String clientId = "paho_client_123" ;
        // 创建一个MqttClient对象
        // public MqttClient(String serverURI, String clientId, MqttClientPersistence persistence)
        MqttClient mqttClient = new MqttClient(serverURI , clientId , new MemoryPersistence()) ;
        // 创建MqttConnectOptions对象
        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions() ;
        mqttConnectOptions.setUserName("zhangsan");
        mqttConnectOptions.setPassword("123".toCharArray());
        mqttConnectOptions.setCleanSession(true);
        mqttClient.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {       // 链接丢失
                System.out.println("connectionLost....");
            }
            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {        // 接收消息
                System.out.println("topic ----> " + topic);
                byte[] payload = message.getPayload();
                System.out.println("msg ----> " + new String(payload));
            }
            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {        // 消息完全传送完毕以后
                System.out.println("deliveryComplete...");
            }
        });
        // 发送建立链接的请求
        mqttClient.connect(mqttConnectOptions);
        // 订阅主题
        mqttClient.subscribe("java/b" , 2);
        while (true) ;
    }

    @Test
    public void sendMsg() throws MqttException {

        // 定义链接相关参数
        String serverURI = "tcp://10.100.101.158:1883" ;
        String clientId = "paho_client_123" ;

        // 创建一个MqttClient对象
        // public MqttClient(String serverURI, String clientId, MqttClientPersistence persistence)
        MqttClient mqttClient = new MqttClient(serverURI , clientId , new MemoryPersistence()) ;

        // 创建MqttConnectOptions对象
        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions() ;
        mqttConnectOptions.setUserName("zhangsan");
        mqttConnectOptions.setPassword("123".toCharArray());
        mqttConnectOptions.setCleanSession(true);

        // 发送建立链接的请求
        mqttClient.connect(mqttConnectOptions);

        // 创建MqttMessage，封装消息的信息
        MqttMessage mqttMessage = new MqttMessage() ;
        mqttMessage.setQos(2);
        mqttMessage.setPayload("hello mqtt java client".getBytes());

        // 发送消息
        mqttClient.publish("java/b" , mqttMessage);

        // 关闭链接
        mqttClient.disconnect();
        mqttClient.close();

    }

    @Test
    public void createConnection() throws MqttException {

        // 定义链接相关参数
        String serverURI = "tcp://10.100.101.158:1883" ;
        String clientId = "paho_client_123" ;

        // 创建一个MqttClient对象
        // public MqttClient(String serverURI, String clientId, MqttClientPersistence persistence)
        MqttClient mqttClient = new MqttClient(serverURI , clientId , new MemoryPersistence()) ;

        // 创建MqttConnectOptions对象
        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions() ;
        mqttConnectOptions.setUserName("zhangsan");
        mqttConnectOptions.setPassword("123".toCharArray());
        mqttConnectOptions.setCleanSession(true);

        // 发送建立链接的请求
        mqttClient.connect(mqttConnectOptions);

        // 让当前的方法处于阻塞状态
        while(true) ;

    }


}
