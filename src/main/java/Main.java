import POJO.Employee;
import POJO.Job;
import POJO.Person;
import Utils.HibernateUtils;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        HibernateUtils.startServer();
        PersonDao personDao = new PersonDao();
        Person person = new Person("Daniyar", "Absatov");
        Person a = new Person("A", "B");
        personDao.savePerson(a);
        personDao.savePerson(person);
        List<Person> personList = personDao.getPersons();
        personList.forEach(s-> System.out.println(s.getFirstName()));
        Employee e = new Employee();
        e.setFirstName("Daniyar");
        e.setLastName("Absatov");
        Job job = new Job();
        job.setJobName("Code developer");
        job.setProcessInfo("In Process");
        e.getJobs().add(job);
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(e);
        session.getTransaction().commit();
        session.close();
    }


}
