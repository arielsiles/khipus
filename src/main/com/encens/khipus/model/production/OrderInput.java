package com.encens.khipus.model.production;


import com.encens.khipus.model.BaseModel;
import com.encens.khipus.model.warehouse.ProductItem;
import org.hibernate.validator.Length;

import javax.persistence.*;
import java.math.BigDecimal;

@TableGenerator(name = "OrderInput_Generator",
        table = "SECUENCIA",
        pkColumnName = "TABLA",
        valueColumnName = "VALOR",
        pkColumnValue = "ORDENINSUMO",
        allocationSize = 10)

@Entity
@Table(name = "ORDENINSUMO")
public class OrderInput implements BaseModel {

    @Id
    @Column(name = "IDORDENINSUMO", columnDefinition = "NUMBER(24,0)", nullable = false)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "OrderInput_Generator")
    private Long id;

    @Column(name = "CANTIDAD", nullable = false, columnDefinition = "NUMBER(24,0)")
    private Double amount;

    @Column(name = "CANTIDADSTOCK", nullable = false, columnDefinition = "NUMBER(24,0)")
    private BigDecimal amountStock;

    @Column(name = "COSTOUNITARIO", nullable = true, columnDefinition = "NUMBER(16,6)")
    private BigDecimal costUnit = new BigDecimal(0.0);

    @Column(name = "COSTOTOTAL", nullable = true, columnDefinition = "NUMBER(16,6)")
    private BigDecimal costTotal = new BigDecimal(0.0);

    @Column(name = "FORMULAMATEMATICA", nullable = false, length = 500)
    private String mathematicalFormula;

    @Column(name = "COD_ART", insertable = false, updatable = false, nullable = false)
    private String productItemCode;

    @Column(name = "NO_CIA", insertable = false, updatable = false, nullable = false)
    @Length(max = 2)
    private String companyNumber;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumns({
            @JoinColumn(name = "NO_CIA", referencedColumnName = "NO_CIA"),
            @JoinColumn(name = "COD_ART", referencedColumnName = "COD_ART")
    })
    private ProductItem productItem;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "IDORDENPRODUCCION", columnDefinition = "NUMBER(24,0)", nullable = false, updatable = false, insertable = true)
    private ProductionOrder productionOrder;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public ProductItem getProductItem() {
        return productItem;
    }

    public void setProductItem(ProductItem productItem) {
        this.productItem = productItem;
    }

    public ProductionOrder getProductionOrder() {
        return productionOrder;
    }

    public void setProductionOrder(ProductionOrder productionOrder) {
        this.productionOrder = productionOrder;
    }

    public BigDecimal getAmountStock() {
        return amountStock;
    }

    public void setAmountStock(BigDecimal amountStock) {
        this.amountStock = amountStock;
    }

    public String getProductItemCode() {
        return productItemCode;
    }

    public void setProductItemCode(String productItemCode) {
        this.productItemCode = productItemCode;
    }

    public String getCompanyNumber() {
        return companyNumber;
    }

    public void setCompanyNumber(String companyNumber) {
        this.companyNumber = companyNumber;
    }

    public String getMathematicalFormula() {
        return mathematicalFormula;
    }

    public void setMathematicalFormula(String mathematicalFormula) {
        this.mathematicalFormula = mathematicalFormula;
    }

    public BigDecimal getCostUnit() {
        return costUnit;
    }

    public void setCostUnit(BigDecimal costUnit) {
        this.costUnit = costUnit;
    }

    public BigDecimal getCostTotal() {
        return costTotal;
    }

    public void setCostTotal(BigDecimal costTotal) {
        this.costTotal = costTotal;
    }
}
