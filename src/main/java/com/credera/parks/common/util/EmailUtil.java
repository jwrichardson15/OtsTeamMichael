package com.credera.parks.common.util;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendTemplatedEmailRequest;

import com.credera.parks.common.dto.TicketDTO;
import com.credera.parks.common.model.Ticket;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${parks.email.from}")
    private String from;

    public void send(boolean create, Ticket ticket) {
        String template;
        if (create) {
            template = "CreateTicket";
        } else {
            template = "CompleteTicket";
        }
        try {
            ObjectMapper Obj = new ObjectMapper();

            String templateData = Obj.writeValueAsString(new TicketDTO(ticket));

            AmazonSimpleEmailService client =
                AmazonSimpleEmailServiceClientBuilder.standard()
                .withRegion(Regions.US_EAST_1).build();
            SendTemplatedEmailRequest request = new SendTemplatedEmailRequest()
                .withDestination(new Destination().withToAddresses(ticket.getEmail()))
                .withTemplate(template)
                .withTemplateData(templateData)
                .withSource(from);
            client.sendTemplatedEmail(request);
            logger.info("Successfully sent an email");
        } catch (Exception ex) {
            logger.warn("Sending an email failed with the following error: " + ex.getMessage());
        }
    }

}
