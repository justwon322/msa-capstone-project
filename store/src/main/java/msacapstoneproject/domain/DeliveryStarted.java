package msacapstoneproject.domain;

import msacapstoneproject.domain.*;
import msacapstoneproject.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class DeliveryStarted extends AbstractEvent {

    private Long id;
    private Long orderId;
    private String customerId;
    private String customerAddr;
    private String productName;
    private String productPrice;
    private String orderStatus;

    public DeliveryStarted(Store aggregate){
        super(aggregate);
    }
    public DeliveryStarted(){
        super();
    }
}
