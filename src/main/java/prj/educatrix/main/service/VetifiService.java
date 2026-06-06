package prj.educatrix.main.service;


import java.util.Random;

public class VetifiService {
    private final MailService mailService;

    public VetifiService(MailService mailService) {
        this.mailService = mailService;
    }
    public String inputCapcha() {
        String capcha = "";
        for (char i = 'a'; i <= 'z'; i++) {
            capcha += i;
        }
        for (char j = 'A'; j <= 'Z'; j++) {
            capcha += j;
        }
        for (char z = '0'; z <= '9'; z++) {
            capcha += z;
        }
        Random rd = new Random();
        int sizeCapcha = 5;
        int count = 1;
        String capchaCheck = "";
        while (count <= sizeCapcha) {
            int c = rd.nextInt(capcha.length());
            capchaCheck += capcha.charAt(c);
            count++;
        }
        return capchaCheck;
    }

}
