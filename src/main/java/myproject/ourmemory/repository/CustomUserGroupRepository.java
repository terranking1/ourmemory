package myproject.ourmemory.repository;

import myproject.ourmemory.domain.UserGroup;
import myproject.ourmemory.dto.usergroup.GetUserGroupRequest;

import java.util.List;

public interface CustomUserGroupRepository {

    List<UserGroup> findByUser(GetUserGroupRequest request);

    List<UserGroup> findByGroup(GetUserGroupRequest request);

}