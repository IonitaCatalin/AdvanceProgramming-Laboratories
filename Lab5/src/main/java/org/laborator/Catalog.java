package org.laborator;

import java.io.Serializable;
import java.util.*;


public class Catalog implements Serializable {
    private String name;
    private String path;
    private List<Document> documents =new ArrayList<>();
    static final long serialVersionUID = 43L;

    public Catalog(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public void add(Document doc)
    {
        documents.add(doc);
    }
    public Document findById (String id)
    {
        return documents.stream().filter(d -> d.getId().equals(id)).findFirst().orElse(null);

    }
    public Document findByName(String name)
    {
        return documents.stream().filter(d->d.getName().equals(name)).findFirst().orElse(null);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Catalog catalog = (Catalog) o;
        return Objects.equals(name, catalog.name) &&
                Objects.equals(path, catalog.path) &&
                Objects.equals(documents, catalog.documents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, path, documents);
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", documents=" + documents +
                '}';
    }
}
