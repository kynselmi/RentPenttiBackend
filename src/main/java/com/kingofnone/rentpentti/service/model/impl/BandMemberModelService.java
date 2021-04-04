package com.kingofnone.rentpentti.service.model.impl;

import com.kingofnone.rentpentti.dao.impl.BandMemberDao;
import com.kingofnone.rentpentti.model.BandMember;
import org.springframework.stereotype.Service;

@Service
public class BandMemberModelService extends AbstractModelService<BandMember> {
    public BandMemberModelService() {
        setDao(new BandMemberDao());
    }
}
