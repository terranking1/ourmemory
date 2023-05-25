package myproject.ourmemory.dto.usergroup;

import lombok.Getter;
import myproject.ourmemory.domain.UserGroup;
import myproject.ourmemory.domain.UserGroupRole;

@Getter
public class UserGroupDto {

    private Long userId;
    private Long groupId;
    private UserGroupRole role;

    public UserGroupDto(UserGroup userGroup) {
        userId = userGroup.getUser().getId();
        groupId = userGroup.getGroup().getId();
        role = userGroup.getRole();
    }
}
