package es.cuatrogatos.jira.xray.rest.client.api.domain;

import com.atlassian.jira.rest.client.api.domain.BasicIssue;
import com.atlassian.jira.rest.client.api.domain.Status;
import org.joda.time.DateTime;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by lucho on 11/08/16.
 */
public class TestRun extends BasicIssue {
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

    public void spubetExecutedBy(String executedBy) {
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


    public enum Status{TODO,EXECUTING,ABORTED,FAIL,PASS};








}
