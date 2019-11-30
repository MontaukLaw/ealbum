package com.cloudhearing.ealbum.utils;

import com.cloudhearing.ealbum.entity.EmailCaptcha;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.Security;
import java.util.Date;
import java.util.Properties;

public class EmailSender {

    private final static String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
    //发件人地址
    public static String recipientAddress = "zunly@hotmail.com";
    //收件人地址
    public static String senderAddress = "ealbum@qq.com";

    private final static String EMAIL_ACCOUNT = "ealbum@qq.com";
    private final static String EMAIL_PASSWORD = "zlvzomnokybxdcig";
    private final static String SMTP_HOST = "smtp.qq.com";

    //    public static void main(String[] args) throws Exception {
    public static void sendCaptchaCode(EmailCaptcha emailCaptcha) throws Exception {
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        Properties props = System.getProperties();
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.setProperty("mail.store.protocol", "smtp");
        props.setProperty("mail.smtp.host", SMTP_HOST);
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.auth.login.disable", "true");
        Session session = Session.getDefaultInstance(props, null);

        //设置调试信息在控制台打印出来
        session.setDebug(true);
        //3、创建邮件的实例对象
        Message msg = getMimeMessage(session, emailCaptcha);
        //4、根据session对象获取邮件传输对象Transport
        Transport transport = session.getTransport();
        //设置发件人的账户名和密码
        transport.connect(EMAIL_ACCOUNT, EMAIL_PASSWORD);
        //发送邮件，并发送到所有收件人地址，message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
        transport.sendMessage(msg, msg.getAllRecipients());

        //如果只想发送给指定的人，可以如下写法
        //transport.sendMessage(msg, new Address[]{new InternetAddress("xxx@qq.com")});

        //5、关闭邮件连接
        transport.close();
    }

    public static MimeMessage getMimeMessage(Session session, EmailCaptcha emailCaptcha) throws Exception {
        //创建一封邮件的实例对象
        MimeMessage msg = new MimeMessage(session);
        //设置发件人地址
        msg.setFrom(new InternetAddress(senderAddress));
        /**
         * 设置收件人地址（可以增加多个收件人、抄送、密送），即下面这一行代码书写多行
         * MimeMessage.RecipientType.TO:发送
         * MimeMessage.RecipientType.CC：抄送
         * MimeMessage.RecipientType.BCC：密送
         */

        //msg.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(recipientAddress));

        msg.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(emailCaptcha.getEmail()));
        //设置邮件主题
        msg.setSubject("您的秋雨相册注册码", "UTF-8");
        //设置邮件正文
        msg.setContent("感谢您注册秋雨相册, 请在10分钟之内使用该注册码(请使用全大写字母): " + emailCaptcha.getCaptchaCode(), "text/html;charset=UTF-8");
        //设置邮件的发送时间,默认立即发送
        msg.setSentDate(new Date());

        return msg;
    }

}
