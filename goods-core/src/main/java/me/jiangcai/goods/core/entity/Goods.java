/*
 * 版权所有:蒋才
 * (c) Copyright Cai Jiang
 * 2013-2017. All rights reserved.
 */

package me.jiangcai.goods.core.entity;

import lombok.Getter;
import lombok.Setter;
import me.jiangcai.goods.lock.GoodsThreadSafeLocker;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.math.BigDecimal;

/**
 * @author CJ
 */
@Entity
@Setter
@Getter
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Goods implements me.jiangcai.goods.Goods, GoodsThreadSafeLocker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200)
    private String name;
    @Column(precision = 10, scale = 2)
    private BigDecimal price;
    private boolean enable;
    @Column(length = 20)
    private String stockStyle;
//    @ManyToOne(cascade = {CascadeType.REFRESH},optional = false)
//    private GoodsSeller seller;

    @Override
    public Object lockObject() {
        return ("Goods-" + id).intern();
    }
}