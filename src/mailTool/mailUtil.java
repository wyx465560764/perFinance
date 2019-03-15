package mailTool;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import java.util.Properties;

/*
 * 管理员发送邮件的配置类
 * 作者：海哥
 * 时间：2017年8月20日
 */
public class mailUtil {
    public void SendMessage(String receiver, String title, String content)
            throws AddressException,MessagingException{
        Properties properties = new Properties();
//        properties.put("mail.debug", "true");//设置是否显示debug信息  true 会在控制台显示相关信息
//        properties.put("mail.transport.protocol", "smtp");// 连接协议
//        properties.put("mail.smtp.host", "smtp.qq.com");// 主机名
//        properties.put("mail.smtp.port", 465);// 端口号
//        properties.put("mail.smtp.auth", "true");
//        properties.put("mail.smtp.ssl.enable", "true");//设置是否使用ssl安全连接  ---一般都使用
//        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//        properties.put("mail.smtp.socketFactory.port", "465");以上为qq邮箱使用代码
        properties.put("mail.debug", "true");//设置是否显示debug信息  true 会在控制台显示相关信息
        String host = "smtp.163.com";
        properties.setProperty("mail.smtp.host", host);
        properties.put("mail.smtp.auth", "true");
//		 阿里云服务器禁用25端口，所以服务器上改为465端口
        properties.put("mail.smtp.port", 465);// 端口号
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.smtp.socketFactory.fallback", "false");
        properties.setProperty("mail.smtp.socketFactory.port", "465");



        //得到回话对象
        Session session = Session.getInstance(properties);
        // 获取邮件对象
        Message message = new MimeMessage(session);
        //设置发件人邮箱地址
        message.setFrom(new InternetAddress("13508233254@163.com"));
        //设置收件人地址
        message.setRecipients(RecipientType.TO,new InternetAddress[] { new InternetAddress(receiver) });
        //设置邮件标题
        message.setSubject(title);
        //设置邮件内容
        message.setText(content);
        //得到邮差对象
        Transport transport = session.getTransport();
        //连接自己的邮箱账户
        transport.connect("13508233254@163.com", "wyx123456");
//        transport.connect("465560764@qq.com", "qcpyvhwvdqmgbjed");//密码为刚才得到的授权码qq邮箱使用
        //发送邮件
        try {
            transport.sendMessage(message, message.getAllRecipients());
            System.out.println("邮件正在发送!");
            transport.close();
        }catch (MessagingException m){
            m.printStackTrace();
        }

    }
}