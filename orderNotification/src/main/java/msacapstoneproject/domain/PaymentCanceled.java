package msacapstoneproject.domain;

import msacapstoneproject.domain.*;
import msacapstoneproject.infra.AbstractEvent;
import lombok.*;
import java.util.*;
@Data
@ToString
public class PaymentCanceled extends AbstractEvent {

    private Long id;
    private Long orderId;
    private String orderStatus;
}


