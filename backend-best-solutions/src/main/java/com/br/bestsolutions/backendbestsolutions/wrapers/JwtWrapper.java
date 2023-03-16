package com.br.bestsolutions.backendbestsolutions.wrapers;

import com.br.bestsolutions.backendbestsolutions.dtos.JwtModelResponseDto;
import com.br.bestsolutions.backendbestsolutions.models.JwtModelResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JwtWrapper {

    public JwtModelResponseDto toDto(JwtModelResponse jwt){
        JwtModelResponseDto jwtDto = new JwtModelResponseDto();
        BeanUtils.copyProperties(jwt, jwtDto);
        return jwtDto;
    }

    public JwtModelResponse toEntity(JwtModelResponseDto jwtDto){
        JwtModelResponse jwt = new JwtModelResponse();
        BeanUtils.copyProperties(jwtDto, jwt);
        return jwt;
    }

    public List<JwtModelResponseDto> toDtoList(List<JwtModelResponse> jwts){
        final List<JwtModelResponseDto> jwtsDto = new ArrayList<>();
        for (JwtModelResponse jwt : jwts) {
            jwtsDto.add(toDto(jwt));
        }
        return jwtsDto;
    }

    public List<JwtModelResponse> toEntityList(List<JwtModelResponseDto> jwtsDto){
        final List<JwtModelResponse> jwts = new ArrayList<>();
        for (JwtModelResponseDto jwtDto : jwtsDto) {
            jwts.add(toEntity(jwtDto));
        }
        return jwts;
    }
}
