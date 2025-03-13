package by.emall.api.product.filters;

public enum Filters {
    BY_SALE(3),
    FROM_LOW_PRICE(6),
    FROM_HIGH_PRICE(9),
    BY_POPULARITY(12),
    BY_RATE(15);

    private final Integer filterCode;

    Filters(Integer filterCode) {
        this.filterCode = filterCode;
    }

    public Integer getFilterCode() {
        return filterCode;
    }
}
