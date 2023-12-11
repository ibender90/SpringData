package ru.geek.market.cart.integrations;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ImportResource;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.PollableChannel;
import org.springframework.stereotype.Component;
import ru.geek.market.api.DTO.ProductDto;

import java.util.Map;

@Component
@ImportResource("http-outbound-gateway.xml")
public class SpringIntegration {

    @Autowired
    @Qualifier("get_send_channel")
    MessageChannel getSendChannel;

    @Autowired
    @Qualifier("get_receive_channel")
    PollableChannel getReceiveChannel;

    public ProductDto findById(Long id) {
        Message<?> message = MessageBuilder.withPayload(id).build();
        getSendChannel.send(message);
        var responce = (String) getReceiveChannel.receive().getPayload();
        ProductDto dto = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            dto = mapper.readValue(responce, ProductDto.class);
        } catch (JsonProcessingException e){
            e.printStackTrace();
        }
        return dto;
    }
}
