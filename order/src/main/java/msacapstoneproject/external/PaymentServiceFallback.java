package msacapstoneproject.external;

import org.springframework.stereotype.Service;

@Service
public class PaymentServiceFallback implements PaymentService{

    public void paymentRequest(Payment payment) {
        // TODO Auto-generated method stub
        System.out.println("########## 결제가 지연중입니다. ##############");
        
    }
    
}
