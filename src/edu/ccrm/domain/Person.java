package edu.ccrm.domain;

import java.time.LocalDate;

// Abstract class
public abstract class Person {
    protected String personId;
    protected String personFullName;
    protected String personEmail;
    protected boolean isActive;
    protected LocalDate accountCreatedAt;

    public Person(String personId, String personFullName, String personEmail) {
        this.personId = personId;
        this.personFullName = personFullName;
        this.personEmail = personEmail;
        this.accountCreatedAt = LocalDate.now();
        this.isActive = true;
    }

    public abstract String getProfile();

    // Getters/Setters
    public String getId() { return personId; }
    public String getFullName() { return personFullName; }
    public String getEmail() { return personEmail; }
    public boolean isActive() { return isActive; }
    public LocalDate getCreatedAt() { return accountCreatedAt; }

    public void setFullName(String personFullName) { this.personFullName = personFullName; }
    public void setEmail(String personEmail) { this.personEmail = personEmail; }
    public void setActive(boolean isActive) { this.isActive = isActive; }

    // toString override (demonstrates polymorphism)
    @Override
    public String toString() {
        return String.format("ID: %s, Name: %s, Email: %s, Active: %b, Since: %s",
                personId, personFullName, personEmail, isActive, accountCreatedAt);
    }
}
