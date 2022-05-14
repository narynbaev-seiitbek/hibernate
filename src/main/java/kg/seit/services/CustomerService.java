package kg.seit.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import kg.seit.configuration.Config;
import kg.seit.enums.OrderStatus;
import kg.seit.models.Customer;
import kg.seit.models.Order;

import java.util.List;

/**
 * @author seiitbeknarynbaev
 */
public class CustomerService implements AutoCloseable{

    EntityManagerFactory entityManagerFactory = Config.createEntityManagerFactory();

    public void save(Customer newCustomer) {
        // save a newCustomer to database
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(newCustomer);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void update(Customer newCustomer) {
        // save a newCustomer to database
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(newCustomer);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void makeAnOrder(Long customerId, Order newOrder) {
        // find customer and give order to customer
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Customer customer = entityManager.createQuery("select c from Customer c where c.id=?1", Customer.class)
                .setParameter(1, customerId).getSingleResult();
        customer.addOrder(newOrder);
        newOrder.setCustomer(customer);
        entityManager.getTransaction().commit();
        entityManager.close();

    }

    public void cancelOrder(Long customerId, Long orderId) {
        // find customer with :customerId and find customer's order with id = :orderId
        // and setOrder status CANCELED
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createQuery("update Order o set o.status=:st where o.customer.id=:cId and o.id=:oId")
                .setParameter("st", OrderStatus.CANCELED)
                .setParameter("cId",customerId)
                .setParameter("oId",orderId).executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void update(Long customerId, Customer newCustomer) {
        // update customer with id = :customerId to newCustomer
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createQuery("update Customer c set c.fullName=:fName, c.phoneNumber=:pNumber where c.id=:id")
                .setParameter("fName",newCustomer.getFullName())
                .setParameter("pNumber",newCustomer.getPhoneNumber())
                .setParameter("id",customerId).executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public List<Order> findAllOrders(Long customerId, OrderStatus orderStatus) {
        // find all orders by :orderStatus where customer id = :customerId
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List orders = entityManager.createQuery(
                        "select o from Order o join Customer c on o.id=c.id where c.id=:id and o.status=:st", Order.class)
                .setParameter("id", customerId)
                .setParameter("st", orderStatus).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return orders;
    }

    public List<Customer> findAll() {
        // return all customers from database
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Customer> customers = entityManager.createQuery("select c from Customer c", Customer.class).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return customers;
    }

    public Customer findById(Long customerId) {
        // find customer with id = :customerId from database and return it
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Customer customer = entityManager.createQuery("select c from Customer c where c.id=?1", Customer.class)
                .setParameter(1, customerId).getSingleResult();
        entityManager.getTransaction().commit();
        entityManager.close();
        return customer;
    }

    public void deleteById(Long customerId) {
        // delete customer from database
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createQuery("delete from Customer c where c.id=?1").setParameter(1,customerId).executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }


    @Override
    public void close() throws Exception {
        entityManagerFactory.close();
    }
}
