package com.sjs.jsvill.dto.letter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LetterDTO {
    private String contents;
    private int checked;
}
