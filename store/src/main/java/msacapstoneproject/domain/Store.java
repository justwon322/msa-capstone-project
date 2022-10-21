package msacapstoneproject.domain;

import msacapstoneproject.domain.DeliveryStarted;
import msacapstoneproject.domain.DeliveryCanceled;
import msacapstoneproject.StoreApplication;
import javax.persistence.*;

import org.springframework.beans.BeanUtils;

import java.util.List;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name="Store_table")
@Data

public class Store  {

    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    
    
    
    
    
    private Long id;
    
    
    
    
    
    private Long orderId;
    
    
    
    
    
    private String customerId;
    
    
    
    
    
    private String customerAddr;
    
    
    
    
    
    private String productName;
    
    
    
    
    
    private String productPrice;
    
    
    
    
    
    private String orderStatus;

    @PostPersist
    public void onPostPersist(){


        DeliveryStarted deliveryStarted = new DeliveryStarted(this);
        deliveryStarted.publishAfterCommit();



        // DeliveryCanceled deliveryCanceled = new DeliveryCanceled(this);
        // deliveryCanceled.publishAfterCommit();

    }

    public static StoreRepository repository(){
        StoreRepository storeRepository = StoreApplication.applicationContext.getBean(StoreRepository.class);
        return storeRepository;
    }




    public static void cancelDelivery(PaymentCanceled paymentCanceled){
        repository().findByOrderId(paymentCanceled.getOrderId()).ifPresent(store->{
            repository().delete(store);
         });

        /** Example 1:  new item 
        Store store = new Store();
        repository().save(store);

        */

        /** Example 2:  finding and process
        
        repository().findById(paymentCanceled.get???()).ifPresent(store->{
            
            store // do something
            repository().save(store);


         });
        */

        
    }
    public static void updateOrderList(PaymentApproved paymentApproved){

        repository().findById(paymentApproved.getOrderId()).ifPresent(store->{
            store.setOrderStatus("결제승인됨");
            repository().save(store);
        });
        
    }
    public static void updateOrderList(Ordered ordered){

        Store store = new Store();
        BeanUtils.copyProperties(ordered, store);
        store.setOrderId(ordered.getId());
        repository().save(store);
        
        
    }


}
