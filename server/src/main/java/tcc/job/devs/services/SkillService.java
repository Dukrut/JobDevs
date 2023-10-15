package tcc.job.devs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tcc.job.devs.entities.UserEntity;
import tcc.job.devs.mappers.SkillMapper;
import tcc.job.devs.payloads.SkillPayloads;
import tcc.job.devs.repositories.SkillRepository;

import java.util.HashSet;
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

    public Set<SkillPayloads.SkillModel> create(Set<SkillPayloads.CreateSkillPayload> createSkillPayloads, int userId) {
        UserEntity user = userService.findEntityById(userId);
        Set<SkillPayloads.SkillModel> skillModels = new HashSet<>();
        for (SkillPayloads.CreateSkillPayload skillPayload : createSkillPayloads) {
            SkillPayloads.SkillModel skillModel = SkillMapper.INSTANCE.toModel(
                    skillRepository.save(skillPayload.getName(), skillPayload.getExperienceTime(), user.getId())
            );
            skillModels.add(skillModel);
        }
        return skillModels;
    }

    public Set<SkillPayloads.SkillModel> update(Set<SkillPayloads.CreateSkillPayload> createSkillPayloads, int userId) {
        UserEntity user = userService.findEntityById(userId);
        skillRepository.deleteAllByUserId(user.getId());
        Set<SkillPayloads.SkillModel> skillModels = new HashSet<>();
        for (SkillPayloads.CreateSkillPayload skillPayload : createSkillPayloads) {
            SkillPayloads.SkillModel skillModel = SkillMapper.INSTANCE.toModel(
                    skillRepository.save(skillPayload.getName(), skillPayload.getExperienceTime(), user.getId())
            );
            skillModels.add(skillModel);
        }
        return skillModels;
    }

}
