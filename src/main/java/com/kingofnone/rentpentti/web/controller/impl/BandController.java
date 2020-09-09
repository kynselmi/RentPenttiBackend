package com.kingofnone.rentpentti.web.controller.impl;

import com.kingofnone.rentpentti.model.Band;
import com.kingofnone.rentpentti.model.BandMember;
import com.kingofnone.rentpentti.service.impl.BandService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController("bandController")
@RequestMapping("/band")
public class BandController extends AbstractController<Band> {
    public BandController() {
        setService(new BandService());
    }

    @GetMapping(value = "/{id}/members", produces = ControllerConstants.APPLICATION_JSON)
    public Set<BandMember> getMembers(@PathVariable long id) {
        BandService bandService = (BandService)getService();
        return bandService.getMembersById(id);
    }

    @PostMapping(value = "/{id}/members", produces = ControllerConstants.APPLICATION_JSON)
    public ResponseEntity<BandMember> addMember(@PathVariable long id, @RequestBody BandMember bandMember) {
        BandService bandService = (BandService)getService();
        bandService.addMemberToBand(bandMember, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
