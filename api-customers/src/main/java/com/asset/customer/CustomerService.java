package com.asset.customer;


import com.asset.amqp.RabbitMQMessageProducer;
import com.asset.clients.fraud.FraudCheckResponse;
import com.asset.clients.fraud.FraudClient;
import com.asset.clients.notifications.NotificationClient;
import com.asset.clients.notifications.NotificationRequest;
import com.asset.kafka.MessageRequest;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;

    private final FraudClient fraudClient;
    private final NotificationClient notificationClient;

    private final RabbitMQMessageProducer producer;
    private KafkaTemplate<String, MessageRequest> kafkaTemplate;


    public String registerCustomer(CustomerRegistrationRequest request){
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();



        // Todo: check email valid
        // Todo: check email not taken
        // Todo: check if fraudster
        // store Customer
        customerRepository.saveAndFlush(customer);

        /*  FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
                "http://FRAUD/api/v1/fraud-check/{customerId}",
                FraudCheckResponse.class,
                customer.getId()
        );*/

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String tokenValue = "";

        if (authentication instanceof JwtAuthenticationToken) {
            JwtAuthenticationToken jwt = (JwtAuthenticationToken) authentication;
            tokenValue = jwt.getToken().getTokenValue();
        }


        String authorizationHeader = "Bearer " + tokenValue;

        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getEmail(), authorizationHeader);

        if(fraudCheckResponse.isFraudster()){
            //publish message to Kafka
            MessageRequest messageRequest = new MessageRequest(
                    customer.getId(),
                    customer.getEmail(),
                    String.format("The customer with email %s might be a fraudster, need to verify ", customer.getEmail() ),
                    LocalDateTime.now());
            kafkaTemplate.send("samservice", messageRequest);

            return "the customer might be a fraudster, need to verify";
            //throw new IllegalStateException("fraudster");
        }else {

            NotificationRequest notificationRequest = new NotificationRequest(customer.getId(),
                    customer.getEmail(),
                    String.format("Hi %s, welcome to Sam's service", customer.getFirstName()));

            // publish message to RabbitMQ
            producer.publish(
                    notificationRequest,
                    "internal.exchange",
                    "internal.notification.routing-key"
            );


            return "the customer is registered";
        }




        // Todo: send notification - make it async, add it to queue

//        notificationClient.invokeNotification(new NotificationRequest(customer.getId(),
//                customer.getEmail(),
//                String.format("Hi %s, welcome to Sam's service", customer.getFirstName())
//                ));



//        producer.publish(
//                new NotificationRequest(customer.getId(),
//                                        customer.getEmail(),
//                                         String.format("Hi %s, welcome to Sam's service", customer.getFirstName())),
//                                notificationConfig.getInternalExchange(),
//                                notificationConfig.getNotificationQueue()
//                        );

    }

    public List<Customer> getCustomers(Customer customer){
        return customerRepository.findCustomersByLastName(customer.getLastName());
    }
}
