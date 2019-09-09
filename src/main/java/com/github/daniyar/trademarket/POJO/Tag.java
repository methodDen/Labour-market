package com.github.daniyar.trademarket.POJO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Tag")
public class Tag {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "tagId")
    private int id;

    @Column(name = "tagName")
    private String tagName;

//    @JsonManagedReference
    @JsonIgnore
    @ManyToMany(mappedBy = "tags")
    private List<Employer> employers;

    public Tag(int id, String tagName) {
        this.id = id;
        this.tagName = tagName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Tag() {
    }

    @Override
    public String toString() {
        return "POJO.Tag{" +
                "id=" + id +
                ", tagName='" + tagName + '\'' +
                '}';
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
