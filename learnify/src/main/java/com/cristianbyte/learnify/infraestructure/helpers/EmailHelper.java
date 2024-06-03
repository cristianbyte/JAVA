package com.cristianbyte.learnify.infraestructure.helpers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class EmailHelper {
    // Inject the email service library
    private final JavaMailSender mailSender;

    public void sendMail(String destination, String name, String course_name){
        MimeMessage message = mailSender.createMimeMessage();

        String htmlContent = this.readHTMLTemplate(name, course_name);

        try {
            message.setFrom(new InternetAddress("charlsmaritz@gmail.com"));
            message.setSubject("You have a new course!");

            message.setRecipients(MimeMessage.RecipientType.TO, destination);
            message.setContent(htmlContent, MediaType.TEXT_HTML_VALUE);

            mailSender.send(message);
            System.out.println("Email sent");

        } catch (MessagingException e) {
            System.out.println("ERROR: Messaging exception occurred - " + e.getMessage());
        }
        
    }

    private String readHTMLTemplate(String name, String course_name){
        // Indicate where the template is located
        final Path path = Paths.get("src/main/resources/mail-template/email_template.html");

        try (var lines = Files.lines(path)){
            var html = lines.collect(Collectors.joining());

            return html.replace("{name}", name)
                       .replace("{course_name}", course_name);
        } catch (IOException e) {
            System.out.println("Could not read the HTML template");
            throw new RuntimeException();
        }
    }
}
