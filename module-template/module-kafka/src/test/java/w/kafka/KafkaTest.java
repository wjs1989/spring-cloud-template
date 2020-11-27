package w.kafka;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;

import java.util.*;
import java.util.concurrent.ExecutionException;

public class KafkaTest {

    private static final String NEW_TOPIC = "topictest1";
    private static final String brokerUrl = "118.24.22.139:9092";

    private static AdminClient adminClient;

    public static void createTopics() {
        NewTopic newTopic = new NewTopic(NEW_TOPIC, 4, (short) 1);
        Collection<NewTopic> newTopicList = new ArrayList<>();
        newTopicList.add(newTopic);
        adminClient.createTopics(newTopicList);
    }


    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", brokerUrl);
        adminClient = AdminClient.create(properties);

        listTopics();

        adminClient.deleteTopics(Arrays.asList("topictestAck"));

        adminClient.close();
    }


    public static void listTopics() {
        ListTopicsResult listTopicsResult = adminClient.listTopics();
        Set<String> strings = null;
        try {
            strings = listTopicsResult.names().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        strings.forEach(s -> {
            System.out.println(s);
        });

    }


}
