package com.artisanat_backend.service;

import com.artisanat_backend.model.Artisan;
import com.artisanat_backend.model.SubOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class NotificationService {
    private final EmailService emailService;
    private final TemplateEngine templateEngine;

    @Autowired
    public NotificationService(EmailService emailService, TemplateEngine templateEngine) {
        this.emailService = emailService;
        this.templateEngine = templateEngine;
    }

    public void notifyArtisan(Artisan artisan, SubOrder subOrder) {
        String subject = "Nouvelle sous-commande reçue";
        String message = "Cher " + artisan.getFullName() + ",\n\n" +
                "Vous avez reçu une nouvelle sous-commande avec un total de " + subOrder.getSubTotal() + "€.\n" +
                "Merci de traiter cette commande dès que possible.\n\n" +
                "Détails de la sous-commande :\n" +
                "ID de la sous-commande : " + subOrder.getId() + "\n" +
                "Nombre de produits : " + subOrder.getSubOrderItems().size() + "\n\n" +
                "Merci.";

        emailService.sendEmail(artisan.getEmail(), subject, message);
    }
}
