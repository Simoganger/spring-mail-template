package com.simontech.mailTemplate.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author simon
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailResponse {
    private String message;
    private boolean status;
}
