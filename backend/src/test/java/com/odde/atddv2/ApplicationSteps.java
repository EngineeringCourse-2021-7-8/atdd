package com.odde.atddv2;

import io.cucumber.java.After;
import io.cucumber.spring.CucumberContextConfiguration;
import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@ContextConfiguration(classes = {SpringVueApplication.class}, loader = SpringBootContextLoader.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@CucumberContextConfiguration
public class ApplicationSteps {
    private final WebDriver webDriver = createWebDriver();

    public WebDriver createWebDriver() {
        System.setProperty("webdriver.chrome.driver", getChromeDriverBinaryPath());
        return new ChromeDriver();
    }

    @After
    public void closeBrowser() {
        webDriver.quit();
    }

    @SneakyThrows
    private String getChromeDriverBinaryPath() {
        try (Stream<Path> walkStream = Files.walk(Paths.get(System.getProperty("user.home"), ".gradle", "webdriver", "chromedriver"))) {
            return walkStream
                    .filter(this::isChromeDriverBinary)
                    .findFirst()
                    .orElseThrow(() -> new IllegalStateException("can't find chrome driver binary"))
                    .toAbsolutePath().toString();
        }
    }

    private boolean isChromeDriverBinary(Path p) {
        File file = p.toFile();
        return file.isFile() && (file.getPath().endsWith("chromedriver") || file.getPath().endsWith("chromedriver.exe"));
    }
}
