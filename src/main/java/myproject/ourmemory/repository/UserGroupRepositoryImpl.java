package myproject.ourmemory.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import myproject.ourmemory.domain.QGroup;
import myproject.ourmemory.domain.QUser;
import myproject.ourmemory.domain.QUserGroup;
import myproject.ourmemory.domain.UserGroup;
import myproject.ourmemory.dto.usergroup.GetUserGroupRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserGroupRepositoryImpl implements CustomUserGroupRepository {

    private final JPAQueryFactory jpaQueryFactory;
    QUserGroup qUserGroup = QUserGroup.userGroup;
    QUser qUser = QUser.user;
    QGroup qGroup = QGroup.group;

    @Override
    public List<UserGroup> findByUser(GetUserGroupRequest request) {
        return jpaQueryFactory
                .selectFrom(qUserGroup)
                .where(qUserGroup.user.id.eq(request.getUserId()))
                .offset(request.getOffset())
                .limit(request.getSize())
                .fetch();
    }

    @Override
    public List<UserGroup> findByGroup(GetUserGroupRequest request) {
        return jpaQueryFactory
                .selectFrom(qUserGroup)
                .where(qUserGroup.group.id.eq(request.getGroupId()))
                .offset(request.getOffset())
                .limit(request.getSize())
                .fetch();
    }
}