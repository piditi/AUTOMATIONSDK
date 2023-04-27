package test_case.feature.android.login.login_channel;

import config.AndroidConfig;
import data.HomeData;
import data.LoginData;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.android.HomePage;
import page.android.LoginPage;
import util.Actions;
import util.AndroidActions;
import util.DeviceHelper;

import java.net.MalformedURLException;

public class LoginGuest extends AndroidConfig{
    @BeforeMethod
    public void preCondition(){
        AndroidActions.startApp(appPackage,appActivity);
    }

    @Test(groups = {"SDK","SDK-TC-246"}, description = "Nhấn Guest login trong điều kiện mạng ổn định")
    public void SDK246_verifyLoginGuestChannel() throws MalformedURLException, InterruptedException {
        HomePage homePage = new HomePage(driver);
        //Open Login Form
        homePage.bypassPopup();
        homePage.selectMenu(HomeData.TEXT_Menu_LOGIN + "->" + HomeData.TEXT_Menu_SHOW_LOGIN_FORM);
        //Select Play Now channel
        LoginPage loginPage = new LoginPage(driver);
        loginPage.selectLoginChannel(LoginData.TEXT_Play_Now_Channel);
        //Verify Popup
        loginPage.handleSecureAccount();
        Actions.compareText(Actions.getText(loginPage.titleNoticeLoginPopup), "Notice");
        String msg = loginPage.getPopupMessageContent();
        Actions.checkTextContains(msg, "userId");
        Actions.checkTextContains(msg, "sessionId");
        Actions.checkTextContains(msg, "sdkCountry");
        Actions.checkTextContains(msg, "secureAcnStatus");
        //Close Popup
        Actions.clickElement(loginPage.buttonOKNoticeLoginPopup);
    }

    @Test(groups = {"SDK","SDK-TC-249"}, description = "Kiểm tra kết quả trả về khi đang trong quá trình đăng nhập nhấn nút Home vật lý và resume lại khi dưới 1 phút")
    public void SDK249_verifyExitHomeWhileLogin() throws MalformedURLException, InterruptedException {
        HomePage homePage = new HomePage(driver);
        //Open Login Form
        homePage.bypassPopup();
        homePage.selectMenu(HomeData.TEXT_Menu_LOGIN + "->" + HomeData.TEXT_Menu_SHOW_LOGIN_FORM);
        //Select Play Now channel
        LoginPage loginPage = new LoginPage(driver);
        loginPage.selectLoginChannel(LoginData.TEXT_Play_Now_Channel);
        //Run app in background then foreground
        AndroidActions.runAppInBackGround(10);
        loginPage.handleSecureAccount();
        //Verify Popup
        Actions.compareText(Actions.getText(loginPage.titleNoticeLoginPopup), "Notice");
        String msg = loginPage.getPopupMessageContent();
        Actions.checkTextContains(msg, "userId");
        Actions.checkTextContains(msg, "sessionId");
        Actions.checkTextContains(msg, "sdkCountry");
        Actions.checkTextContains(msg, "secureAcnStatus");
        //Close Popup
        Actions.clickElement(loginPage.buttonOKNoticeLoginPopup);
    }

    @AfterMethod
    public void cleanUp() {
    }
    @AfterSuite
    public void teardown() {
        driver.quit();
    }
}
