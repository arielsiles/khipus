package com.encens.khipus.service.customers;

import com.encens.khipus.framework.service.GenericService;
import com.encens.khipus.model.customers.AccountItem;
import com.encens.khipus.model.customers.ClientOrder;

import javax.ejb.Local;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Diego
 * Date: 12/11/13
 * Time: 19:11
 * To change this template use File | Settings | File Templates.
 */
@Local
public interface AccountItemService extends GenericService {

    public List<AccountItemServiceBean.OrderClient> findClientsOrder(Date date,BigDecimal distribuidor,String stateOrder);

    public List<AccountItemServiceBean.OrderItem> findOrderItem(Date dateOrder,String stateOrder);

    public Integer getAmount(String codArt,String codPedido);

    public List<BigDecimal> findDistributor(Date date);

    public String getNameEmployeed(BigDecimal codEmployeed);

    public Collection<AccountItemServiceBean.OrderItem> findOrderItemPack(Date dateOrder, String stateOrder);

    public Integer getAmountCombo(String codArt, String idOrder);
}
