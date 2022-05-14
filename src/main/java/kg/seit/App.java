package kg.seit;

import kg.seit.services.CustomerService;
import kg.seit.services.OrderService;
import kg.seit.services.SupplierService;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) throws Exception {

        CustomerService customerService = new CustomerService();
        OrderService orderService = new OrderService();
        SupplierService supplierService = new SupplierService();


//        customerService.save(new Customer("Seitbek",996500909909L));
//        customerService.save(new Customer("Azat",996709453298L));
//        customerService.save(new Customer("Ulan",996507343345L));
//        customerService.save(new Customer("Kuba",996705623244L));

//        customerService.makeAnOrder(1L,new Order(LocalDateTime.now(),BigDecimal.valueOf(2500),OrderStatus.DELIVERED));

//        customerService.cancelOrder(1L,1L);


//        customerService.update(1L,new Customer("Azamat",996709003300L));
//        customerService.findAll().forEach(System.out::println);
//        customerService.findAllOrders(1L, OrderStatus.CANCELED).forEach(System.out::println);


//        supplierService.save(new Supplier("Nurlan",996500443322L,SupplierStatus.FREE));
//        supplierService.save(new Supplier("Beksultan",9967008788889L,SupplierStatus.BUSY));
//        supplierService.save(new Supplier("Zamir",996500112233L,SupplierStatus.FREE));

//        Address address3 = new Address("Kyrgyzstan","Naryn","Kochkor","Alybaev 11");
//        Address address4 = new Address("Kyrgyzstan","Bishkek","Chuy","Bokonbaev 122");
//        Address address5 = new Address("Kyrgyzstan", "Osh", "Kadamjai", "Aitmatov 55");
//        Supplier supplier = new Supplier("Kanat",996222887766L,SupplierStatus.FREE);
//        Customer customer = new Customer("Bekjan",996555990066L);
//
//        Order order1 = new Order(customer,address4,address5,LocalDateTime.now()
//                ,BigDecimal.valueOf(5000),supplier,OrderStatus.DELIVERED);
//
//        orderService.save(order1);
//        customerService.save(customer);
//        supplierService.save(supplier);

//        supplierService.getOrder(2L,12L);

//        orderService.deleteById(13L);
//        supplierService.deleteById(15L);
//        customerService.deleteById(18L);





        customerService.close();
        orderService.close();
        supplierService.close();


    }
}
