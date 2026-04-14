package com.minhph091.estateportal.controller;

import com.minhph091.estateportal.entity.Agent;
import com.minhph091.estateportal.exception.ResourceNotFoundException;
import com.minhph091.estateportal.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.json.JsonMapper;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class AgentController {
    private final AgentService agentService;

    @Autowired
    public AgentController(AgentService agentService) {
        this.agentService = agentService;
    }

    @GetMapping("/agents")
    public ResponseEntity<List<Agent>> getAllAgent() {
        List<Agent> agents = agentService.findAllAgent();
        return ResponseEntity.status(HttpStatus.OK).body(agents);
    }

    @GetMapping("/agents/{id}")
    public ResponseEntity<Agent> getAgentById(@PathVariable Integer id) {
        Agent agent = agentService.findAgentById(id).orElseThrow(()
                -> new ResourceNotFoundException("Không tìm thấy id"));
        return ResponseEntity.status(HttpStatus.OK).body(agent);
    }

    @PatchMapping("/agents/{id}")
    public ResponseEntity<Agent> patchAgentById(@PathVariable Integer id,
                                                @RequestBody Map<String, Object> patchBody) {
        JsonMapper jsonMapper = new JsonMapper();
        Agent agent = agentService.findAgentById(id).orElseThrow(()
                -> new ResourceNotFoundException("Không tìm thấy user theo id"));
        if (patchBody.containsKey("id")) {
            throw new RuntimeException("Id không được phép tồn tại");
        }
        jsonMapper.updateValue(patchBody, agent);
        return ResponseEntity.status(HttpStatus.OK).body(agent);
    }

    @DeleteMapping("/agents/{id}")
    public ResponseEntity<Void> deleteAgentById(@PathVariable Integer id) {
        Agent agent = agentService.findAgentById(id).orElseThrow(()
                -> new ResourceNotFoundException("Không tìm thấy user theo id"));
        agentService.deleteAgentById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
