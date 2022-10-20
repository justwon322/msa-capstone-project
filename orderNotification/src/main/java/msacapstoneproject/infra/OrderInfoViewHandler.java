package msacapstoneproject.infra;

import msacapstoneproject.domain.*;
import msacapstoneproject.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class OrderInfoViewHandler {


    @Autowired
    private OrderInfoRepository orderInfoRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenOrdered_then_CREATE_1 (@Payload Ordered ordered) {
        try {

            if (!ordered.validate()) return;

            // view 객체 생성
            OrderInfo orderInfo = new OrderInfo();
            // view 객체에 이벤트의 Value 를 set 함
            orderInfo.setId(ordered.getId());
            orderInfo.setOrderStatus("Ordered");
            // view 레파지 토리에 save
            orderInfoRepository.save(orderInfo);

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whenOrderCanceled_then_UPDATE_1(@Payload OrderCanceled orderCanceled) {
        try {
            if (!orderCanceled.validate()) return;
                // view 객체 조회
            Optional<OrderInfo> orderInfoOptional = orderInfoRepository.findById(orderCanceled.getId());

            if( orderInfoOptional.isPresent()) {
                 OrderInfo orderInfo = orderInfoOptional.get();
            // view 객체에 이벤트의 eventDirectValue 를 set 함
                orderInfo.setOrderStatus("OrderCanceled");    
                // view 레파지 토리에 save
                 orderInfoRepository.save(orderInfo);
                }


        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenPaymentCanceled_then_UPDATE_2(@Payload PaymentCanceled paymentCanceled) {
        try {
            if (!paymentCanceled.validate()) return;
                // view 객체 조회
            Optional<OrderInfo> orderInfoOptional = orderInfoRepository.findById(Long.valueOf(paymentCanceled.getOrderId()));

            if( orderInfoOptional.isPresent()) {
                 OrderInfo orderInfo = orderInfoOptional.get();
            // view 객체에 이벤트의 eventDirectValue 를 set 함
                orderInfo.setOrderStatus("PaymentCanceled");    
                // view 레파지 토리에 save
                 orderInfoRepository.save(orderInfo);
                }


        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenDeliveryCanceled_then_UPDATE_3(@Payload DeliveryCanceled deliveryCanceled) {
        try {
            if (!deliveryCanceled.validate()) return;
                // view 객체 조회
            Optional<OrderInfo> orderInfoOptional = orderInfoRepository.findById(Long.valueOf(deliveryCanceled.getOrderId()));

            if( orderInfoOptional.isPresent()) {
                 OrderInfo orderInfo = orderInfoOptional.get();
            // view 객체에 이벤트의 eventDirectValue 를 set 함
                orderInfo.setOrderStatus("DeliveryCanceled");    
                // view 레파지 토리에 save
                 orderInfoRepository.save(orderInfo);
                }


        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenPaymentApproved_then_UPDATE_4(@Payload PaymentApproved paymentApproved) {
        try {
            if (!paymentApproved.validate()) return;
                // view 객체 조회
            Optional<OrderInfo> orderInfoOptional = orderInfoRepository.findById(Long.valueOf(paymentApproved.getOrderId()));

            if( orderInfoOptional.isPresent()) {
                 OrderInfo orderInfo = orderInfoOptional.get();
            // view 객체에 이벤트의 eventDirectValue 를 set 함
                orderInfo.setOrderStatus("PaymentApproved");    
                // view 레파지 토리에 save
                 orderInfoRepository.save(orderInfo);
                }


        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenDeliveryStarted_then_UPDATE_5(@Payload DeliveryStarted deliveryStarted) {
        try {
            if (!deliveryStarted.validate()) return;
                // view 객체 조회
            Optional<OrderInfo> orderInfoOptional = orderInfoRepository.findById(Long.valueOf(deliveryStarted.getOrderId()));

            if( orderInfoOptional.isPresent()) {
                 OrderInfo orderInfo = orderInfoOptional.get();
            // view 객체에 이벤트의 eventDirectValue 를 set 함
                orderInfo.setOrderStatus("DeliveryStarted");    
                // view 레파지 토리에 save
                 orderInfoRepository.save(orderInfo);
                }


        }catch (Exception e){
            e.printStackTrace();
        }
    }

}

