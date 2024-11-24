package com.mozipp.product.domain.reservation.service;

import com.mozipp.product.domain.reservation.converter.ReservationConverter;
import com.mozipp.product.domain.reservation.dto.ModelReservationListDto;
import com.mozipp.product.domain.reservation.entity.Reservation;
import com.mozipp.product.domain.reservation.entity.ReservationStatus;
import com.mozipp.product.domain.reservation.repository.ReservationRepository;
import com.mozipp.product.global.handler.BaseException;
import com.mozipp.product.global.handler.response.BaseResponseStatus;
import com.mozipp.product.users.Model;
import com.mozipp.product.users.repository.ModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ModelReservationService {

    private final ReservationRepository reservationRepository;
    private final ModelRepository modelRepository;

    public List<ModelReservationListDto> getModelReservationList(Long modelId, ReservationStatus status) {

        Model model = modelRepository.findById(modelId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_MODEL));

        List<Reservation> reservations = reservationRepository.findAllByReservationRequest_Model(model);
        List<ModelReservationListDto> modelReservationListDtos = new ArrayList<>();

        for (Reservation reservation : reservations) {
            if(reservation.getReservationStatus() == status) {
                ModelReservationListDto dto = ReservationConverter.toModelReservationListDto(reservation);
                modelReservationListDtos.add(dto);
            }
        }

        return modelReservationListDtos;
    }
}
