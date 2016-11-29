package cheetahmail_drools_spark;

import cheetahmail_drools_java.CampaignEvent;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.broadcast.Broadcast;
import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.core.util.Drools;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatelessKnowledgeSession;

/**
 * Created by depatel on 11/28/2016.
 */
public class DroolsOnSpark {



    public static void main(String[] args){

        // spark initialization.
        SparkConf sc = new SparkConf().setAppName("DroolsOnSpark").setMaster("local[*]");
        JavaSparkContext jsc = new JavaSparkContext(sc);
        Broadcast<DroolSession> drlSession = jsc.broadcast(new DroolSession("C:\\Users\\depatel\\IdeaProjects\\DroolsOnSpark\\src\\main\\resources\\campaignEvents.drl"));

        JavaRDD<String> inputRDD = jsc.textFile("C:\\Users\\depatel\\IdeaProjects\\DroolsOnSpark\\src\\main\\resources\\sampleEvents.txt");

        JavaRDD<CampaignEvent> eventsRDD = inputRDD.map(new Function<String, CampaignEvent>() {
            @Override
            public CampaignEvent call(String v1) throws Exception {
                return new CampaignEvent(v1);
            }
        });



        JavaRDD<CampaignEvent> drlRDD = eventsRDD.map(new Function<CampaignEvent, CampaignEvent>() {
            @Override
            public CampaignEvent call(CampaignEvent v1) throws Exception {
                DroolSession brSession = drlSession.value();
                brSession.ksession.execute(v1);
                System.out.println(v1.getEventType() + " : "+ v1.isValidEvent());
                return v1;
            }
        });

        System.out.println(drlRDD.count());




    }
}
