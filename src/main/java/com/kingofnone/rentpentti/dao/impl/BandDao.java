package com.kingofnone.rentpentti.dao.impl;

import com.kingofnone.rentpentti.model.Band;
import org.springframework.stereotype.Repository;

@Repository
public class BandDao extends AbstractDao<Band> {
    public BandDao() {
        setClazz(Band.class);
    }
}
