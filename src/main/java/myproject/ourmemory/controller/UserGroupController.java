package myproject.ourmemory.controller;

import lombok.RequiredArgsConstructor;
import myproject.ourmemory.domain.Group;
import myproject.ourmemory.domain.User;
import myproject.ourmemory.domain.UserGroup;
import myproject.ourmemory.dto.usergroup.*;
import myproject.ourmemory.exception.GroupNotFound;
import myproject.ourmemory.exception.UserGroupNotFound;
import myproject.ourmemory.exception.UserNotFound;
import myproject.ourmemory.repository.GroupRepository;
import myproject.ourmemory.repository.UserGroupRepository;
import myproject.ourmemory.repository.UserRepository;
import myproject.ourmemory.service.UserGroupService;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserGroupController {

    private final UserGroupService userGroupService;
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final UserGroupRepository userGroupRepository;

    /**
     * 특정 회원 그룹 리스트 조회
     */
    @GetMapping("/userGroups/byUser/{userId}")
    public GetByUserResponse findAllByUser(@PathVariable Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFound::new);

        List<UserGroup> userGroups = userGroupService.listByUser(userId);
        List<GroupList> collect = userGroups.stream()
                .map(u -> new GroupList(u))
                .collect(Collectors.toList());

        GetByUserResponse result = GetByUserResponse.builder()
                .user(user)
                .groups(collect)
                .build();

        return result;
    }

    /**
     * 특정 그룹 회원 리스트 조회
     */
    @GetMapping("/userGroups/byGroup/{groupId}")
    public GetByGroupResponse findAllByGroup(@PathVariable Long groupId) {

        Group group = groupRepository.findById(groupId)
                .orElseThrow(GroupNotFound::new);

        List<UserGroup> userGroups = userGroupService.listByGroup(groupId);
        List<UserList> collect = userGroups.stream()
                .map(u -> new UserList(u))
                .collect(Collectors.toList());

        GetByGroupResponse result = GetByGroupResponse.builder()
                .group(group)
                .users(collect)
                .build();

        return result;
    }

    /**
     * 유저그룹 등록(그룹 생성)
     */
    @PostMapping("/userGroups/create")
    public GroupList create(@Valid @RequestBody CreateUserGroupRequest request) {

        Long userGroupId = userGroupService.create(request);
        UserGroup userGroup = userGroupRepository.findById(userGroupId)
                .orElseThrow(UserGroupNotFound::new);

        return new GroupList(userGroup);
    }

    /**
     * 유저그룹 등록(그룹 입장)
     */
    @PostMapping("/userGroups/join")
    public GroupList join(@Valid @RequestBody JoinUserGroupRequest request) {

        Long userGroupId = userGroupService.join(request);
        UserGroup userGroup = userGroupRepository.findById(userGroupId)
                .orElseThrow(UserGroupNotFound::new);

        return new GroupList(userGroup);
    }

    /**
     * 유저그룹 수정(ROLE)
     */
    @PostMapping("/userGroups/{groupId}")
    public void updateRole(@PathVariable Long groupId, @RequestBody UpdateUserGroupRequest request) {
        userGroupService.updateRole(groupId, request);

    }

    /**
     * 유저그룹 삭제
     */
    @DeleteMapping("/userGroups/{userGroupId}")
    public DeleteUserGroupResponse delete(@PathVariable Long userGroupId) {
        userGroupService.delete(userGroupId);

        return new DeleteUserGroupResponse(userGroupId);
    }
}
