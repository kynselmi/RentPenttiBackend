package com.kingofnone.rentpentti.web.controller.impl;

import com.kingofnone.rentpentti.model.Band;
import com.kingofnone.rentpentti.model.BandMember;
import com.kingofnone.rentpentti.service.impl.BandMemberService;
import com.kingofnone.rentpentti.service.impl.BandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController("bandController")
@RequestMapping("/band")
public class BandController extends AbstractController<Band> {
    @Autowired
    private BandMemberService bandMemberService;

    public BandController() {
        setService(new BandService());
    }

    @GetMapping(value = "/{id}/members", produces = ControllerConstants.APPLICATION_JSON)
    public Set<BandMember> getMembers(@PathVariable long id) {
        BandService bandService = (BandService)getService();
        return bandService.getMembersById(id);
    }

    @PostMapping(value = "/{id}/members", produces = ControllerConstants.APPLICATION_JSON)
    public ResponseEntity<BandMember> addMembers(@PathVariable long id, @RequestBody List<Long> bandMemberIds) {
        BandService bandService = (BandService)getService();
        bandService.addMembersToBand((bandMemberIds.stream()
                .map(bandMemberId -> bandMemberService.getById(bandMemberId))
                .filter(Optional::isPresent).map(bandMemberOptional -> bandMemberOptional.get())
                .collect(Collectors.toList())), id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
