package by.emall.pages;

import by.emall.enums.CatalogSections;
import by.emall.pages.base.singleton.Singleton;
import by.emall.pages.base.utils.WaitUtil;
import by.emall.pages.base.utils.XPathUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class CatalogPage {
    private static final Logger logger = LogManager.getLogger(CatalogPage.class);
    private WebDriver driver;
    private By CATALOG_TITLE_LOCATOR;

    public CatalogPage() {
        this.driver = Singleton.getDriver();
        logger.info("CatalogPage initialized with WebDriver: {}", driver);
    }

    public String getCatalogTitle(CatalogSections section) {
        logger.info("Attempting to get the title for section: {}", section);
        CATALOG_TITLE_LOCATOR = By.xpath(XPathUtil.getXPathOfTitleByCategory(section));

        try {
            String title = driver.findElement(CATALOG_TITLE_LOCATOR).getText();
            logger.info("Catalog title for section {}: {}", section, title);
            return title;

        } catch (NoSuchElementException e) {
            logger.error("NoSuchElementException: Title for section {} not found. Error: {}", section, e.getMessage());
            return null;
        } catch (Exception e) {
            logger.error("Error getting catalog title for section {}: {}", section, e.getMessage());
            return null;
        }
    }
}
