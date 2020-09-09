package com.kingofnone.rentpentti.service.impl;

import com.kingofnone.rentpentti.dao.impl.BandMemberDao;
import com.kingofnone.rentpentti.model.BandMember;
import org.springframework.stereotype.Service;

@Service
public class BandMemberService extends AbstractService<BandMember> {
    public BandMemberService() {
        setDao(new BandMemberDao());
    }
}
