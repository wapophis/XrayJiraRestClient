package es.cuatrogatos.jira.xray.rest.client.api.domain;

import com.atlassian.jira.rest.client.api.domain.BasicIssue;
import com.google.common.collect.Iterables;
import es.cuatrogatos.jira.xray.rest.client.core.internal.json.util.RendereableItem;
import es.cuatrogatos.jira.xray.rest.client.core.internal.json.util.RendereableItemImpl;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Created by lucho on 11/08/16.
 */
public class TestStep extends BasicIssue implements Versionable<TestStep> {
    private int version = 0;
    private TestStep oldVersion = null;

    private Long id;
    private Integer index;
    private RendereableItem step;
    private RendereableItem data;
    private RendereableItem result;
    private Iterable<Evidence> attachments;
    private Iterable<Evidence> evidences;
    private Iterable<Defect> defects;
    private Comment comment;
    private Status status;


    public TestStep(URI self, String key, Long id) {
        super(self, key, id);
    }

    public TestStep(URI self, String key, Long id, Integer index, RendereableItem step, RendereableItem data, RendereableItem result, Iterable<Evidence> attachments, Status status, Comment comment, Iterable<Defect> defects, Iterable<Evidence> evidences) {
        super(self, key, id);
        this.index = index;
        this.step = step;
        this.data = data;
        this.result = result;
        this.attachments = attachments;
        this.status = status;
        this.comment = comment;
        this.defects = defects;
        this.evidences = evidences;
    }


    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public RendereableItem getStep() {
        return step;
    }

    public void setStep(RendereableItem step) {
        this.step = step;
    }

    public RendereableItem getData() {
        return data;
    }

    public void setData(RendereableItem data) {
        this.data = data;
    }

    public RendereableItem getResult() {
        return result;
    }

    public void setResult(RendereableItem result) {
        this.result = result;
    }

    public Iterable<Evidence> getAttachments() {
        return attachments;
    }

    public void setAttachments(Iterable<Evidence> attachments) {
        this.setOldVersion(this);
        this.attachments = attachments;
    }

    public TestStep getOldVersion() {
        return oldVersion;
    }

    public void setOldVersion(TestStep oldVersion) {
        if (this.oldVersion == null)
            this.oldVersion = oldVersion;
        this.version = 1;
    }

    public int getVersion() {
        return version;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public Iterable<Evidence> getEvidences() {
        return evidences;
    }

    public void setEvidences(Iterable<Evidence> evidences) {
        this.evidences = evidences;
    }

    public Iterable<Defect> getDefects() {
        return defects;
    }

    public void setDefects(Iterable<Defect> defects) {
        try {
            this.setOldVersion(this.clone());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        this.defects = defects;
    }

    public enum Status {TODO, EXECUTING, ABORTED, FAIL, PASS}

    ;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        try {
            this.setOldVersion(this.clone());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        this.status = status;
    }

    public TestStep clone() throws CloneNotSupportedException {
        TestStep myTestStep = new TestStep(super.getSelf(), super.getKey(), super.getId());
        if (this.step != null)
            myTestStep.setStep(new RendereableItemImpl(this.step.getRaw(), this.step.getRendered()));
        if (this.data != null)
            myTestStep.setData(new RendereableItemImpl(this.data.getRaw(), this.data.getRendered()));
        if (this.result != null)
            myTestStep.setResult(new RendereableItemImpl(this.result.getRaw(), this.result.getRendered()));
        if (this.comment != null)
            myTestStep.setComment(new Comment(this.comment.getRaw(), this.comment.getRendered()));
        if (this.status != null)
            myTestStep.setStatus(this.status);

        if (this.attachments != null) {
            Collection<Evidence> attachments = new ArrayList<Evidence>();
            for (Evidence e : this.getAttachments()) {
                attachments.add(new Evidence(new Long(e.getId().longValue()), e.getFileName(), e.getFileSize(), (Date) e.getCreated().clone(), e.getAuthor(), e.getFileURL()));
            }
            myTestStep.setAttachments(attachments);
        }
        if (this.evidences != null) {
            Collection<Evidence> evidences = new ArrayList<Evidence>();
            for (Evidence e : this.getEvidences()) {
                evidences.add(new Evidence(new Long(e.getId().longValue()), e.getFileName(), e.getFileSize(), (Date) e.getCreated().clone(), e.getAuthor(), e.getFileURL()));
            }
            myTestStep.setEvidences(evidences);
        }
        if (this.defects != null) {
            Collection<Defect> defects = new ArrayList<Defect>();
            for (Defect d : this.getDefects()) {
                defects.add(new Defect(d.getSelf(), d.getKey(), d.getId(), d.getSummary(), d.getStatus()));
            }
            myTestStep.setDefects(defects);
        }
        return myTestStep;
    }
}


