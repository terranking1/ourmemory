package myproject.ourmemory.dto.userGroup;

import lombok.Getter;

@Getter
public class DeleteUserGroupResponse {

    private Long id;

    public DeleteUserGroupResponse(Long id) {
        this.id = id;
    }
}
