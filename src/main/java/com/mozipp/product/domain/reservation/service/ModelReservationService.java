package com.mozipp.product.domain.reservation.service;

import com.mozipp.product.domain.reservation.converter.ReservationConverter;
import com.mozipp.product.domain.reservation.dto.ModelReservationListDto;
import com.mozipp.product.domain.reservation.entity.Reservation;
import com.mozipp.product.domain.reservation.repository.ReservationRepository;
import com.mozipp.product.test.model.entity.Model;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ModelReservationService {

    private final ReservationRepository reservationRepository;

    public List<ModelReservationListDto> getModelReservationList(Model model) {

        List<Reservation> reservations = reservationRepository.findAllByReservationRequest_Model(model);
        List<ModelReservationListDto> modelReservationListDtos = new ArrayList<>();

        for (Reservation reservation : reservations) {
            ModelReservationListDto dto = ReservationConverter.toModelReservationListDto(reservation);
            modelReservationListDtos.add(dto);
        }

        return modelReservationListDtos;
    }
}
