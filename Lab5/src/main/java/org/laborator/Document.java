package org.laborator;

import java.io.File;
import java.io.Serializable;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class Document implements Serializable {
    static final long serialVersionUID = 42L;
    private String name;
    private String id;
    private String location;

    public Document(String name,String location) {
        this.name = name;
        this.id = UUID.randomUUID().toString();
        this.location = location;
    }
    private Map<String,Object> tags=new HashMap<>();

    public void addTag(String key,Object tagObj)
    {
        tags.put(key,tagObj);
    }

    public String getName() {
        return name;
    }
    public boolean existsLocalFile() {
        return new File(this.location).exists();
    }
    public boolean isLocationValidURL()
    {
        try
        {
            URL url = new URL(location);
            url.toURI();
            return true;
        }
        catch (Exception exception)
        {
            return false;
        }
    }

    public Map<String, Object> getTags() {
        return tags;
    }

    public void setTags(Map<String, Object> tags) {
        this.tags = tags;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Document document = (Document) o;
        return Objects.equals(name, document.name) &&
                Objects.equals(id, document.id) &&
                Objects.equals(location, document.location) &&
                Objects.equals(tags, document.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, location, tags);
    }

    @Override
    public String toString() {
        return "Document{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", location='" + location + '\'' +
                ", tags=" + tags +
                '}';
    }
}
