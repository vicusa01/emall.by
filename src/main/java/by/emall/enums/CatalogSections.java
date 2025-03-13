package by.emall.enums;

public enum CatalogSections {
    ELECTRONICS("Электроника"),
    PHARMACY("Аптека"),
    CHILDREN_GOODS("Детские товары"),
    PET_SUPPLIES("Товары для животных"),
    BEAUTY_HEALTH("Красота, здоровье"),
    HOUSEHOLD_CHEMICALS("Бытовая химия, хозтовары"),
    FURNITURE("Мебель"),
    INTERIOR_TABLEWARE("Интерьер, посуда"),
    CONSTRUCTION_RENOVATION("Стройка, ремонт"),
    GARDEN("Дача, сад"),
    GROCERIES("Продукты"),
    HOME_APPLIANCES("Бытовая техника");

    private final String label;

    CatalogSections(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;

    }
}
