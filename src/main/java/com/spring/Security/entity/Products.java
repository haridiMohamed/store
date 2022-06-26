package com.spring.Security.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "products")
@Data
public class Products  implements Serializable {

    @Id
    @Column(name="refProduct",length=15)
    private String refProduct;

    @NotNull
    @Column(name="nameProduct",length=25)
    private String nameProduct;

    @Column(name="descProduct")
    private String descProduct;

    @Column(name="totalQte")
    private int totalQte;

    @NotNull
    private Double prix;
    private boolean newProduct;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL )
    private CategoryProduct categore;
//    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL )
//    private Set<ImgFile> imgFiles  = new HashSet<ImgFile>();

    public Products() {
    }

    public Products(String refProduct, @NotNull String nameProduct, String descProduct, int totalQte, @NotNull Double prix, CategoryProduct categore, Set<ImgFile> imgFiles) {
        this.refProduct = refProduct;
        this.nameProduct = nameProduct;
        this.descProduct = descProduct;
        this.totalQte = totalQte;
        this.prix = prix;
        this.categore = categore;
      //  this.imgFiles = imgFiles;
    }
}
