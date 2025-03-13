package by.emall.api.product.filters;


public enum CategoryCode {
    TEE(4488),
    COFFEE(4051),
    WATER(4033);


    private final Integer categoryCode;

    CategoryCode(Integer categoryCode) {
        this.categoryCode = categoryCode;
    }

    public Integer getArticleCode() {
        return categoryCode;
    }
}

