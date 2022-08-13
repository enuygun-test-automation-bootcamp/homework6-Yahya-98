package pageobjects;

import framework.Helper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class HomePage {
    WebDriver driver;
    Helper elementHelper;
    By searchInput = By.id("search-input");
    By searchIcon = By.cssSelector("button[type=submit]");
    By wishListIcon = By.cssSelector("a[data-tooltip=\"Add to Wishlist\"]");
    By imageBox = By.cssSelector(".tt-image-box");
    By favorites = By.cssSelector("div[data-tooltip=\"Favorites\"]");
    By titleOfWish = By.xpath("//*/h2[@class='tt-title']");
    By titleOfResWish = By.xpath("//div[@id=\"products\"]/div[1]/div/div[2]/span/a");
    By myaccount = By.xpath("//button[@data-tooltip=\"My account\"]");
    By singin = By.xpath("//div[@class=\"tt-dropdown-inner\"]//ul//li[2]/a");
    By loginemail = By.xpath("//input[@id=\"loginEmail\"]");
    By loginpass = By.xpath("//input[@id=\"loginPass\"]");
    By loginbtn = By.xpath("//button[@class=\"btn \"]");
    By signout = By.xpath("//div[@class=\"tt-dropdown-inner\"]//ul//li[3]/a");
    By homebtn = By.xpath("//img[@class=\"loading\"]");
    By removewishlist = By.xpath("//a[@class=\"btn-link js-removeitem delete-wishlist\"]");
    By wishlistcounter = By.xpath("//span[@class=\"fav-count tt-badge-cart\"]");

    public HomePage(WebDriver driver)
    {
        this.driver=driver;
        this.elementHelper=new Helper(driver);
    }

    public String addItemToWishList(){
        this.elementHelper.findElement(searchInput).click();
        this.elementHelper.typeForInput("rose",this.elementHelper.findElement(searchInput));
        this.elementHelper.findElement(searchIcon).click();
        this.elementHelper.hoverMover(this.elementHelper.findElement(imageBox));
        this.elementHelper.findElements(wishListIcon).get(0).click();
        return this.elementHelper.findElements(titleOfResWish).get(0).getText();

    }

    public void checkWishInFavorList(String expectedWish){
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,-250)");
        this.elementHelper.findElement(favorites).click();
        Assert.assertEquals(expectedWish,this.elementHelper.findElement(titleOfWish).getText());


    }

    public String login(String email, String pass){
        this.elementHelper.findElement(myaccount).click();
        this.elementHelper.findElement(singin).click();
        this.elementHelper.findElement(loginemail).sendKeys(email);
        this.elementHelper.findElement(loginpass).sendKeys(pass);
        this.elementHelper.findElement(loginbtn).click();
        this.elementHelper.findElement(myaccount).click();
        return this.elementHelper.findElement(signout).getText();

    }

    public String removeWishlist(){
        this.elementHelper.findElement(removewishlist).click();
        this.elementHelper.findElement(homebtn).click();
        return this.elementHelper.findElement(wishlistcounter).getText();

    }



}
