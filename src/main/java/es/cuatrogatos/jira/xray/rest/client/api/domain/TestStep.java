package es.cuatrogatos.jira.xray.rest.client.api.domain;

import com.atlassian.jira.rest.client.api.domain.BasicIssue;
import es.cuatrogatos.jira.xray.rest.client.core.internal.json.util.RendereableItem;

import java.net.URI;

/**
 * Created by lucho on 11/08/16.
 */
public class TestStep extends BasicIssue{
    private Long id;
    private Integer index;
    private RendereableItem step;
    private RendereableItem data;
    private RendereableItem result;
    private Iterable<Evidence> attachments;

    public TestStep(URI self,String key,Long id){
        super(self,key,id);
    }

    public TestStep(URI self,String key,Long id,Integer index,RendereableItem step,RendereableItem data,RendereableItem result,Iterable<Evidence> attachments)
    {
        super(self,key,id);
        this.index=index;
        this.step=step;
        this.data=data;
        this.result=result;
        this.attachments=attachments;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        this.attachments = attachments;
    }

}
