package com.example.demo.controller;

import com.example.demo.dto.QueuePositionResponse;
import com.example.demo.entity.QueuePosition;
import com.example.demo.service.QueueService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/queue")
public class QueueController {

    private final QueueService queueService;

    public QueueController(QueueService queueService) {
        this.queueService = queueService;
    }

    @PutMapping("/position/{tokenId}/{position}")
    public QueuePositionResponse update(@PathVariable Long tokenId,
                                        @PathVariable Integer position) {
        QueuePosition qp = queueService.updateQueuePosition(tokenId, position);

        QueuePositionResponse res = new QueuePositionResponse();
        res.setTokenId(qp.getToken().getId());
        res.setTokenNumber(qp.getToken().getTokenNumber());
        res.setPosition(qp.getPosition());
        return res;
    }

    @GetMapping("/position/{tokenId}")
    public QueuePositionResponse get(@PathVariable Long tokenId) {
        QueuePosition qp = queueService.getPosition(tokenId);

        QueuePositionResponse res = new QueuePositionResponse();
        res.setTokenId(qp.getToken().getId());
        res.setTokenNumber(qp.getToken().getTokenNumber());
        res.setPosition(qp.getPosition());
        return res;
    }
}
