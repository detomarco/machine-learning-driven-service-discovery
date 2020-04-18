
package it.univaq.disim.numismatic.authservice.controller.model;

import lombok.Data;

@Data
public class UserType extends BaseUserType {

    private String username;
    private String password;
    private String name;
    private String surname;
    private String email;
    private Boolean enabled;

}
