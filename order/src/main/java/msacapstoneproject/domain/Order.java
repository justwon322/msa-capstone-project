package msacapstoneproject.domain;

import msacapstoneproject.domain.Ordered;
import msacapstoneproject.domain.OrderCanceled;
import msacapstoneproject.OrderApplication;
import javax.persistence.*;
import java.util.List;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name="Order_table")
@Data

public class Order  {

    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    
    
    
    
    
    private Long id;
    
    
    
    
    
    private String customerId;
    
    
    
    
    
    private String customerAddr;
    
    
    
    
    
    private String productName;
    
    
    
    
    
    private String productPrice;
    
    
    
    
    
    private String orderStatus;

    @PostPersist
    public void onPostPersist(){

        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.


        Ordered ordered = new Ordered(this);
        ordered.publishAfterCommit();

        msacapstoneproject.external.Payment payment = new msacapstoneproject.external.Payment();
        payment.setOrderId(ordered.getId());
        payment.setOrderStatus(ordered.getOrderStatus());
        // mappings goes here
        OrderApplication.applicationContext.getBean(msacapstoneproject.external.PaymentService.class)
            .paymentRequest(payment);


        



        // OrderCanceled orderCanceled = new OrderCanceled(this);
        // orderCanceled.publishAfterCommit();

    }
    @PreRemove
    public void onPreRemove(){
        OrderCanceled orderCanceled = new OrderCanceled(this);
        orderCanceled.publishAfterCommit();
    }

    public static OrderRepository repository(){
        OrderRepository orderRepository = OrderApplication.applicationContext.getBean(OrderRepository.class);
        return orderRepository;
    }






}
