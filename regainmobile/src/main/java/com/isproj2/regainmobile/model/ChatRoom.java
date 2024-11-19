package com.isproj2.regainmobile.model;

import java.util.Collection;
import java.util.Objects;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "chat_room")
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user1_user_id", referencedColumnName = "user_id", nullable = false)
    private User user1;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user2_user_id", referencedColumnName = "user_id", nullable = false)
    private User user2;

    @Column(name = "room_id")
    private String roomId; 

    @OneToMany(mappedBy = "roomId", fetch = FetchType.LAZY)
    @JsonBackReference
    private Collection<ChatMessage> chatMessage;

    public ChatRoom(User user1, User user2) {
        this.user1 = user1;
        this.user2 = user2;
        this.roomId = generateRoomId(user1, user2);
    }

    private String generateRoomId(User user1, User user2) {
        return UUID.randomUUID().toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatRoom chatRoom = (ChatRoom) o;
        return Objects.equals(user1, chatRoom.user1) && Objects.equals(user2, chatRoom.user2) ||
               Objects.equals(user1, chatRoom.user2) && Objects.equals(user2, chatRoom.user1);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Math.min(user1.getUserID(), user2.getUserID()), 
                        Math.max(user1.getUserID(), user2.getUserID()));
    }
    
}
