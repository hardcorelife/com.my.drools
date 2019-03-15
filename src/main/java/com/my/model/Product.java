package com.my.model;

/**
 * 商品
 */
public class Product {
    private Integer id; //商品id
    private String name; //商品名称
    private Integer count = 1; //数量
    private double price; //价格
    private double discount = 1;	//折扣
    private double deduction = 0;  //现金抵扣
    private Integer productLine ; //产品线
    private Integer category ; //分类

    public Product(String name, double price)
    {
        this.name = name;
        this.price = price;
    }
    public Product(String name, double price, Integer count)
    {
        this.name = name;
        this.price = price;
        this.count = count;
    }
    public Product(Integer id, String name, double price, Integer count)
    {
        this.id = id;
        this.name = name;
        this.price = price;
        this.count = count;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getDeduction() {
        return deduction;
    }

    public void setDeduction(double deduction) {
        this.deduction = deduction;
    }

    public Integer getProductLine() {
        return productLine;
    }

    public void setProductLine(Integer productLine) {
        this.productLine = productLine;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public Integer getCount()
    {
        return count;
    }
    public void setCount(Integer count)
    {
        this.count = count;
    }
    public double getPrice()
    {
        return price;
    }
    public void setPrice(double price)
    {
        this.price = price;
    }
    public double getDiscount()
    {
        return discount;
    }
    public void setDiscount(double discount)
    {
        this.discount = discount;
    }
    /**
     * 金额
     */
    public double getPay()
    {
        return price * count * discount - deduction;
    }

    public String toString() {
        return "[name=" + name + ",price=" + price + ",count=" + count + ",discount=" + discount + ",deduction=" + deduction + ",getPay()=" + getPay() + "]";
    }

}
