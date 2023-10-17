package tcc.job.devs.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tcc.job.devs.entities.UserEntity;
import tcc.job.devs.mappers.SkillMapper;
import tcc.job.devs.payloads.SkillPayloads;
import tcc.job.devs.repositories.SkillRepository;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SkillService {

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private UserService userService;

    public Set<SkillPayloads.SkillModel> findByUserId(int id) {
        return skillRepository.findByUserId(id).stream().map(SkillMapper.INSTANCE::toModel).collect(Collectors.toSet());
    }

    @Transactional
    public Set<SkillPayloads.SkillModel> create(Set<SkillPayloads.CreateSkillPayload> createSkillPayloads, int userId) {
        UserEntity user = userService.findEntityById(userId);
        for (SkillPayloads.CreateSkillPayload skillPayload : createSkillPayloads) {
            skillRepository.save(skillPayload.getName(), skillPayload.getExperienceTime().toString(), user.getId());
        }
        return skillRepository.findByUserId(userId)
                .stream().map(SkillMapper.INSTANCE::toModel).collect(Collectors.toSet());
    }

    @Transactional
    public Set<SkillPayloads.SkillModel> update(Set<SkillPayloads.CreateSkillPayload> createSkillPayloads, int userId) {
        UserEntity user = userService.findEntityById(userId);
        skillRepository.deleteAllByUserId(user.getId());
        for (SkillPayloads.CreateSkillPayload skillPayload : createSkillPayloads) {
            skillRepository.save(skillPayload.getName(), skillPayload.getExperienceTime().toString(), user.getId());
        }
        return skillRepository.findByUserId(userId)
                .stream().map(SkillMapper.INSTANCE::toModel).collect(Collectors.toSet());
    }

}
