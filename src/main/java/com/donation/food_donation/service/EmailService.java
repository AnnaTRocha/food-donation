package com.donation.food_donation.service;

import com.donation.food_donation.model.Donation;
import com.donation.food_donation.model.DonationItem;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;

    public void sendDonationNotificationEmail(String toEmail, Donation donation) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Nova doação recebida para: " + donation.getItem().getTitle());

        String text = String.format(
                "Olá %s,\n\n" +
                        "Você recebeu uma nova doação para o item: %s\n" +
                        "Doador: %s\n" +
                        "Telefone: %s\n" +
                        "Data agendada para retirada: %s\n" +
                        "Endereço para retirada: %s\n\n" +
                        "Por favor, entre em contato com o doador para confirmar os detalhes.\n\n" +
                        "Atenciosamente,\nEquipe donation.food_donation",
                donation.getRecipient().getName(),
                donation.getItem().getTitle(),
                donation.getDonor().getName(),
                donation.getDonor().getPhone(),
                donation.getScheduledPickupDate().toString(),
                donation.getPickupAddress()
        );

        message.setText(text);
        mailSender.send(message);
    }

    public void sendItemReminderEmail(String toEmail, DonationItem item) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Lembrete: Item de doação pendente - " + item.getTitle());

        String text = String.format(
                "Olá %s,\n\n" +
                        "Este é um lembrete sobre o item de doação que você cadastrou:\n" +
                        "Título: %s\n" +
                        "Descrição: %s\n" +
                        "Data de cadastro: %s\n\n" +
                        "Por favor, verifique se você ainda precisa deste item. Caso já tenha recebido, " +
                        "marque-o como concluído em nosso sistema.\n\n" +
                        "Atenciosamente,\nEquipe donation.food_donation",
                item.getUser().getName(),
                item.getTitle(),
                item.getDescription(),
                item.getCreatedAt().toString()
        );

        message.setText(text);
        mailSender.send(message);
    }
}