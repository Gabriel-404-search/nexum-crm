package com.nexum.crm.infra.config.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
    public void sendLoginTokenHtml(String toEmail, String token, String username) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(toEmail);
            helper.setSubject("Nexum CRM - Seu Token de Acesso");

            String htmlContent = """
    <!DOCTYPE html>
    <html lang="pt">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Nexum CRM - Token de Acesso</title>
        <style>
            body {
                font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Helvetica, Arial, sans-serif;
                background-color: #f1f5f9;
                margin: 0;
                padding: 0;
                -webkit-font-smoothing: antialiased;
            }
            .wrapper {
                width: 100%%;
                table-layout: fixed;
                background-color: #f1f5f9;
                padding: 40px 0;
            }
            .main-table {
                background-color: #ffffff;
                margin: 0 auto;
                width: 100%%;
                max-width: 600px;
                border-radius: 12px;
                overflow: hidden;
                box-shadow: 0 10px 25px -5px rgba(0, 0, 0, 0.05), 0 8px 10px -6px rgba(0, 0, 0, 0.05);
                border: 1px solid #e2e8f0;
            }
            .header-bg {
                background: linear-gradient(135deg, #4f46e5 0%%, #2563eb 100%%);
                padding: 36px 30px;
                text-align: center;
                color: #ffffff;
            }
            .header-bg h1 {
                margin: 0;
                font-size: 26px;
                font-weight: 700;
                letter-spacing: -0.5px;
            }
            .header-bg p {
                margin: 8px 0 0 0;
                font-size: 14px;
                color: #e0e7ff;
            }
            .content-padding {
                padding: 40px 36px;
                color: #334155;
            }
            .greeting {
                font-size: 20px;
                font-weight: 600;
                color: #0f172a;
                margin-top: 0;
                margin-bottom: 16px;
            }
            .description {
                font-size: 15px;
                line-height: 1.6;
                color: #475569;
                margin-bottom: 24px;
            }
            .token-container {
                background-color: #f8fafc;
                border: 2px dashed #cbd5e1;
                border-radius: 10px;
                padding: 24px;
                text-align: center;
                margin: 28px 0;
            }
            .token-label {
                font-size: 11px;
                text-transform: uppercase;
                letter-spacing: 1px;
                font-weight: 700;
                color: #64748b;
                margin-bottom: 8px;
            }
            .token-code {
                font-family: 'Courier New', Courier, monospace;
                font-size: 18px;
                font-weight: bold;
                color: #4f46e5;
                word-break: break-all;
                letter-spacing: 1px;
            }
            .security-notice {
                font-size: 13px;
                color: #64748b;
                background-color: #f8fafc;
                border-left: 3px solid #3b82f6;
                padding: 12px 16px;
                border-radius: 0 6px 6px 0;
                margin-top: 24px;
                line-height: 1.5;
            }
            .footer-bg {
                background-color: #f8fafc;
                padding: 24px 36px;
                text-align: center;
                font-size: 12px;
                color: #94a3b8;
                border-top: 1px solid #e2e8f0;
            }
            .footer-bg p {
                margin: 0;
                line-height: 1.4;
            }
        </style>
    </head>
    <body>
        <center class="wrapper">
            <table class="main-table" width="100%%" cellpadding="0" cellspacing="0" border="0">
                <tr>
                    <td class="header-bg">
                        <h1>Nexum CRM</h1>
                        <p>Autenticação e Segurança Inteligente</p>
                    </td>
                </tr>
                <tr>
                    <td class="content-padding">
                        <h2 class="greeting">Olá, %s!</h2>
                        <p class="description">Recebemos uma solicitação de início de sessão associada à sua conta no Nexum CRM. Para concluir a validação e obter acesso à sua sessão, utilize o token exclusivo gerado abaixo:</p>
                        
                        <div class="token-container">
                            <div class="token-label">O seu Token de Acesso JWT</div>
                            <div class="token-code">%s</div>
                        </div>
                        
                        <div class="security-notice">
                            <strong>Nota de segurança:</strong> Se não solicitou este acesso, por favor ignore esta mensagem. O seu token é estritamente confidencial e não deve ser partilhado com terceiros.
                        </div>
                    </td>
                </tr>
                <tr>
                    <td class="footer-bg">
                        <p>&copy; 2026 Nexum CRM. Todos os direitos reservados.</p>
                        <p style="margin-top: 4px;">Este é um envio automático, por favor não responda a este email.</p>
                    </td>
                </tr>
            </table>
        </center>
    </body>
    </html>
    """.formatted(username, token);

            helper.setText(htmlContent, true);
            mailSender.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException("Falha ao enviar o email com o token de acesso.", e);
        }
    }
}

