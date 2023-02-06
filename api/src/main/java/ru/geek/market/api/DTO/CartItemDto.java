package ru.geek.market.api.DTO;


public class CartItemDto {
    private Long productId;
    private String productName;
    private Double productPrice;
    private Integer quantity;
    private Double priceCalculated;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPriceCalculated() {
        return priceCalculated;
    }

    public void setPriceCalculated(Double priceCalculated) {
        this.priceCalculated = priceCalculated;
    }
}
