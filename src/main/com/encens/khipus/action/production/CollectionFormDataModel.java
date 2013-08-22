package com.encens.khipus.action.production;


import com.encens.khipus.framework.action.QueryDataModel;
import com.encens.khipus.model.production.CollectionForm;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Name("collectionFormDataModel")
@Scope(ScopeType.PAGE)
public class CollectionFormDataModel extends QueryDataModel<Long, CollectionForm> {

    private PrivateCriteria privateCriteria;

    private static final String[] RESTRICTIONS = {
            "collectionForm.date >= #{collectionFormDataModel.privateCriteria.startDate}",
            "collectionForm.date <= #{collectionFormDataModel.privateCriteria.endDate}"
    };

    @Create
    public void init() {
        sortProperty = "collectionForm.date";
    }

    @Override
    public String getEjbql() {
        return "select collectionForm " +
                "from CollectionForm collectionForm ";
    }

    @Override
    public List<String> getRestrictions() {
        return Arrays.asList(RESTRICTIONS);
    }

    public PrivateCriteria getPrivateCriteria() {
        if (privateCriteria == null) {
            privateCriteria = new PrivateCriteria();
        }
        return privateCriteria;
    }

    public static class PrivateCriteria {
        private Date startDate;
        private Date endDate;

        public Date getStartDate() { return startDate; }
        public void setStartDate(Date startDate) { this.startDate = startDate; }

        public Date getEndDate() { return endDate; }
        public void setEndDate(Date endDate) { this.endDate = endDate; }
    }
}
