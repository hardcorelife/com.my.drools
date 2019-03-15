package com.my.model;

import java.util.List;

/**
 * 订单
 */
public class Order {
    private Integer id;
    private List<Product> products;
    private double cash = 0;	//返现
    public Integer getId()
    {
        return id;
    }
    public void setId(Integer id)
    {
        this.id = id;
    }
    public List<Product> getProducts()
    {
        return products;
    }
    public void setProducts(List<Product> products)
    {
        this.products = products;
    }
    /**
     * 总金额
     */
    public double getTotal()
    {
        double total = 0;
        for(Product product : products)
        {
            total += product.getPay();
        }
        return total;
    }
    /**
     * 实际支付金额
     */
    public double getPay()
    {
        return getTotal() - cash;
    }
    public double getCash()
    {
        return cash;
    }
    public void setCash(double cash) {
        this.cash = cash;
    }
}
