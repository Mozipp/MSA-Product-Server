package com.mozipp.product.domain.product.service;

import com.mozipp.product.domain.product.entity.DesignerProduct;
import com.mozipp.product.domain.request.entity.RequestStatus;
import com.mozipp.product.domain.request.entity.ReservationRequest;
import com.mozipp.product.domain.request.repository.ReservationRequestRepository;
import com.mozipp.product.global.handler.BaseException;
import com.mozipp.product.global.handler.response.BaseResponseStatus;
import com.mozipp.product.global.util.JwtUtil;
import com.mozipp.product.users.Designer;
import com.mozipp.product.users.User;
import com.mozipp.product.users.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFindService {

    private final ReservationRequestRepository reservationRequestRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public Long getModelId(Long designerProductId){
        ReservationRequest reservationRequest = reservationRequestRepository.findByDesignerProduct_IdAndRequestStatus(designerProductId, RequestStatus.ACCEPTED);
        return reservationRequest.getModel().getId();
    }

    public Long getDesignerId(DesignerProduct designerProduct){
        Designer designer = designerProduct.getDesigner();
        return designer.getId();
    }

    public Long getUserId(String accessToken) {
        accessToken = accessToken.substring(7);
        Claims claims = jwtUtil.getClaimsFromToken(accessToken);
        String username = claims.getSubject();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_USER));
        return user.getId();
    }

    public Long getUserIdByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_USER));
        return user.getId();
    }
}
