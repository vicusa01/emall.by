package by.emall.ui.utils;

import by.emall.ui.enums.CatalogSections;

public class XPathUtil {
    public static String getXPathByCategory(CatalogSections category) {
        return "//span[text()='" + category.getLabel() + "']";
    }

    public static String getXPathOfTitleByCategory(CatalogSections category) {
        return "//h1[contains(string(.), '" + category.getLabel() + "')]";
    }

}
