package br.com.vlemes.osworks.api.model;

import javax.validation.constraints.NotNull;

public class CostumerIdInput {
    @NotNull
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
