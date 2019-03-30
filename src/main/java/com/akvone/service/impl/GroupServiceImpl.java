package com.akvone.service.impl;

import com.akvone.dao.GroupRepository;
import com.akvone.dto.GroupDto;
import com.akvone.entity.GroupEntity;
import com.akvone.service.GroupService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

  private final GroupRepository groupRepository;

  @Override
  public GroupEntity createOrUpdate(GroupDto groupDto) {
    Long id = groupDto.getId();
    String name = groupDto.getName();

    Optional<GroupEntity> groupOpt = groupRepository.findById(id);

    if (groupOpt.isPresent()) {
      return groupOpt.get();
    } else {
      GroupEntity groupEntity = new GroupEntity();
      groupEntity.setId(id);
      groupEntity.setName(name);

      return groupRepository.save(groupEntity);
    }
  }

}
