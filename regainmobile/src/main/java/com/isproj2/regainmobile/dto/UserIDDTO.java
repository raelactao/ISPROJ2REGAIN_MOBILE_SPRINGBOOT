package com.isproj2.regainmobile.dto;

import java.util.Base64;

import com.isproj2.regainmobile.model.UserID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class UserIDDTO {
    private int id;

    @lombok.NonNull
    private UserDTO user;

    private String idType;

    @lombok.NonNull
    private String idNumber;

    private String idImage;
 
    public UserIDDTO(UserID userID) {
        this.id = userID.getId();
        this.user = new UserDTO(userID.getUser());
        this.idType = userID.getIdType();
        this.idNumber = userID.getIdNumber();
        // Convert byte[] to Base64 string
        if (userID.getIdImage() != null) {
            this.idImage = Base64.getEncoder().encodeToString(userID.getIdImage());
        }
    }

    public byte[] getIdImageBytes() {
        if (this.idImage != null && !this.idImage.isEmpty()) {
            return Base64.getDecoder().decode(this.idImage);
        }
        return null;
    }
}
