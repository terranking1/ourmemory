package myproject.ourmemory.repository;

import myproject.ourmemory.domain.UserGroup;
import myproject.ourmemory.dto.usergroup.GetUserGroupRequest;

import java.util.List;

public interface CustomUserGroupRepository {

    UserGroup findOneUserGroup(GetUserGroupRequest request);
    List<UserGroup> findByUser(GetUserGroupRequest request);

    List<UserGroup> findAllByUser(Long userId);

    List<UserGroup> findByGroup(GetUserGroupRequest request);

    List<UserGroup> findAllByGroup(Long groupId);

    Long countGroups(GetUserGroupRequest request);
}
