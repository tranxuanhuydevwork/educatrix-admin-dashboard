package prj.educatrix.main.controller;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SeleniumTest {

    @Test
    public void testBanUserNotFirstOne() throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("http://localhost:8080/admin/users/learner");

        List<WebElement> checkboxes = driver.findElements(By.xpath("//table[@id='activeAccountTab']//tbody//tr//td[1]//input[@type='checkbox']"));

        if (checkboxes.size() > 1) {
            for (int i = 1; i < checkboxes.size(); i++) {
                checkboxes.get(i).click();
            }
        } else {
            System.out.println("Không có đủ user để thực hiện thao tác.");
            driver.quit();
            return;
        }


        WebElement bulkBanButton = driver.findElement(By.id("bulkBan"));
        bulkBanButton.click();
        Thread.sleep(3000);

        WebElement reasonInput = driver.findElement(By.id("reason"));
        reasonInput.sendKeys("Vi phạm chính sách cộng đồng.");

        WebElement imageUpload = driver.findElement(By.id("imageEvidence"));
        String filePath = "F:\\SPRING-BOOT\\educatrix\\educatrix-test\\src\\main\\resources\\static\\images\\default-avatar.png";
        imageUpload.sendKeys(filePath);

        WebElement confirmTextInput = driver.findElement(By.id("confirmText"));
        confirmTextInput.sendKeys("CONFIRM");

        Thread.sleep(2000);

        WebElement confirmYesButton = driver.findElement(By.id("confirmBanBtn"));
        confirmYesButton.click();

        Thread.sleep(2000);

        String currentUrl = driver.getCurrentUrl();
        if (currentUrl.contains("/admin/users/learner")) {
            System.out.println("Ban user thành công!");
        } else {
            System.out.println("Ban user thất bại.");
        }

        driver.quit();
    }
    @Test
    public void testUnbanUser() throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("http://localhost:8080/admin/users/learner");

        WebElement bannedTabButton = driver.findElement(By.xpath("//button[text()='Banned Accounts']"));
        bannedTabButton.click();
        Thread.sleep(5000);

        List<WebElement> rows = driver.findElements(By.xpath("//table[@id='bannedAccountTab']//tbody//tr"));
        List<String> bannedUsernames = new ArrayList<>();

        for (WebElement row : rows) {
            WebElement usernameCell = row.findElement(By.xpath(".//td[2]"));
            bannedUsernames.add(usernameCell.getText());
        }

        List<WebElement> checkboxes = driver.findElements(By.xpath("//table[@id='bannedAccountTab']//tbody//tr//td//input[@type='checkbox']"));

        if (!checkboxes.isEmpty()) {
            for (int i = 1; i < checkboxes.size(); i++) {
                checkboxes.get(i).click();
            }
        } else {
            System.out.println("Không có user nào trong danh sách Banned.");
            driver.quit();
            return;
        }

        WebElement bulkUnbanButton = driver.findElement(By.id("bulkUnban"));
        bulkUnbanButton.click();
        Thread.sleep(2000);
        WebElement confirmTextInput = driver.findElement(By.id("confirmText"));
        confirmTextInput.sendKeys("CONFIRM");
        Thread.sleep(3000);

        WebElement confirmBanBtn = driver.findElement(By.id("confirmBanBtn"));
        confirmBanBtn.click();
        Thread.sleep(5000);

        WebElement activeTabButton = driver.findElement(By.xpath("//button[text()='Active Accounts']"));
        activeTabButton.click();
        Thread.sleep(8000);

        List<WebElement> unbannedUsers = driver.findElements(By.xpath("//table[@id='activeAccountTab']//tbody//tr"));
        List<String> activeUsernames = new ArrayList<>();

        for (WebElement userRow : unbannedUsers) {
            WebElement usernameCell = userRow.findElement(By.xpath(".//td[2]"));
            activeUsernames.add(usernameCell.getText());
        }

        Thread.sleep(10000);
        boolean allUsersUnbanned = true;

        for (String username : bannedUsernames) {
            if (!activeUsernames.contains(username)) {
                System.out.println("User chưa được unban: " + username);
                allUsersUnbanned = false;
            }
        }

        if (allUsersUnbanned) {
            System.out.println("Tất cả user đã được unban thành công!");
        } else {
            System.out.println("Một số user chưa được unban.");
        }

        driver.quit();
    }

}
