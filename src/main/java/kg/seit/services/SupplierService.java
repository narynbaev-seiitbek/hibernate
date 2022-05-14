package kg.seit.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import kg.seit.configuration.Config;
import kg.seit.enums.OrderStatus;
import kg.seit.enums.SupplierStatus;
import kg.seit.models.Order;
import kg.seit.models.Supplier;

import java.util.List;

import static kg.seit.enums.SupplierStatus.BUSY;

/**
 * @author seiitbeknarynbaev
 */
public class SupplierService implements AutoCloseable{

    EntityManagerFactory entityManagerFactory = Config.createEntityManagerFactory();
    // create objects of repositories

    public void save(Supplier newSupplier) {
        // write your code here
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(newSupplier);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void update(Long supplierId, Supplier newSupplier) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createQuery("update Supplier s set s.fullName=:fName,s.phoneNumber=:pNumber,s.status=:st where s.id=:id")
                .setParameter("fName",newSupplier.getFullName())
                .setParameter("pNumber",newSupplier.getPhoneNumber())
                .setParameter("id",supplierId).executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public List<Supplier> findAllSuppliers() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Supplier> suppliers = entityManager.createQuery("select s from Supplier s ", Supplier.class).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return suppliers;
    }

    public Supplier findById(Long supplierId) {
        // write your code here
        // you should return supplier with id = :supplierId
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Supplier supplier = entityManager.createQuery("select s from Supplier s where s.id=?1", Supplier.class)
                .setParameter(1, supplierId).getSingleResult();
        entityManager.getTransaction().commit();
        entityManager.close();
        return supplier;
    }

    public void getOrder(Long supplierId,Long orderId) {
        // give free order to supplier with id = :supplierId
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Supplier supplier = entityManager.createQuery("select s from Supplier s where s.id=?1", Supplier.class)
                .setParameter(1, supplierId).getSingleResult();
        Order order = entityManager.createQuery("select o from Order o where o.id=?1", Order.class)
                .setParameter(1, orderId).getSingleResult();
        if (supplier.getStatus().equals(SupplierStatus.FREE) && order.getStatus().equals(OrderStatus.REQUEST)) {
            supplier.addOrder(order);
            Supplier supplier2 = entityManager.createQuery("select s from Supplier s where s.id=?1", Supplier.class)
                    .setParameter(1, supplierId).getSingleResult();
            supplier2.setStatus(BUSY);
        }
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void deleteById(Long supplierId) {
        // write your code here
        // you should delete supplier with id = :supplierId
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createQuery("delete from Supplier s where s.id=?1").setParameter(1,supplierId).executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public List<Order> findAllOrders(Long supplierId) {
        // find all supplier's delivered orders
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Order> orders = entityManager.createQuery(
                        "select o from Order o join Supplier s on o.id=s.id where o.status= :st and s.id=:id", Order.class)
                .setParameter("st",OrderStatus.DELIVERED)
                .setParameter("id",supplierId).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return orders;
    }

    public List<Supplier> findAllBusySuppliers() {
        // find all busy suppliers
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Supplier> supp = entityManager.createQuery("select s from Supplier s where s.status ='BUSY'", Supplier.class)
                .getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return supp;
    }

    @Override
    public void close() throws Exception {
        entityManagerFactory.close();
    }
}
