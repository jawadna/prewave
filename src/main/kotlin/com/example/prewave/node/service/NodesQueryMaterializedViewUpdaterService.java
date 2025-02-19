package com.example.prewave.node.service;

import com.example.prewave.node.repository.INodeRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class NodesQueryMaterializedViewUpdaterService {

//    private static final int FIXED_RATE = 600000;
    private static final int FIXED_RATE = 30000;
    private static final int INITIAL_DELAY = 360000;
    private final INodeRepository nodeRepository;

    public NodesQueryMaterializedViewUpdaterService(INodeRepository nodeRepository) {
        this.nodeRepository = nodeRepository;
    }

    @Scheduled(fixedDelay = FIXED_RATE, initialDelay = INITIAL_DELAY)
    public void refreshMaterializedView() {
        nodeRepository.refreshMaterializedView();
    }
}