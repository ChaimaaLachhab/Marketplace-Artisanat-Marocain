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

    // Hypothèse : Notification par email
    @Autowired
    private EmailService emailService;

    public void notifyArtisan(Long artisanId, SubOrder subOrder) {
        // Logique pour récupérer les informations de l'artisan
        Artisan artisan = getArtisanById(artisanId);

        // Créer un message de notification
        String subject = "Nouvelle sous-commande reçue";
        String message = "Cher " + artisan.getFullName() + ",\n\n" +
                         "Vous avez reçu une nouvelle sous-commande avec un total de " + subOrder.getSubTotal() + "€.\n" +
                         "Merci de traiter cette commande dès que possible.\n\n" +
                         "Détails de la sous-commande :\n" +
                         "ID de la sous-commande : " + subOrder.getId() + "\n" +
                         "Nombre de produits : " + subOrder.getProducts().size() + "\n\n" +
                         "Merci.";

        // Envoyer la notification par email
        emailService.sendEmail(artisan.getEmail(), subject, message);
    }

    // Simuler une méthode pour récupérer un artisan par ID
    private Artisan getArtisanById(Long artisanId) {
        Artisan artisan = artisanRepository.findById(artisanId).orElseThrow();
        artisan.setId(artisanId);
        artisan.setFullName("Artisan " + artisanId);
        artisan.setEmail("artisan" + artisanId + "@exemple.com");
        return artisan;
    }
}
