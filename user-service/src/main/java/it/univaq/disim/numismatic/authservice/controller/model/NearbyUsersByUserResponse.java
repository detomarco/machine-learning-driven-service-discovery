
package it.univaq.disim.numismatic.authservice.controller.model;

import lombok.Data;

import java.util.List;

@Data
public class NearbyUsersByUserResponse {

    private List<UserType> users;

}