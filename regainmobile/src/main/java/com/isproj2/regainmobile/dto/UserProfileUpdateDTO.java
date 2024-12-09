package com.isproj2.regainmobile.dto;

import com.isproj2.regainmobile.model.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileUpdateDTO {
    private Integer id;
    private String lastName;
    private String firstName;
    @lombok.NonNull
    private String username;
    private byte[] profileImage; // Editable (optional)
    private byte[] gcashQRcode; // Editable (optional)
    private String junkshopName;

    public UserProfileUpdateDTO(User user) {
        this.id = user.getUserID();
        this.lastName = user.getLastName();
        this.firstName = user.getFirstName();
        this.username = user.getUsername();
        this.junkshopName = user.getJunkshopName();
        this.profileImage = user.getProfileImage();
        this.gcashQRcode = user.getGcashQr();
    }
}
