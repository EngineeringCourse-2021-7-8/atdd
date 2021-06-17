package com.odde.atddv2;

import io.cucumber.java.zh_cn.当;
import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.By.xpath;

public class TestSteps {
    private final WebDriver webDriver = createWebDriver();

    public WebDriver createWebDriver() {
        System.setProperty("webdriver.chrome.driver", getChromeDriverBinaryPath());
        return new ChromeDriver();
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

    @当("测试环境")
    public void 测试环境() {
        webDriver.get("http://localhost:10081/");
        assertThat(webDriver.findElements(xpath("//*[text()='登录']"))).isNotEmpty();
        webDriver.quit();
    }
}
