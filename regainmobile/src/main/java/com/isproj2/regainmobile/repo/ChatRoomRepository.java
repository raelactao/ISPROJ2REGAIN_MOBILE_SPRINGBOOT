package com.isproj2.regainmobile.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.isproj2.regainmobile.model.ChatRoom;
import com.isproj2.regainmobile.model.User;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    Optional<ChatRoom> findByRoomId(String roomId);

    @Query("SELECT c FROM ChatRoom c WHERE (c.user1 = :user1 AND c.user2 = :user2) OR (c.user1 = :user2 AND c.user2 = :user1)")
    Optional<ChatRoom> findByUser1AndUser2OrUser2AndUser1(@Param("user1") User user1, @Param("user2") User user2);


    List<ChatRoom> findByUser1OrUser2(User user1, User user2);

    @Query("SELECT cr FROM ChatRoom cr WHERE cr.user1.userID = :userId OR cr.user2.userID = :userId")
    Page<ChatRoom> findByUser1_UserIDOrUser2_UserID(@Param("userId") Integer userId1, @Param("userId") Integer userId2, Pageable pageable);

}