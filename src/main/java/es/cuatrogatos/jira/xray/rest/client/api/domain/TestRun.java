package es.cuatrogatos.jira.xray.rest.client.api.domain;

import com.atlassian.jira.rest.client.api.domain.BasicIssue;
import com.atlassian.jira.rest.client.api.domain.Status;
import com.google.common.collect.Iterables;
import org.joda.time.DateTime;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

/**
 * Created by lucho on 11/08/16.
 */
public class TestRun extends BasicIssue implements Versionable<TestRun> {
    private TestRun oldVersion;
    private int version=0;

    private Status status;
    private String executedBy;
    private String assignee;
    private Date startedOn;
    private Date finishedOn;
    private Iterable<Defect> defects;
    private Iterable<Evidence> evidences;
    private Comment comment;

    private Iterable<TestStep> steps;
    private Iterable<Example> examples;

    private String scenario;
    private String scenarioOutline;






    public TestRun(URI self,String key,Long id){
        super(self,key,id);
    }

    public TestRun(URI self, String key, Long id,Status status,Date startedOn,Date finishedOn,String assignee,String executedBy,Iterable<Defect> defects,Iterable<Evidence> evidences,Comment comment,Iterable<Example>examples,Iterable<TestStep>steps) {
        super(self, key, id);
        this.status=status;
        this.assignee=assignee;
        this.executedBy=executedBy;
        this.startedOn=startedOn;
        this.finishedOn=finishedOn;
        this.defects=defects;
        this.evidences=evidences;
        this.comment=comment;
        this.steps=steps;
        this.examples=examples;
        try {
            this.oldVersion= (TestRun) this.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    // TODO: ADD CLONE TO ARRAYS USED IN THE TEST RUN
    public TestRun clone() throws CloneNotSupportedException {
        TestRun myTestRun= null;
            myTestRun = new TestRun(super.getSelf(),super.getKey(),super.getId());
            if(this.executedBy!=null)
                myTestRun.setExecutedBy(new String(this.executedBy));
            if(this.assignee!=null)
                myTestRun.setAssignee(new String(this.assignee));
            if(this.startedOn!=null)
                myTestRun.setStartedOn((Date) this.startedOn.clone());
            if(this.finishedOn!=null)
                myTestRun.setFinishedOn((Date) this.finishedOn.clone());
            if(this.defects!=null){
                ArrayList<Defect> cloneDefects=new ArrayList<Defect>();
                for(Defect def:this.defects){
                    cloneDefects.add(def.clone());
                }
                myTestRun.setDefects(cloneDefects);
            }
    //    myTestRun.resetVersion();
        return myTestRun;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Iterable<Defect> getDefects() {
        return defects;
    }

    public void setDefects(Iterable<Defect> defects) {
        try {
            this.setOldVersion(this.clone());
        } catch (CloneNotSupportedException e) {
            throw new IllegalArgumentException("CAN'T CLONE MYSELF SO VERSIONABLE OBJECT IS LOST");
        }
        this.defects = defects;
    }

    public Iterable<Evidence> getEvidences() {
        return evidences;
    }

    public void setEvidences(ArrayList<Evidence> evidences) {
        this.evidences = evidences;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public String getExecutedBy() {
        return executedBy;
    }

    public void setExecutedBy(String executedBy) {
        this.executedBy = executedBy;
    }

    public Date getStartedOn() {
        return startedOn;
    }

    public void setStartedOn(Date startedOn) {
        this.startedOn = startedOn;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public Date getFinishedOn() {
        return finishedOn;
    }

    public void setFinishedOn(Date finishedOn){this.finishedOn=finishedOn;}

    public Iterable<TestStep> getSteps() {
        return steps;
    }

    public void setSteps(Iterable<TestStep> steps) {
        this.steps = steps;
    }

    public Iterable<Example> getExamples() {
        return examples;
    }

    public void setExamples(Iterable<Example> examples) {
        this.examples = examples;
    }

    public TestRun getOldVersion() {
        return this.oldVersion;
    }

    public void setOldVersion(TestRun oldVersion) {
        if(this.oldVersion==null){
            this.oldVersion=oldVersion;
        }
        this.version=1;
    }

    public int getVersion() {
        return version;
    }

    protected void resetVersion(){
        this.version=0;
        this.oldVersion=null;
    }


    public enum Status{TODO,EXECUTING,ABORTED,FAIL,PASS};


}
