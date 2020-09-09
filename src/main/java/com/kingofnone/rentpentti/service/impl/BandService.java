package com.kingofnone.rentpentti.service.impl;

import com.kingofnone.rentpentti.dao.impl.BandMemberDao;
import com.kingofnone.rentpentti.model.Band;
import com.kingofnone.rentpentti.model.BandMember;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

@Service
public class BandService extends AbstractService<Band> {
    public BandService() {
        setDao(new BandMemberDao());
    }

    public Set<BandMember> getMembersById(Long bandId) {
        Optional<Band> bandOptional = getById(bandId);
        return bandOptional.isPresent() ? bandOptional.get().getMembers() : Collections.emptySet();
    }

    public void addMemberToBand(BandMember bandMember, long bandId) {
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
}
