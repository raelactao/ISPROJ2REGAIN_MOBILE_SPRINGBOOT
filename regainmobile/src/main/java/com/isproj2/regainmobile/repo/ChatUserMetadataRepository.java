// package com.isproj2.regainmobile.repo;

// import com.isproj2.regainmobile.model.ChatUserMetadata;
// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Modifying;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;

// import java.sql.Timestamp;
// import java.util.Optional;

// public interface ChatUserMetadataRepository extends JpaRepository<ChatUserMetadata, Long> {

//     Optional<ChatUserMetadata> findByUserIdAndRoomId(Integer userId, String roomId);

//     @Modifying
//     @Query("UPDATE ChatUserMetadata m SET m.lastReadAt = :lastReadAt WHERE m.userId = :userId AND m.roomId = :roomId")
//     void updateLastReadAt(@Param("userId") Integer userId, @Param("roomId") String roomId, @Param("lastReadAt") Timestamp lastReadAt);
// }

