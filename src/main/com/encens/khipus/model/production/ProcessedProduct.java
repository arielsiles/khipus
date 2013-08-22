package com.encens.khipus.model.production;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: david
 * Date: 6/5/13
 * Time: 10:32 AM
 * To change this template use File | Settings | File Templates.
 */

@NamedQueries({
    @NamedQuery(name = "ProcessedProduct.withProductCompositionFind",
                query = "select processedProduct " +
                        "from ProcessedProduct processedProduct " +
                        "left join fetch processedProduct.productCompositionList " +
                        "where processedProduct.id = :id")
})

@Entity
@Table(name = "PRODUCTOPROCESADO")
@DiscriminatorValue("PRODUCTOPROCESADO")
@PrimaryKeyJoinColumns(value = {
        @PrimaryKeyJoinColumn(name = "IDPRODUCTOPROCESADO", referencedColumnName = "IDMETAPRODUCTOPRODUCCION")})
public class ProcessedProduct extends MetaProduct {


    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "IDCOMPANIA",columnDefinition = "NUMBER(24,0)" , nullable = false, updatable = false, insertable = true)
    private com.encens.khipus.model.admin.Company company;

    @OneToMany(mappedBy = "processedProduct", fetch = FetchType.LAZY)
    private List<ProductComposition> productCompositionList = new ArrayList<ProductComposition>();

    public List<ProductComposition> getProductCompositionList() {
        return productCompositionList;
    }

    public void setProductCompositionList(List<ProductComposition> productCompositionList) {
        this.productCompositionList = productCompositionList;
    }

    public com.encens.khipus.model.admin.Company getCompany() {
        return company;
    }

    public void setCompany(com.encens.khipus.model.admin.Company company) {
        this.company = company;
    }

    @Transient
    public String getFullName() {
        if (getCode() == null || getName() == null) {
            return "";
        } else {
            return "[" + getCode() + "] " + getName();
        }
    }

    public void setFullName(String fullName) {

    }
}
