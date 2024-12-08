package com.isproj2.regainmobile.model;

import com.isproj2.regainmobile.config.EncryptionUtil;
import com.isproj2.regainmobile.dto.UserIDDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "userID")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserID {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "user", referencedColumnName = "user_id")
    private User user;

    @Convert(converter = EncryptionUtil.class)
    @Column(name = "id_type", length = 50)
    private String idType;

    @Convert(converter = EncryptionUtil.class)
    @Column(name = "id_number", length = 100)
    private String idNumber;

    @Lob
    @Column(name = "id_image", columnDefinition = "LONGBLOB")
    private byte[] idImage;

    public UserID(User user, UserIDDTO dto) {
        this.id = dto.getId();
        this.user = user;
        this.idType = dto.getIdType();
        this.idNumber = dto.getIdNumber();
        if (dto.getIdImage() != null && !dto.getIdImage().isEmpty()) {
            this.idImage = dto.getIdImageBytes(); // Decode Base64 to byte[]
        }
    }

}
