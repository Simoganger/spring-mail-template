package com.simontech.mailTemplate.endpoint;

import com.simontech.mailTemplate.dto.MailRequest;
import com.simontech.mailTemplate.dto.MailResponse;
import com.simontech.mailTemplate.service.EmailService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author simon
 */
@RestController
public class EmailController {

    @Autowired
    EmailService emailService;

    @PostMapping("/send-mail")
    public MailResponse sendEmail(@RequestBody MailRequest request) {

        // Retrouver l'année en cours pour fixer le copyright
        SimpleDateFormat df = new SimpleDateFormat("yyyy");

        Map<String, Object> model = new HashMap<>();

        model.put("name", request.getName());
        model.put("year", df.format(new Date()));
        model.put("titre", "Validation d'une inscription");
        model.put("contenu", "Une nouvelle inscription est disponible");
        model.put("formule", "Cordialement");

        return emailService.sendMail(request, model);
    }

}
