package com.kingofnone.rentpentti.web.controller.impl;

import com.kingofnone.rentpentti.model.BandMember;
import com.kingofnone.rentpentti.service.model.impl.BandMemberModelService;
import org.springframework.web.bind.annotation.*;

@RestController("bandMemberController")
@RequestMapping("/bandmember")
public class BandMemberController extends AbstractController<BandMember> {
    public BandMemberController() {
        setService(new BandMemberModelService());
    }
}
