package tests.US_0008;

import com.github.javafaker.Faker;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HotelMyCampMain;
import pages.HotelMyCampRoomReservation;

import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;



public class TestCase06 {

    HotelMyCampRoomReservation hotelMyCampRoomReservation;
    HotelMyCampMain hotelMyCampMain;


    @Test
    public void test06() {

        //siteye gidilir
        Driver.getDriver().get(ConfigReader.getProperty("HMCUrl"));
        ReusableMethods.waitFor(2);
        //log in olunur
        hotelMyCampMain=new HotelMyCampMain();
        hotelMyCampMain.anasayfaLogin.click();
        hotelMyCampMain.usernameBox.sendKeys(ConfigReader.getProperty("ValidUserName"));
        hotelMyCampMain.passwordBox.sendKeys(ConfigReader.getProperty("ValidPassword"));
        ReusableMethods.waitFor(2);
        hotelMyCampMain.loginButtonElementi.click();


    }
    @Test
    public void test07(){

        //hotel management elementi gorunuyor mu diye bakilir
        //hotel management elementine tiklanir
        //room reservation gorunur mu diye kontrol edilir
        Assert.assertTrue(hotelMyCampMain.hotelManagementButtonElementi.isDisplayed());
        ReusableMethods.waitFor(2);
        hotelMyCampMain.hotelManagementButtonElementi.click();
        ReusableMethods.waitFor(2);
        Assert.assertTrue(hotelMyCampMain.roomReservationButtonElementi.isDisplayed());

    }

    @Test
    public void test08(){

        //room reservation elemntine tiklanir
        //add room reservation butonu gorunuyor mu diye kontrol edilir
        hotelMyCampMain.roomReservationButtonElementi.click();
        Assert.assertTrue(hotelMyCampMain.addRoomReservationButtonElementi.isDisplayed());
    }

    @Test
    public void test09(){

        //add room reservation butonuna tiklanir
        //create hotelroomreservation formu gorunuyor mu diye kontrol edilir
        hotelMyCampMain.addRoomReservationButtonElementi.click();
        hotelMyCampRoomReservation=new HotelMyCampRoomReservation();
        Assert.assertTrue(hotelMyCampRoomReservation.createHotelroomreservatıonFormu.isDisplayed());


    }
    Select select;
    @Test
    public void test10(){

        //formdaki kutucuklar secilip uygun sekilde doldurulur
        Actions actions=new Actions(Driver.getDriver());
        Faker faker=new Faker();
        select=new Select(hotelMyCampRoomReservation.idUserilk);
        select.selectByIndex(1);
        hotelMyCampRoomReservation.idUserikinci.click();
        Select select1=new Select(hotelMyCampRoomReservation.idUserikinci);
        ReusableMethods.waitFor(2);
        select1.selectByIndex(1);
        //actions.sendKeys(Keys.ENTER).perform();

        hotelMyCampRoomReservation.reservationPrice.click();
        actions.sendKeys("1000")
                .sendKeys(Keys.TAB)
                .sendKeys("02.03.2022",Keys.ENTER,Keys.TAB,Keys.TAB)
                .sendKeys("04.03.2022",Keys.ENTER,Keys.TAB,Keys.TAB)
                .sendKeys("1",Keys.TAB)
                .sendKeys("0",Keys.TAB)
                .sendKeys(faker.name().name(),Keys.TAB)
                .sendKeys(faker.phoneNumber().cellPhone(),Keys.TAB)
                .sendKeys(faker.internet().emailAddress(),Keys.TAB)
                .sendKeys("Ucret pesin kirmizi mesin")
                .perform();

        hotelMyCampRoomReservation.isPaidKutucugu.click();
        actions.sendKeys(Keys.TAB).perform();

        //tum kisimlar doldurulduktan sonra formun en altindaki save butonu tiklanir
        //reservation islemi basarili onayi gorulur ve ok butonu tiklanarak islem sonlandirilir
        Assert.assertTrue(hotelMyCampRoomReservation.saveButonu.isDisplayed());
        hotelMyCampRoomReservation.saveButonu.click();
        ReusableMethods.waitFor(3);
        Assert.assertTrue(hotelMyCampRoomReservation.reservationOnayYazisi.isDisplayed());
        hotelMyCampRoomReservation.onayOkTusu.click();
        Driver.closeDriver();



    }

}