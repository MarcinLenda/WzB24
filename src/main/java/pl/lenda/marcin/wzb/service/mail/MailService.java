package pl.lenda.marcin.wzb.service.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Created by Promar on 04.11.2016.
 */
@Service
public class MailService {

    private final JavaMailSender javaMailSender;
    JavaMailSenderImpl sender = new JavaMailSenderImpl();
    MimeMessage mimeMessage = sender.createMimeMessage();


    @Autowired
    MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


    public MimeMessage mailSend(String setTo, String setFrom, String setSubject, String setText) throws MessagingException {
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        helper.setTo(setTo);
        helper.setReplyTo(setTo);
        helper.setFrom(setFrom);
        helper.setSubject(setSubject);
        helper.setText(setText, true);
//        FileSystemResource res = new FileSystemResource(new java.io.File("C:\\Users\\Promar\\Desktop\\b5.png"));
//        helper.addInline("identifier1234", res);
        javaMailSender.send(mimeMessage);

        return  mimeMessage;
    }

    public MimeMessage mailConfirmAccount(String setTo, String name, String surname, String userName, String number) throws MessagingException {

        String line1 = "";
        String line2 = "";
        String line3 = "";
        String line4 = "";
        String line5 = "";
        String ednContent = "</table>\n" +
                "\n" +
                "    <div style=\"text-align: center\">\n" +
                "        <a href=\"http://wzb24.pl/#/login\">\n" +
                "            <input style=\"margin-top: 30px;\" type=\"button\" value=\"Zaloguj się już teraz na WzB!\">\n" +
                "        </a>\n" +
                "    </div>\n" +
                "\n" +
                "    <div id=\"footer\">\n" +
                "        <p> Mail został wygenerowany automatycznie. All rights reserved &copy;</p>\n" +
                "        <div id=\"logo\">\n" +
                "            <img src='data:image/png;base64,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' alt=\"WzB\">\n" +
                "        </div>\n" +
                "    </div>\n" +
                "\n" +
                "</div>\n" +
                "\n" +
                "\n" +
                "</body>\n" +
                "</html>";




        String content = "<!DOCTYPE html>\n" +
                "\n" +
                "<html>\n" +
                "<head>\n" +
                "    <link href=\"https://fonts.googleapis.com/css?family=Roboto\" rel=\"stylesheet\">\n" +
                "    <meta charset=\"utf-8\">\n" +
                "    <style>\n" +
                "\n" +
                "        body {\n" +
                "            background-color: #dddddd;\n" +
                "            font-family: 'Roboto', sans-serif;\n" +
                "\n" +
                "        }\n" +
                "\n" +
                "        table {\n" +
                "            font-family: 'Roboto', sans-serif;\n" +
                "            border-collapse: collapse;\n" +
                "        }\n" +
                "\n" +
                "        h1 {\n" +
                "            margin-top: 28px;\n" +
                "            margin-bottom: 25px;\n" +
                "            font-family: 'Roboto', sans-serif;\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "\n" +
                "        p {\n" +
                "            font-size: 25px;\n" +
                "        }\n" +
                "\n" +
                "        td, th {\n" +
                "            border: 2px solid #FFFFFF;\n" +
                "            text-align: center;\n" +
                "            padding: 8px;\n" +
                "        }\n" +
                "\n" +
                "        tr:nth-child(even) {\n" +
                "            background-color: #dddddd;\n" +
                "        }\n" +
                "\n" +
                "        #content {\n" +
                "            width: 80%;\n" +
                "            margin: 0 auto;\n" +
                "            background-color: #FFFFFF;\n" +
                "            padding-top: 15px;\n" +
                "            padding-bottom: 15px;\n" +
                "            padding-left: 3px;\n" +
                "            padding-right: 4px;\n" +
                "\n" +
                "        }\n" +
                "\n" +
                "        #footer {\n" +
                "            width: 100%;\n" +
                "            height: 80px;\n" +
                "            background-color: rgba(0, 0, 0, .87);\n" +
                "            color: #FFFFFF;\n" +
                "            padding-left: 1px;\n" +
                "            margin-top: 30px;\n" +
                "\n" +
                "        }\n" +
                "\n" +
                "        #footer p {\n" +
                "            font-family: 'Roboto', sans-serif;\n" +
                "            float: left;\n" +
                "            font-size: 17px;\n" +
                "        }\n" +
                "\n" +
                "        #logo {\n" +
                "            float: right;\n" +
                "            margin-right: 25px;\n" +
                "\n" +
                "        }\n" +
                "\n" +
                "        input {\n" +
                "            font-family: \"Open Sans\", Sans-serif;\n" +
                "            font-size: 25px;\n" +
                "            width: 470px;\n" +
                "            height: 50px;\n" +
                "            display: inline-block;\n" +
                "            margin: 0 auto;\n" +
                "            background: #23AD21;\n" +
                "            color: white;\n" +
                "            border: solid 1px #b3aca7;\n" +
                "        }\n" +
                "\n" +
                "        input:hover {\n" +
                "            background: #fec503;\n" +
                "            cursor: pointer;\n" +
                "\n" +
                "        }\n" +
                "\n" +
                "\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<div id=\"content\">\n" +
                "    <h1 style=\"font-size: 41px;\">Witaj "+name+"!</h1>\n" +
                "    <p>Aktywacja konta "+userName+" przebiegła pomyślnie. Twoje dane to:</p>\n" +
                "\n" +
                "    <table style=\"margin: 0 auto;\">\n" +
                "\n" +
                "        <tr style=\"background-color: #23AD21; color:white;\">\n" +
                "            <th style='width: 30%; font-size: 16px;'>E-mail</th>\n" +
                "            <th style='width: 25%; font-size: 16px;'>Imie</th>\n" +
                "            <th style='width: 30%; font-size: 16px;'>Nazwisko</th>\n" +
                "            <th style='width: 15%; font-size: 16px;'>Numer pracownika</th>\n" +
                "        </tr>";

        line1 = "<th style=' font-size: 16px;'>" + userName + "</th>";
        line2 = "<th style=' font-size: 16px;'>" + name + "</th>";
        line3 = "<th style=' font-size: 16px;'>" + surname + "</th>";
        line5 = "<th style=' font-size: 16px;'>" + number + "</th>";

        String allContent = content + line1 + line2 + line3 + line5 + ednContent;


        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        helper.setTo(setTo);
        helper.setReplyTo(setTo);
        helper.setFrom("wzbims@gmail.com");
        helper.setSubject("Aktywacja konta: "+userName);
        helper.setText(allContent, true);

        javaMailSender.send(mimeMessage);
        return mimeMessage;
    }
}