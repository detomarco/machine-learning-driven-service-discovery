
package it.univaq.disim.numismatic.stubservice.controller.model;

import lombok.Data;

import java.util.List;

@Data
public class NearbyUsersByUserResponse {

    private List<UserType> users;

}
