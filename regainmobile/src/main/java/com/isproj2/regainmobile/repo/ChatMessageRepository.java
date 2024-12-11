package com.isproj2.regainmobile.repo;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.isproj2.regainmobile.model.ChatMessage;
import com.isproj2.regainmobile.model.ChatRoom;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

    List<ChatMessage> findByRoomId(ChatRoom roomId);
    Page<ChatMessage> findByRoomIdOrderByTimestampAsc(ChatRoom roomId, Pageable pageable);

    @Query(value = "SELECT cm FROM ChatMessage cm WHERE cm.roomId = :room ORDER BY cm.timestamp DESC, cm.id DESC")
    ChatMessage findTopByRoomIdOrderByTimestampDesc(@Param("room") ChatRoom room);

    @Query(value = "SELECT * FROM chat_message WHERE room_id = :roomId ORDER BY timestamp DESC, id DESC LIMIT 1", nativeQuery = true)
    ChatMessage findTopMessageByRoomId(@Param("roomId") String roomId);

    @Query("SELECT m FROM ChatMessage m WHERE m.roomId.roomId = :roomId AND m.timestamp > :lastReadAt")
    List<ChatMessage> findUnreadMessages(
            @Param("roomId") String roomId,
            @Param("lastReadAt") Timestamp lastReadAt);

}
