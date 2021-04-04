package com.kingofnone.rentpentti.service.model.impl;

import com.kingofnone.rentpentti.dao.impl.BandDao;
import com.kingofnone.rentpentti.model.Band;
import com.kingofnone.rentpentti.model.BandMember;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class BandModelService extends AbstractModelService<Band> {
    private static final Logger logger = LogManager.getLogger(BandModelService.class);

    public BandModelService() {
        setDao(new BandDao());
    }

    public Set<BandMember> getMembersById(Long bandId) {
        Optional<Band> bandOptional = getById(bandId);
        return bandOptional.isPresent() ? bandOptional.get().getMembers() : Collections.emptySet();
    }

    public void addMemberToBand(BandMember bandMember, long bandId) {
        logger.info("Start adding band member ["+bandMember.toString()+"]to band ["+bandId+"]");
        Optional<Band> bandOptional = getById(bandId);
        if (bandOptional.isEmpty()) {
            return;
        }
        Band band = bandOptional.get();
        Set<BandMember> bandMembers = band.getMembers();
        bandMembers.add(bandMember);
        band.setMembers(bandMembers);
        dao.update(band);
    }

    public void addMembersToBand(List<BandMember> bandMembers, long bandId) {
        for (BandMember bandMember : bandMembers) {
            addMemberToBand(bandMember, bandId);
        }
    }

    public void setMembersToBand(List<BandMember> bandMembers, long bandId) {
        Optional<Band> bandOptional = getById(bandId);
        if (bandOptional.isEmpty()) {
            return;
        }
        Band band = bandOptional.get();
        Set<BandMember> newBandMembers = Collections.emptySet();
        newBandMembers.addAll(bandMembers);
        band.setMembers(newBandMembers);
        dao.update(band);
    }
}
