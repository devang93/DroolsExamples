package cheetahmail_drools_java;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.command.CommandFactory;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatelessKnowledgeSession;
import org.drools.runtime.rule.QueryResults;

;import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by depatel on 11/22/2016.
 */
public class DroolsOnJava {

    public static KnowledgeBuilder getKnowledgeBuilder(String filePath){

        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kbuilder.add(ResourceFactory.newFileResource(filePath), ResourceType.DRL);

        if(kbuilder.hasErrors()) {
            System.err.println(kbuilder.getErrors().toString());
            return null;
        }
        return kbuilder;
    }

    public static List getCampaignEventsList (String eventFilePath){

        List eventList = new ArrayList<CampaignEvent>();
        String currentLine;
        try{
            BufferedReader br = new BufferedReader(new FileReader(new File(eventFilePath)));
            while( (currentLine = br.readLine())!= null){
                CampaignEvent event = new CampaignEvent(currentLine);
                eventList.add(event);
            }
            return eventList;
        } catch (Exception e) {
            System.out.println("Exception in reading event from file ");
            e.printStackTrace();
            return eventList;
        }
    }

    public static void main(String[] args) {
//
        Long startTime = System.currentTimeMillis();

        // step 1: Get KnowledgeBuilder from drool rules files.
        KnowledgeBuilder kbuilder = getKnowledgeBuilder("C:\\Users\\depatel\\IdeaProjects\\DroolsOnSpark\\src\\main\\resources\\campaignEvents.drl");

        // step 2: Get KnowledgeBase out of KnowledgeBuilder packages.
        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        kbase.addKnowledgePackages( kbuilder.getKnowledgePackages() );

        // step 3: Get KnowledgeSession out of KnowledgeBase.
        StatelessKnowledgeSession ksession = kbase.newStatelessKnowledgeSession();

        System.out.println("Time taken to do the ksession initialization :");
        System.out.println(System.currentTimeMillis()-startTime);

        List events = getCampaignEventsList("C:\\Users\\depatel\\IdeaProjects\\DroolsOnSpark\\src\\main\\resources\\sampleEvents.txt");

        ksession.execute(events);

        Iterator iterator = events.iterator();
        while(iterator.hasNext()){
            CampaignEvent e = (CampaignEvent) iterator.next();
            System.out.println( e.getEventType() + "  "+ e.isValidEvent());
        }

        //QueryResults results = ksession.execute(CommandFactory.qu);

        // Need to do some searching on how to use the kiemodule nad kieservices.
        // it seems like that we need to load the kiemodoule from the xsd.
        // I am having an error for unable to load kiemodule from xsd and then unable to open zip file for the specified drl file path.


//        KieServices kieServices = KieServices.Factory.get();
//        KieModule kModule = kieServices.getRepository().addKieModule(kieServices.getResources().newFileSystemResource("C:\\Users\\depatel\\IdeaProjects\\DroolsOnSpark\\src\\main\\resources\\campaignEvents.drl"));
//
//        //kieServices.getResources().newFileSystemResource(new File("C:\\Users\\depatel\\IdeaProjects\\DroolsOnSpark\\src\\main\\resources\\campaignEvents.drl"));
//        KieContainer kieContainer = kieServices.newKieContainer(kModule.getReleaseId());
////        KieSession kSession = kieContainer.newKieSession("hellotest");
////        kSession.setGlobal("list",
////                new ArrayList<Object>());
//        StatelessKieSession kSession = kieContainer.newStatelessKieSession();
//        Applicant applicant1 = new Applicant();
//        applicant.setName("Devang");
//        applicant.setAge(20);
//        kSession.execute(applicant1);
//        //kSession.fireAllRules();
//        System.out.println(applicant1.getName());
    }

}
