
package it.univaq.disim.numismatic.userservice.controller.model;

import lombok.Data;

import java.util.List;

@Data
public class NearbyUsersByUserResponse {

    private List<UserType> users;

}
