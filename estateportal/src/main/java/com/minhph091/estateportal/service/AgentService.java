package com.minhph091.estateportal.service;

import com.minhph091.estateportal.entity.Agent;
import com.minhph091.estateportal.repository.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AgentService {
    private final AgentRepository agentRepository;

    @Autowired
    public AgentService(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }

    @Transactional(readOnly = true)
    public Optional<Agent> findAgentById(int id) {
        return agentRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Agent> findAllAgent() {
        return agentRepository.findAll();
    }

    @Transactional
    public Agent addAgent(Agent agent) {
        return agentRepository.save(agent);
    }

    @Transactional
    public Agent updateAgent(Agent agent) {
        return agentRepository.save(agent);
    }

    @Transactional
    public void deleteAgentById(int id) {
        agentRepository.deleteById(id);
    }


}
