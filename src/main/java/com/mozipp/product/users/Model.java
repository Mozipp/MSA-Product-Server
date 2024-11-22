package com.mozipp.product.users;

import com.mozipp.product.domain.request.entity.ReservationRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@PrimaryKeyJoinColumn(name = "model_id")
public class Model extends User {

    private String petName;

    private String petAge;

    @Enumerated(EnumType.STRING)
    private PetGender petGender;

    private String breed;

    private String petImageUrl;

    @OneToMany(mappedBy = "model")
    private List<ReservationRequest> reservationRequests = new ArrayList<>();

    public void updatePetProfile(String petName, String petAge, PetGender petGender, String breed) {
        this.petName = petName;
        this.petAge = petAge;
        this.petGender = petGender;
        this.breed = breed;
    }

    public void addReservationRequest(ReservationRequest reservationRequest) {
        reservationRequests.add(reservationRequest);
    }
}
