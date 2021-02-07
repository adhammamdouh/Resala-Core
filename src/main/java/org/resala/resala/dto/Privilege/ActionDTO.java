package org.resala.resala.dto.Privilege;

public class ActionDTO {
    int id;
    int volunteer_id;
    String name;

    public ActionDTO() {
    }

    public ActionDTO(int volunteer_id, String name) {
        this.volunteer_id = volunteer_id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVolunteer_id() {
        return volunteer_id;
    }

    public void setVolunteer_id(int volunteer_id) {
        this.volunteer_id = volunteer_id;
    }
}
