package es.cuatrogatos.jira.xray.rest.client.api.domain;

import java.net.URI;
import java.util.Date;

/**
 * Created by lucho on 16/08/16.
 */
public class Evidence {
    private Long id;
    private String fileName;
    private String fileSize;
    private Date created;
    private String author;
    private URI fileURL;

    public Evidence(Long id,String fileName,String fileSize,Date created,String author,URI fileURL){
        this.id=id;
        this.fileName=fileName;
        this.fileSize=fileSize;
        this.created=created;
        this.author=author;
        this.fileURL=fileURL;
    }
};