package cheetahmail_drools_spark;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatelessKnowledgeSession;

import java.io.Serializable;

/**
 * Created by depatel on 11/28/2016.
 * It is a wrapper class with all the Drools Knowledge Initiation.
 * We can not broadcast the KnowledgeSession directly as it is un-Serializable.
 */
public class DroolSession implements Serializable {


    public static KnowledgeBuilder getKnowledgeBuilder(String filePath){

        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kbuilder.add(ResourceFactory.newFileResource(filePath), ResourceType.DRL);

        if(kbuilder.hasErrors()) {
            System.err.println(kbuilder.getErrors().toString());
            return null;
        }
        return kbuilder;
    }

    public transient StatelessKnowledgeSession ksession;

    public DroolSession( String FilePath) {

        KnowledgeBuilder kbuilder = getKnowledgeBuilder(FilePath);

        // step 2: Get KnowledgeBase out of KnowledgeBuilder packages.
        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        kbase.addKnowledgePackages( kbuilder.getKnowledgePackages() );

        // step 3: Get KnowledgeSession out of KnowledgeBase.
        this.ksession= kbase.newStatelessKnowledgeSession();

    }
}
