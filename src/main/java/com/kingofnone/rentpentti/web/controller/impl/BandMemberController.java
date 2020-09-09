package com.kingofnone.rentpentti.web.controller.impl;

import com.kingofnone.rentpentti.model.BandMember;
import com.kingofnone.rentpentti.service.impl.BandMemberService;
import org.springframework.web.bind.annotation.*;

@RestController("bandMemberController")
@RequestMapping("/bandmember")
public class BandMemberController extends AbstractController<BandMember> {
    public BandMemberController() {
        setService(new BandMemberService());
    }
}
