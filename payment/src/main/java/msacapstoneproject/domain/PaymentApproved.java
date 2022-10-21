package msacapstoneproject.domain;

import msacapstoneproject.domain.*;
import msacapstoneproject.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class PaymentApproved extends AbstractEvent {

    private Long id;
    private Long orderId;
    private String orderStatus;

    public PaymentApproved(Payment aggregate){
        super(aggregate);
    }
    public PaymentApproved(){
        super();
    }
}
