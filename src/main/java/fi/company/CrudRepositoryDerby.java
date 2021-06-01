package fi.company;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RequestScoped
public class CrudRepositoryDerby implements CrudRepository {

    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public Customer addCustomer(Customer customer) {
        System.out.println("JPA ADD");
        em.persist(customer);
        return customer;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return em.createQuery("Select obj from Customer as obj").getResultList();
    }

    @Override
    public Optional<Customer> getCustomerWithId(int id) {
        return Optional.ofNullable(em.find(Customer.class, id));
    }

    @Override
    @Transactional
    public boolean deleteCustomerWithId(int id) {
        Customer temp = em.find(Customer.class, id);
        if(temp == null) {
            return false;
        } else {
            em.remove(temp);
            return true;
        }
    }

}