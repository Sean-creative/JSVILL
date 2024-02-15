package com.sjs.jsvill.dto;

import com.sjs.jsvill.service.kafka.NotiMessage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class NotiMessageDTO {
    private Long memberRowid;
    private String userPhone;
    private String message;
    private String createdHours;

    public static NotiMessageDTO entityToDTO(NotiMessage message) {
        return NotiMessageDTO.builder()
                .userPhone(message.getUserPhone())
                .message(message.getMessage())
                .build();
    }
}
