package com.credera.parks.common.util;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${parks.email.from}")
    private String from;

    static final String CREATESUBJECT = "National Parks - Ticket Creation";

    static final String CREATEHTMLBODY = "<h1>National Parks Ticket Request</h1>"
        + "<p>Thank you for letting us know! We'll have a team member take a look at it.</p>";

    static final String CREATETEXTBODY = "Thank you for letting us know! We'll have a team member take a look at it.";

    static final String COMPLETESUBJECT = "National Parks - Ticket Completion";

    static final String COMPLETEHTMLBODY = "<h1>National Parks Ticket Completion</h1>"
        + "<p>Your ticket has now been completed.</p>";

    static final String COMPLETETEXTBODY = "Your ticket has now been completed.";

    public void send(boolean create, String to) {
        String subject;
        String htmlBody;
        String textBody;
        if (create) {
            subject = CREATESUBJECT;
            htmlBody = CREATEHTMLBODY;
            textBody = CREATETEXTBODY;
        } else {
            subject = COMPLETESUBJECT;
            htmlBody = COMPLETEHTMLBODY;
            textBody = COMPLETETEXTBODY;
        }
        try {
            AmazonSimpleEmailService client =
                AmazonSimpleEmailServiceClientBuilder.standard()
                .withRegion(Regions.US_EAST_1).build();
            SendEmailRequest request = new SendEmailRequest()
                .withDestination(
                                 new Destination().withToAddresses(to))
                .withMessage(new Message()
                             .withBody(new Body()
                                       .withHtml(new Content()
                                                 .withCharset("UTF-8").withData(htmlBody))
                                       .withText(new Content()
                                                 .withCharset("UTF-8").withData(textBody)))
                             .withSubject(new Content()
                                          .withCharset("UTF-8").withData(subject)))
                .withSource(from);
            client.sendEmail(request);
            logger.info("Successfully sent an email");
        } catch (Exception ex) {
            logger.warn("Sending an email failed with the following error: " + ex.getMessage());
        }
    }

}
