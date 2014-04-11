package de.metalcon.middleware.domain.entity.impl;

import de.metalcon.domain.Muid;
import de.metalcon.middleware.domain.entity.Entity;
import de.metalcon.middleware.domain.entity.EntityType;
import de.metalcon.urlmappingserver.api.requests.registration.UserUrlData;

public class User extends Entity<UserUrlData> {

    @Override
    public EntityType getEntityType() {
        return EntityType.USER;
    }

    public static String concatNames(String firstName, String lastName) {
        return firstName + " " + lastName;
    }

    private String firstName;

    private String lastName;

    public User(
            Muid muid,
            String firstName,
            String lastName) {
        super(muid, concatNames(firstName, lastName));
        setFirstName(firstName);
        setLastName(lastName);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public UserUrlData getUrlData() {
        return new UserUrlData(getMuid(), getFirstName(), getLastName());
    }

}
