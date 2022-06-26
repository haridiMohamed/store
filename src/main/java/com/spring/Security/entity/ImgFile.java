package com.spring.Security.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "imgfile")
//@AssociationOverrides({
//
//        @AssociationOverride(name = "kp.product1",
//                joinColumns = @JoinColumn(name = "refProduct")),
//        @AssociationOverride(name = "kp.name",
//                joinColumns = @JoinColumn(name = "name" )) })
@Data
public class ImgFile  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")

    private String name;

    @Column(name = "url")
    private String url;

//    private IdImageProduct kp = new IdImageProduct();


//
//    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL )
//    private Products products;

    public ImgFile() {
    }
//
//    public ImgFile(Products product, String url) {
//        super();
//        this.getKp().setProduct(product);
//        this.url = url;
//    }

//    public IdImageProduct getKp() {
//        return kp;
//    }
//
//    public void setKp(IdImageProduct kp) {
//        this.kp = kp;
//    }
//    @Transient
//    public Products getProducts() {
//        return getKp().getProduct();
//    }
//    public void setProducts(Products product) {
//        getKp().setProduct(product);
//    }



}
