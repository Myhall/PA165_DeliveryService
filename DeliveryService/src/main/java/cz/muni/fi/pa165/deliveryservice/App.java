package cz.muni.fi.pa165.deliveryservice;


public class App {

     public static void main(String[] args) throws Exception {
         
///        ApplicationContext context = 
//    	  new ClassPathXmlApplicationContext(new String[] {"classpath:applicationContext.xml"});
//        
//        CourierService courierService = (CourierService) context.getBean("courierService");
//        courierService.createCourier(new CourierDTO("first", "last", "email"));
//        CourierDTO c = courierService.createCourier(new CourierDTO("firstName", "lastName", "email@email.com"));
//        
//        
//        System.out.println("******************");
//        System.out.println(courierService.findCourier(1L).getFirstName());
//        System.out.println(courierService.getAllCouriers());
//        
//        courierService.createCourier(c);
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DeliveryServicePu");
//        EntityManager em = emf.createEntityManager();
//         DeliveryService ds = new DeliveryServiceImpl();
//         DeliveryDAO dao = new JPADeliveryDAO(emf.createEntityManager());
//         DeliveryItemDTO di = new DeliveryItemDTO();di.setName("aaa");
//         List<DeliveryItemDTO> l = new ArrayList<>(); l.add(di);
//         CustomerDTO cc = new CustomerDTO(); cc.setEmail("fak@fak.fak");
//         DeliveryDTO d = new DeliveryDTO(); d.setCustomer(cc);
//         d.setItems(l);
//         DeliveryDAO dao = new JPADeliveryDAO(em);
//         DeliveryItem di = new DeliveryItem();di.setName("aaa");
//         List<DeliveryItem> l = new ArrayList<>(); l.add(di);
//         Customer cc = new Customer(); cc.setEmail("fak@fak.fak");
//         Delivery d = new Delivery(); //d.setCustomer(cc);
//         d.setItems(l);
//         em.getTransaction().begin();
//         
//         dao.createDelivery(d);
//         em.getTransaction().commit();
    }
}
