package by.emall.api.product.card;


public enum ProductArticle {
    MILK(494687),
    CHEESE(2094742),
    WATER(673194);


    private final Integer articleCode;

    ProductArticle(Integer articleCode) {
        this.articleCode = articleCode;
    }

    public Integer getArticleCode() {
        return articleCode;
    }
}

