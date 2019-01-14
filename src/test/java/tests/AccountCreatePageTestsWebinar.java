package tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AccountCreatePageHelper;
import pages.HomePageHelper;
import pages.MenuPageHelper;
import pages.ProfilePageHelper;
import util.DataProviders;

/**
 * Created by Inka on 07-Jan-19.
 */
public class AccountCreatePageTestsWebinar extends TestBase {
    // we use here Helper-classes from the class(not from webinar)
    HomePageHelper homePage;
    AccountCreatePageHelper accountCreatePage;
    ProfilePageHelper profilePage;
    MenuPageHelper menuPage;

    @BeforeMethod
    public void initPage() {
        homePage = PageFactory
                .initElements(driver, HomePageHelper.class);
        accountCreatePage = PageFactory
                .initElements(driver, AccountCreatePageHelper
                        .class);
        profilePage = PageFactory.initElements(driver,
                ProfilePageHelper.class);
        menuPage = PageFactory
                .initElements(driver, MenuPageHelper.class);
        homePage.waitUntilPageLoad();
    }

    @Test
    public void  createNewAccount(){
        homePage.pressCreateAccountButton();
        accountCreatePage.waitUntilPageLoad()
                .enterValueToFieldEmailRandom()
                .enterValueToFieldPassword("example")
                .enterValueToFieldRepPassword("example")
                .pressRegistrationButton();
        profilePage.waitUntilPageLoad();

        Assert.assertEquals(profilePage.getHeader(),"Registration");

        profilePage.menuButtonClick();
        menuPage.waitUntilPageLoad()
                .pressLogOutButton();
        homePage.waitUntilPageLoad();

        Assert.assertEquals(homePage.getHeader(),"Shabbat in the family circle");

    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "randomUsers")
    public void  createNewAccountsWithDataProvider(String email, String password){
        homePage.pressCreateAccountButton();
        accountCreatePage.waitUntilPageLoad()
                .enterValueToFieldEmail(email)
                .enterValueToFieldPassword(password)
                .enterValueToFieldRepPassword(password)
                .pressRegistrationButton();
        profilePage.waitUntilPageLoad();

        Assert.assertEquals(profilePage.getHeader(),"Registration");

        profilePage.menuButtonClick();
        menuPage.waitUntilPageLoad()
                .pressLogOutButton();
        homePage.waitUntilPageLoad();

        Assert.assertEquals(homePage.getHeader(),"Shabbat in the family circle");

    }


}
