package com.kingofnone.rentpentti.dao.impl;

import com.kingofnone.rentpentti.model.BandMember;
import org.springframework.stereotype.Repository;

@Repository
public class BandMemberDao extends AbstractDao {
    public BandMemberDao() { setClazz(BandMember.class); }
}
