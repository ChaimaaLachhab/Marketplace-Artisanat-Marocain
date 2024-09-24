package com.artisanat_backend.service;

import com.artisanat_backend.model.Artisan;
import com.artisanat_backend.model.SubOrder;
import com.artisanat_backend.model.User;
import com.artisanat_backend.repository.ArtisanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    private ArtisanRepository artisanRepository;

    @Autowired
    private EmailService emailService;

    public void notifyArtisan(Long artisanId, SubOrder subOrder) {
        Artisan artisan = getArtisanById(artisanId);

        String subject = "Nouvelle sous-commande reçue";
        String message = "Cher " + artisan.getFullName() + ",\n\n" +
                         "Vous avez reçu une nouvelle sous-commande avec un total de " + subOrder.getSubTotal() + "€.\n" +
                         "Merci de traiter cette commande dès que possible.\n\n" +
                         "Détails de la sous-commande :\n" +
                         "ID de la sous-commande : " + subOrder.getId() + "\n" +
                         "Nombre de produits : " + subOrder.getProducts().size() + "\n\n" +
                         "Merci.";

        emailService.sendEmail(artisan.getEmail(), subject, message);
    }

    private Artisan getArtisanById(Long artisanId) {
        Artisan artisan = artisanRepository.findById(artisanId).orElseThrow();
        artisan.setId(artisanId);
        artisan.setFullName("Artisan " + artisanId);
        artisan.setEmail("artisan" + artisanId + "@exemple.com");
        return artisan;
    }
}
