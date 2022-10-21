package msacapstoneproject.domain;

import msacapstoneproject.domain.*;
import msacapstoneproject.infra.AbstractEvent;
import lombok.*;
import java.util.*;
@Data
@ToString
public class DeliveryCanceled extends AbstractEvent {

    private Long id;
    private Long orderId;
    private String customerId;
    private String customerAddr;
    private String productName;
    private String productPrice;
    private String orderStatus;
}


