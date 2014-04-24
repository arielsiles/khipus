package com.encens.khipus.service.finances;

import com.encens.khipus.exception.finances.CompanyConfigurationNotFoundException;
import com.encens.khipus.model.admin.BusinessUnit;
import com.encens.khipus.model.finances.FinanceUser;
import com.encens.khipus.model.finances.FinancesModule;
import com.encens.khipus.model.finances.Voucher;
import com.encens.khipus.model.finances.VoucherDetail;

import java.util.Date;
import java.util.List;

/**
 * @author
 * @version 1.0
 */


public interface VoucherService {

    void create(Voucher voucher);

    Voucher createBody(Voucher voucher);

    /**
     * Creates the voucher detail
     *
     * @param voucher           The voucher for change if is necessary
     * @param voucherDetailList The detail list that will be persisted
     */
    void createDetail(Voucher voucher, List<VoucherDetail> voucherDetailList);

    void deleteVoucher(Voucher voucher);

    void approvedAllVoucherEntries(String defaultCompanyNumber, BusinessUnit businessUnit, Date startDate, Date endDate, String numberTransction, FinanceUser financeUser, FinancesModule financesModule) throws CompanyConfigurationNotFoundException;
}
