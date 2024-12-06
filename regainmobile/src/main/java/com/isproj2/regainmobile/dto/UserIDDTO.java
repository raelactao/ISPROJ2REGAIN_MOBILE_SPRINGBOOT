package com.isproj2.regainmobile.dto;

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
        // this.idImage = userID.getIdImage();

    }

}
