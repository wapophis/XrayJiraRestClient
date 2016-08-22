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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public URI getFileURL() {
        return fileURL;
    }

    public void setFileURL(URI fileURL) {
        this.fileURL = fileURL;
    }
};