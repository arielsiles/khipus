package com.encens.khipus.action.production;

import com.encens.khipus.framework.action.QueryDataModel;
import com.encens.khipus.model.production.IndirectCostsConfig;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.security.Restrict;

/**
 * Created by Diego on 08/07/2014.
 */
@Name("indirectCostsConfigDataModel")
@Scope(ScopeType.PAGE)
public class IndirectCostsConfigDataModel extends QueryDataModel<Long,IndirectCostsConfig> {

    private static final String[] RESTRICTIONS = {
             "indirectCostConfig.description = #{indirectCostsConfigDataModel.criteria.description}"
            ,"indirectCostConfig.account = #{indirectCostsConfigDataModel.criteria.account}"
    };

    @Override
    public String getEjbql(){
        return "select indirectCostsConfig from IndirectCostsConfig indirectCostsConfig";
    }

}
