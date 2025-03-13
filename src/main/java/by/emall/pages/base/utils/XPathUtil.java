package by.emall.pages.base.utils;

import by.emall.enums.CatalogSections;

public class XPathUtil {
    public static String getXPathByCategory(CatalogSections category) {
        return "//span[text()='" + category.getLabel() + "']";
    }
    public static String getXPathOfTitleByCategory(CatalogSections category) {
        return STR."//h1[contains(string(.),'\{category.getLabel()}')]";
    }

}
