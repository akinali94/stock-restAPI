package com.nttdatacasefirst.stockAPI.testcontroller;

import com.nttdatacasefirst.stockAPI.entity.ShareHolder;
import com.nttdatacasefirst.stockAPI.repository.HissedarRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HissedarController {
    private HissedarRepository hissedarRepository;

    public HissedarController(HissedarRepository hissedarRepository){
        this.hissedarRepository = hissedarRepository;
    }

    @GetMapping("/test/hissedar/list")
    public Iterable<ShareHolder> retrieveAllHissedar(){
        return this.hissedarRepository.findAll();
    }

    @PostMapping("/test/hissedar/add")
    public ShareHolder createHissedar(@RequestBody ShareHolder shareHolder){
        return this.hissedarRepository.save(shareHolder);
    }
}
