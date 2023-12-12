/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilities;

import static constants.Constants.MAIL_HOST;
import static constants.Constants.MAIL_PORT;
import static constants.Constants.OEMAIL;
import static constants.Constants.OPASSWORD;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import java.io.File;
import java.io.StringWriter;
import javax.mail.PasswordAuthentication;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



/**
 *
 * @author Bhatt Jaimin
 */
public class EmailUtil {
    private String templatePath;
    private String templateName;
    private String subject;
    private Map<String, Object> dataModel;
    private String otp=null;
    private ResourceBundle bundle;


    public EmailUtil() {
        dataModel = new HashMap<>();
    }

    public EmailUtil(String basename) {
        dataModel = new HashMap<>();
    //    this.bundle = ResourceBundle.getBundle(basename, context.getViewRoot().getLocale());
    }

    public String getTemplatePath() {
        return templatePath;
    }

    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public Map<String, Object> getDataModel() {
        return dataModel;
    }

    public void setDataModel(Map<String, Object> dataModel) {
        this.dataModel = dataModel;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int sendSingleMailSync(String recipient) {
        
        Properties props = new Properties();
        
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", MAIL_HOST);
        props.put("mail.smtp.port", MAIL_PORT);

        String senderMail = OEMAIL;
        String senderPass = OPASSWORD;
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderMail, senderPass);
            }
        });
        try {
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setRecipient(Message.RecipientType.TO, InternetAddress.parse(recipient)[0]);
            mimeMessage.setSubject(subject);
            //sendOTP();
            //String emailContent = generateEmailContent();
             otp=OTPUtils.generateOTP();
            mimeMessage.setText("Reset Password!!OTP");
            String content = "<h1>"+otp+"</h1>";
            String completeMessage = "<html><body>" +
  "<h3>Your One time password (OTP) From PIZZAHUNT is : </h3>" +
  content +
"</body></html>";
mimeMessage.setContent(completeMessage, "text/html");
            mimeMessage.setContent(completeMessage,"text/html");
            Transport.send(mimeMessage);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Integer.parseInt(otp);
    }

    private String generateEmailContent() {
        try {
            // Create a free marker configuration
            Configuration freeMarkerConfig = new Configuration(Configuration.VERSION_2_3_30);
//            freeMarkerConfig.setDirectoryForTemplateLoading(new File("classpath:/templates"));
            freeMarkerConfig.setTemplateLoader(new ClassTemplateLoader(EmailUtil.class,templatePath ));
            //Loading template
            Template template = freeMarkerConfig.getTemplate(templateName);

            //Creating datamodel to merge with our template template
//            Map<String, Object> dataModel = new HashMap<>();
//            dataModel.put("OTP", otp);
            //Merging template with free datamodel
            StringWriter stringWriter = new StringWriter();
            template.process(dataModel, stringWriter);
            return stringWriter.toString();
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }
    private void sendOTP() {
            setSubject("One time password (OTP) for resetting your Password ");
            setTemplatePath("classpath:/templetes");
            setTemplateName("templetes.html");
            HashMap<String, Object> dataModel = new HashMap<>();
            otp=OTPUtils.generateOTP();
            dataModel.put("OTP", otp);
            setDataModel(dataModel);
    }

}
