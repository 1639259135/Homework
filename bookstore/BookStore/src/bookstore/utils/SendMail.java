package bookstore.utils;

import bookstore.user.web.UserServlet;
import org.junit.Test;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
public class SendMail{

    private String sendAddr = "1639259135";//这是发送方的邮箱
//    private String receiveAddr = "";//接收方的邮箱

    //这个不是密码,是开启163邮箱第三方登录时候设置的授权码
    //如果不开启,那么是无法第三方登录163邮箱账号的
    private String pass = "rmltxwipsbmhcdcg";

    @Test
    public void sendMail(String receiveAddr,String code) throws MessagingException {
        Properties prop = new Properties();
        //设置服务器主机名
        prop.setProperty("mail.host", "smtp.qq.com");
        prop.setProperty("mail.smtp.auth", "true");
        prop.setProperty("mail.smtp.port", "587");
//		prop.put("mail.transport.protocol", "smtp");

        //我们要登录一个账号,来给别人发邮件
        //现在先构建一个邮件账号，需要用邮件地址和密码
        javax.mail.Authenticator auth = new javax.mail.Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(sendAddr + "@qq.com", pass);
            }
        };

        //与服务器连接,这时候就连上了163.com的服务器
        //实际上就是用java代码,登录了自己的163邮箱
        Session session = Session.getInstance(prop, auth);

        //创建一个邮件对象
        //这就是登录了邮箱之后,点击了发邮件,进入编辑邮件的窗口
        MimeMessage msg = new MimeMessage(session);

        //设置发件人
        msg.setFrom(new InternetAddress(sendAddr + "@qq.com"));

        //设置收件人
//        System.out.println(receiveAddr);
        msg.setRecipients(Message.RecipientType.TO, receiveAddr);
        //设置抄送
//		msg.setRecipients(Message.RecipientType.CC,"抄送人@163.com");

        //设置密送
        //一封邮件发给了a,b,c三个人,a是可以知道这封邮件也发给了b,c的
        //如果设置了密送给d,那么a,b,c是不知道这封邮件也发给了d的
//		msg.setRecipients(Message.RecipientType.BCC,"偷偷的发@163.com");

        //添加主题
        msg.setSubject("激活账号");

        //添加内容,第二个参数表示这是html的内容
        UserServlet us = new UserServlet();

        msg.setContent("\n<a href=\"http://localhost:8080/bookstore/user?method=active&code="
                + code +"\">点击激活</a>", "text/html;charset=utf-8");

        Transport.send(msg);

    }
}
