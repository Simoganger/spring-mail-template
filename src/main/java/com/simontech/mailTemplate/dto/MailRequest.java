
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
public class MailRequest {
    
    private String name;
    private String to;
    private String from;
    private String subject;
    
}
