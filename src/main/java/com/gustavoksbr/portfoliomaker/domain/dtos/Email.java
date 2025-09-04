package com.gustavoksbr.portfoliomaker.domain.dtos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Email {
    private String to;
    private String subject;
    private String body;
}
