
package it.univaq.disim.numismatic.userservice.controller.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class LoginRequest {

    @NotNull
    @NotEmpty
    protected String username;

    @NotNull
    @NotEmpty
    protected String password;

}
