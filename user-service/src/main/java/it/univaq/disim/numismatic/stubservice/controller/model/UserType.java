
package it.univaq.disim.numismatic.stubservice.controller.model;

import lombok.Data;

@Data
public class UserType extends BaseUserType {

    private Boolean enabled;
    private RolesType roles;

}
