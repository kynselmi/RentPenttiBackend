package com.kingofnone.rentpentti.service.model.impl;

import com.kingofnone.rentpentti.model.Band;
import com.kingofnone.rentpentti.model.BandMember;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.LinkedHashSet;
import java.util.Set;

public class BandModelServiceTest {

    public static final long BAND1_ID = 1L;
    public static final String BAND1_NAME = "Band1";
    public static final String BAND_MEMBER1_FIRST_NAME = "John";
    public static final String BAND_MEMBER1_LAST_NAME = "Smith";
    BandModelService bandModelService;

    @Before
    public void setUp() {
        bandModelService = Mockito.spy(new BandModelService());
        Band bandModel = new Band();
        bandModel.setId(BAND1_ID);
        bandModel.setName(BAND1_NAME);
        bandModel.setMembers(createBandMembers(bandModel));
        bandModel.setDeleted(true);
        Mockito.doReturn(bandModel).when((AbstractModelService)bandModelService).getById(BAND1_ID);
    }

    private Set<BandMember> createBandMembers(Band bandModel) {
        Set<BandMember> bandMembers = new LinkedHashSet<>();
        BandMember bandMember1 = new BandMember();
        bandMember1.setFirstName(BAND_MEMBER1_FIRST_NAME);
        bandMember1.setLastName(BAND_MEMBER1_LAST_NAME);
        Set<Band> bandsForMember = new LinkedHashSet<>();
        bandsForMember.add(bandModel);
        bandMember1.setBands(bandsForMember);
        bandMembers.add(bandMember1);
        return bandMembers;
    }

    @Test
    public void testGetMembersById() {
        Set<BandMember> bandMembers = bandModelService.getMembersById(BAND1_ID);
        Assert.assertFalse("Band members set should not be empty",bandMembers.isEmpty());
        Assert.assertEquals("Band members set should have one entry", bandMembers.size(), 1);
        BandMember bandMember = bandMembers.iterator().next();
        Assert.assertEquals("First name should match", BAND_MEMBER1_FIRST_NAME, bandMember.getFirstName());
        Assert.assertEquals("Last name should match", BAND_MEMBER1_LAST_NAME, bandMember.getLastName());
    }
}
