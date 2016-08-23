package es.cuatrogatos.jira.xray.rest.client.api.domain;

import com.atlassian.jira.rest.client.api.domain.BasicIssue;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by lucho on 16/08/16.
 */

public class Defect extends BasicIssue {
    private String summary;
    private String myStatus;
    private com.atlassian.jira.rest.client.api.domain.Status status;

    public Defect(String key){
        super(null,key,null);
    }

    public Defect(URI self, String key, Long id, String summary, String myStatus) {
        super(self, key, id);
        this.summary=summary;
        this.myStatus=myStatus;
    }


    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getStatus() {
        return myStatus;
    }

    public void setStatus(String myStatus) {
        this.myStatus = myStatus;
    }

    public Defect clone(){
        return new Defect(super.getSelf(),super.getKey(),super.getId(),this.summary,this.myStatus);
    }
}
