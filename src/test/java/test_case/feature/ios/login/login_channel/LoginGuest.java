package test_case.feature.ios.login.login_channel;

import config.IOSConfig;
import data.HomeData;
import data.LoginData;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import page.ios.HomePage;
import page.ios.LoginPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import util.Actions;
import util.IOSActions;

import java.net.MalformedURLException;

public class LoginGuest extends IOSConfig {

    @BeforeMethod
    public void preCondition(){
        IOSActions.activeApp(appBundleID);
    }

    @Test(groups = {"SDK","SDK-TC-246"}, description = "Nhấn Guest login trong điều kiện mạng ổn định")
    public void SDK246_verifyLoginGuestChannel() throws MalformedURLException, InterruptedException {
        HomePage homePage = new HomePage(driver);
        //Open Login Form
        homePage.bypassPopup();
        homePage.selectMenu(HomeData.TEXT_Menu_Login + "->" + HomeData.TEXT_Menu_Show_Login_Form);
        //Select Play Now channel
        LoginPage loginPage = new LoginPage(driver);
        loginPage.selectLoginChannel(LoginData.TEXT_Play_Now_Channel);
        //Verify Popup
        Actions.compareText(Actions.getText(loginPage.titleNoticePopup), "Notice");
        String msg = loginPage.getPopupMessageContent();
        Actions.checkTextContains(msg, "UserID");
        Actions.checkTextContains(msg, "sessionID");
        Actions.checkTextContains(msg, "loginType");
        Actions.checkTextContains(msg, "socialID");
        //Close Popup
        Actions.clickElement(loginPage.buttonOKPopup);

    }

    @Test(groups = {"SDK","SDK-TC-249"}, description = "Kiểm tra kết quả trả về khi đang trong quá trình đăng nhập nhấn nút Home vật lý và resume lại khi dưới 1 phút")
    public void SDK249_verifyExitHomeWhileLogin() throws MalformedURLException, InterruptedException {
        HomePage homePage = new HomePage(driver);
        //Open Login Form
        homePage.bypassPopup();
        homePage.selectMenu(HomeData.TEXT_Menu_Login + "->" + HomeData.TEXT_Menu_Show_Login_Form);
        //Select Play Now channel
        LoginPage loginPage = new LoginPage(driver);
        loginPage.selectLoginChannel(LoginData.TEXT_Play_Now_Channel);
        //Run app in background then foreground
        IOSActions.runAppInBackGround(10);
        IOSActions.activeApp(appBundleID);
        loginPage.handleSecureAccount();

        //Verify Popup
        Actions.compareText(Actions.getText(loginPage.titleNoticePopup), "Notice");
        String msg = loginPage.getPopupMessageContent();
        Actions.checkTextContains(msg, "UserID");
        Actions.checkTextContains(msg, "sessionID");
        Actions.checkTextContains(msg, "loginType");
        Actions.checkTextContains(msg, "socialID");
        //Close Popup
        Actions.clickElement(loginPage.buttonOKPopup);

    }

    @AfterMethod
    public void cleanUp() {
        IOSActions.terminateApp(appBundleID);
    }
    @AfterSuite
    public void teardown() {
        driver.quit();
    }
}
