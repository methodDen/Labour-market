package com.github.daniyar.trademarket.POJO;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Company")
public class Company {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "companyId")
    private int id;

    @Column(name = "companyName")
    private String companyName;

        public Company() {

    }

    public Company(int id, String companyName, List<Employer> employers) {
        this.id = id;
        this.companyName = companyName;
        this.employers = employers;
    }

    //    @JsonManagedReference
    @OneToMany(mappedBy = "company")
    private List<Employer> employers;




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<Employer> getEmployers() {
        return employers;
    }

    public void setEmployers(List<Employer> employers) {
        this.employers = employers;
    }

    @Override
    public String toString() {
        return "POJO.Company{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                '}';
    }
}
