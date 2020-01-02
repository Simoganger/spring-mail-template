package com.simontech.mailTemplate.service;

import com.simontech.mailTemplate.dto.MailRequest;
import com.simontech.mailTemplate.dto.MailResponse;
import com.sun.mail.util.MailConnectException;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

/**
 *
 * @author simon
 */
@Service
public class EmailService {
    
    @Autowired
    private JavaMailSender sender;
    
    @Autowired
    private Configuration config;
    
    public MailResponse sendMail(MailRequest request, Map<String, Object> model){
        MailResponse response = new MailResponse();
        MimeMessage message = sender.createMimeMessage();
        
        try{
            // Set MediaType
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,StandardCharsets.UTF_8.name());
            
            // add attachment
            //helper.addAttachment("palmier.png", new ClassPathResource("palmier.png"));
            
            Template t = config.getTemplate("email-template.ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
            
            helper.setTo(request.getTo());
            helper.setText(html,true);
            helper.setSubject(request.getSubject());
            helper.setFrom(request.getFrom());
            sender.send(message);
            
            response.setMessage("Mail send to : " + request.getTo());
            response.setStatus(Boolean.TRUE);
            
        }catch(MessagingException | IOException | TemplateException e){
            response.setMessage("Mail sending failure : " + e.getMessage());
            response.setStatus(Boolean.FALSE);
        }
        return response;
    }
}
