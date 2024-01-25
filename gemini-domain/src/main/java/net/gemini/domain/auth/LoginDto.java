package net.gemini.domain.auth;

import lombok.Data;

/**
 * @author edison
 */
@Data
public class LoginDto {

    private String username;
    private String password;
}
