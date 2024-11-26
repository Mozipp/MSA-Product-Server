package com.mozipp.product.domain.request.service;

import com.mozipp.product.domain.product.entity.DesignerProduct;
import com.mozipp.product.domain.product.entity.ProductStatus;
import com.mozipp.product.domain.product.repository.DesignerProductRepository;
import com.mozipp.product.domain.request.converter.ReservationRequestConverter;
import com.mozipp.product.domain.request.dto.ModelRequestCreateDto;
import com.mozipp.product.domain.request.dto.ModelRequestListDto;
import com.mozipp.product.domain.request.dto.RequestProductDto;
import com.mozipp.product.domain.request.entity.RequestStatus;
import com.mozipp.product.domain.request.entity.ReservationRequest;
import com.mozipp.product.domain.request.repository.ReservationRequestRepository;
import com.mozipp.product.global.handler.BaseException;
import com.mozipp.product.global.handler.response.BaseResponseStatus;
import com.mozipp.product.users.Model;
import com.mozipp.product.users.repository.ModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ModelRequestService {

    private final ReservationRequestRepository reservationRequestRepository;
    private final DesignerProductRepository designerProductRepository;
    private final ModelRepository modelRepository;

    @Transactional
    public void createModelReservationRequest(Long modelId, ModelRequestCreateDto request) {
        Model model = modelRepository.findById(modelId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_MODEL));
        DesignerProduct designerProduct = designerProductRepository.findById(request.getDesignerProductId())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_DESIGNER_PRODUCT));

        ReservationRequest reservationRequest = ReservationRequestConverter.toReservationRequest(model, designerProduct, request);
        designerProduct.updateProductStatus(ProductStatus.UNAVAILABLE);
        reservationRequestRepository.save(reservationRequest);
    }

    public List<ModelRequestListDto> getModelReservationRequest(Long modelId, RequestStatus status) {
        Model model = modelRepository.findById(modelId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_MODEL));
        List<ReservationRequest> reservationRequests = model.getReservationRequests();

        List<ModelRequestListDto> modelRequestList = new ArrayList<>();
        for (ReservationRequest request : reservationRequests) {
            if(request.getRequestStatus() == status) {
                RequestProductDto requestProductDto = ReservationRequestConverter.toRequestProductDto(request);
                ModelRequestListDto modelRequestListDto = ReservationRequestConverter.toModelRequestListDto(request, requestProductDto);
                modelRequestList.add(modelRequestListDto);
            }
        }

        return modelRequestList;
    }
}
