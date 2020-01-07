package mastermind.service;


import org.junit.Test;

public class MailServiceTest {

    @Test
    public void sendDummyMail() {
        MailService mailService = new MailService();
        mailService.sendMail("tuwpisznazwemaila@gmail.com", "dummy", -1);
        assert(true);
    }
}
