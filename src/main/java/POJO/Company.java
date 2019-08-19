package POJO;

import javax.persistence.*;

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

    @Override
    public String toString() {
        return "POJO.Company{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                '}';
    }
}
