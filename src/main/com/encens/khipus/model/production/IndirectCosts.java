package com.encens.khipus.model.production;

import com.encens.khipus.model.BaseModel;
import com.encens.khipus.model.CompanyListener;
import com.encens.khipus.model.admin.Company;
import com.encens.khipus.model.common.Text;
import com.encens.khipus.model.warehouse.Group;
import com.encens.khipus.util.Constants;
import org.hibernate.annotations.Filter;
import org.hibernate.validator.Length;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Entity for Employee Time CArd
 *
 * @author Diego Loza
 * @version 1.2.1
 */
@TableGenerator(name = "IndirectCostsConifg.tableGenerator",
        table = "SECUENCIA",
        pkColumnName = "TABLA",
        valueColumnName = "VALOR",
        pkColumnValue = "COSTOSINDIRECTOS",
        allocationSize = 10)

@Entity
@Table(name = "COSTOSINDIRECTOS")
@Filter(name = Constants.COMPANY_FILTER_NAME)
@EntityListeners(CompanyListener.class)
public class IndirectCosts implements BaseModel {

    @Id
    @Column(name = "IDCOSTOSINDIRECTOS", nullable = false)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "IndirectCostsConifg.tableGenerator")
    private Long id;

    @Column(name = "NOMBRE", nullable = true)
    private String name;

    @Column(name = "MONTOBS", nullable = false, columnDefinition = "NUMBER(16,2)")
    private BigDecimal amountBs;

    @ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "IDPERIODOCOSTOINDIRECTO")
    private PeriodIndirectCost periodIndirectCost;

    @ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "IDORDENPRODUCCION")
    private ProductionOrder productionOrder;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "IDCOMPANIA", nullable = false, updatable = false, insertable = true)
    private Company company;

    @OneToOne(optional = true, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "IDCOSTOSINDIRECTOSCONF", nullable = true)
    private IndirectCostsConifg costsConifg;

    @Version
    @Column(name = "VERSION", nullable = false)
    private long version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAmountBs() {
        return amountBs;
    }

    public void setAmountBs(BigDecimal amountBs) {
        this.amountBs = amountBs;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public PeriodIndirectCost getPeriodIndirectCost() {
        return periodIndirectCost;
    }

    public void setPeriodIndirectCost(PeriodIndirectCost periodIndirectCost) {
        this.periodIndirectCost = periodIndirectCost;
    }

    public ProductionOrder getProductionOrder() {
        return productionOrder;
    }

    public void setProductionOrder(ProductionOrder productionOrder) {
        this.productionOrder = productionOrder;
    }

    public IndirectCostsConifg getCostsConifg() {
        return costsConifg;
    }

    public void setCostsConifg(IndirectCostsConifg costsConifg) {
        this.costsConifg = costsConifg;
    }
}
