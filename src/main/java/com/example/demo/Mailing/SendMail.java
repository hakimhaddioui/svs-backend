package com.example.demo.Mailing;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
public class SendMail {

    @Autowired
    private JavaMailSender emailSender;

    // Méthode pour lire et personnaliser un template
    private String loadTemplate(String templateName, Map<String, String> placeholders) {
        try {
            // Charger le fichier HTML à partir des ressources
            ClassPathResource resource = new ClassPathResource("templates/" + templateName);
            String template = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);

            // Remplacer les placeholders par les valeurs dynamiques
            for (Map.Entry<String, String> entry : placeholders.entrySet()) {
                template = template.replace("{{" + entry.getKey() + "}}", entry.getValue());
            }

            return template;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    // Envoi de l'email de bienvenue avec personnalisation
    @Async
    public void sendWelcomeEmail(String email, String username, String password) {
        String subject = "Bienvenue sur notre plateforme !";
        Map<String, String> placeholders = new HashMap<>();
        placeholders.put("username", username);
        placeholders.put("password", password);

        String htmlContent = loadTemplate("sendWelcomeEmail.html", placeholders);
        sendEmailWithTemplate(email, subject, htmlContent);
    }

    // Envoi de l'email lors de la modification du mot de passe
    @Async
    public void sendPasswordUpdateEmail(String email) {
        String subject = "Modification de votre mot de passe";
        Map<String, String> placeholders = new HashMap<>();
        // Pas de données dynamiques dans ce cas
        String htmlContent = loadTemplate("sendPasswordUpdateEmail.html", placeholders);
        sendEmailWithTemplate(email, subject, htmlContent);
    }

    // Envoi de l'email lors de la réinitialisation du mot de passe
    @Async
    public void sendPasswordResetEmail(String email, String newPassword) {
        String subject = "Réinitialisation de votre mot de passe";
        Map<String, String> placeholders = new HashMap<>();
        placeholders.put("email", email);
        placeholders.put("newPassword", newPassword);

        String htmlContent = loadTemplate("sendPasswordResetEmail.html", placeholders);
        sendEmailWithTemplate(email, subject, htmlContent);
    }

    // Méthode pour envoyer l'email
    public void sendEmailWithTemplate(String to, String subject, String htmlContent) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlContent, true); // Le texte est du HTML
            helper.addInline("logo_oncf", new ClassPathResource("static/images/Logo-oncf.png"));
            emailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
