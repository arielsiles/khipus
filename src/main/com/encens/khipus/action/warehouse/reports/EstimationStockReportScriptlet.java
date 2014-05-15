package com.encens.khipus.action.warehouse.reports;

import com.encens.khipus.model.warehouse.MovementDetailType;
import com.encens.khipus.model.warehouse.ProductItemPK;
import com.encens.khipus.model.warehouse.WarehousePK;
import com.encens.khipus.model.warehouse.WarehouseVoucherState;
import com.encens.khipus.service.warehouse.MovementDetailService;
import com.encens.khipus.service.warehouse.WarehouseService;
import net.sf.jasperreports.engine.JRDefaultScriptlet;
import net.sf.jasperreports.engine.JRScriptletException;
import org.jboss.seam.Component;

import java.math.BigDecimal;

/**
 * Encens S.R.L.
 *
 *
 * @author
 * @version 2.3
 */
public class EstimationStockReportScriptlet extends JRDefaultScriptlet {

    private WarehouseService warehouseService = (WarehouseService) Component.getInstance("warehouseService");

    public void beforeDetailEval() throws JRScriptletException {
        super.beforeDetailEval();

        String codArt = (String) this.getFieldValue("inventory.articleCode");
        BigDecimal unitaryBalance = (BigDecimal) this.getFieldValue("inventory.unitaryBalance");


        BigDecimal totalOrders = warehouseService.findAmountOrderByCodArt(codArt);
        BigDecimal totalExpectedAmount = warehouseService.findExpectedAmountOrderProduction(codArt);
        BigDecimal totalProducedAmount = warehouseService.findProducedAmountOrderProduction(codArt);
        BigDecimal totalBalanceLessTotalOrder = unitaryBalance.subtract(totalOrders);
        BigDecimal totalProducedAmountPlusBalance = unitaryBalance.subtract(totalOrders).add(totalProducedAmount);
        BigDecimal totalExpectedAmountPlusBalance = totalExpectedAmount.add(totalProducedAmountPlusBalance);

        this.setVariableValue("totalOrders", totalOrders);
        this.setVariableValue("totalPlanned",totalExpectedAmount);
        this.setVariableValue("totalProduced",totalProducedAmount);
        this.setVariableValue("totalBalanceLessTotalOrder",totalBalanceLessTotalOrder);
        this.setVariableValue("totalPlannedPlusBalanceLessTotalOrder", totalExpectedAmountPlusBalance);
        this.setVariableValue("totalPoducedPlusBalanceLessTotalOrder", totalProducedAmountPlusBalance);

    }


}
