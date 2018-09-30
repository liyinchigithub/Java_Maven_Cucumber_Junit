package com.cucumber;

import cucumber.api.java.After;
import cucumber.api.java.zh_cn.并且;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.awt.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import cucumber.api.java.zh_cn.假如;
import cucumber.api.java.zh_cn.当;
import cucumber.api.java.zh_cn.那么;
import org.openqa.selenium.chrome.ChromeOptions;

public class Stepdefs {
    public WebDriver driver;
    public boolean result;

    /**
     * @param url 网址url
     * @author liyinchi 2018.09.27
     * @deprecated 浏览器打开网页
     */
    @Before
    @假如("^打开\"([^\"]*)\"$")
    public void open_url(String url) {
        String chromdrivername = "chromedriver.exe";
        ChromeOptions options = new ChromeOptions();
        if (System.getProperty("os.name").toUpperCase().contains("WINDOWS")){
            chromdrivername = "chromedriver.exe";
            // 通过配置参数禁止data;的出现
            options.addArguments("--user-data-dir="+System.getProperty("user.home") + "/AppData/Local/Google/Chrome/User Data/Default");

            // 通过配置参数删除“您使用的是不受支持的命令行标记：--ignore-certificate-errors。稳定性和安全性会有所下降。”提示
            options.addArguments("--start-maximized", "allow-running-insecure-content", "--test-type");
        }
        else if (System.getProperty("os.name").toUpperCase().contains("MAC")){//mac系统
            chromdrivername = "chromedriver";
        }
        else {
            chromdrivername = "chromedriver";
        }
        //调用驱动chromedriverV2.36 或 V2.37，对应浏览器V65=>69
        System.setProperty("webdriver.chrome.driver", "Tools/"+chromdrivername);

        //将chrome设置为无头浏览器，即不启动浏览器界面
//        options.setHeadless(true);

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get(url);
        System.out.println("标题" + driver.getTitle());
    }


    /**
     * @author liyinchi 2018.09.27
     * @deprecated 浏览器打开网页
     */
    @After
    public void CloseBrowser() {
        driver.close();
    }


    /**
     * @deprecated feature文件的每个steps
     */
    @当("^用户点击首页登录")
    public void user_click_login() {
        driver.findElement(By.xpath("//*[@id=\"u1\"]/a[7]")).click();
    }

    @并且("^弹出登录方式窗口")
    public void login_window() {
        Assert.assertEquals("用户名登录", driver.findElement(By.xpath(".//p[contains(text(),\"用户名登录\")]")).getText());
    }

    @当("^选择用户名登录方式")
    public void username_login() {
        driver.findElement(By.xpath("//p[contains(text(),\"用户名登录\")]")).click();
    }

    @那么("^输入账号\"([^\"]*)\"$")
    public void input_username(String username) {
        driver.findElement(By.name("userName")).sendKeys(username);
    }

    @那么("^输入密码\"([^\"]*)\"$")
    public void input_password(String password) {
        driver.findElement(By.name("password")).sendKeys(password);
    }


    @那么("^点击登录后，登录成功\"([^\"]*)\"$")
    public void login_success(String username) {
        try {
            driver.findElement(By.xpath(".//input[@value='登录']")).click();
            driver.findElement(By.xpath("//span[contains(text()," + username + ")]"));
            result = true;
        } catch (Exception e) {
            result = false;
        }
        Assert.assertEquals(true, result);
    }

    @那么("^点击登录后，提示\"([^\"]*)\"$")
    public void error_tips(String error_tips) {
        try {
            driver.findElement(By.xpath(".//input[@value='登录']")).click();
            Thread.sleep(1500);
            Assert.assertEquals(error_tips, driver.findElement(By.xpath(".//span[@id='TANGRAM__PSP_10__error']")).getText());
            System.out.println(driver.findElement(By.xpath(".//span[@id='TANGRAM__PSP_10__error']")).getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}