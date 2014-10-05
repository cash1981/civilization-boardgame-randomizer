package no.asgari.civilization.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import no.asgari.civilization.server.SheetName;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * All the Level 1, 2, 3, 4 and Space Flight techs
 */
@Data
@NoArgsConstructor
public class Tech implements Item, Level {
    @JsonIgnore
    public static final int LEVEL_1 = 1;
    @JsonIgnore
    public static final int LEVEL_2 = 2;
    @JsonIgnore
    public static final int LEVEL_3 = 3;
    @JsonIgnore
    public static final int LEVEL_4 = 4;
    @JsonIgnore
    public static final int LEVEL_5 = 5;

    @NotEmpty
    private String name;
    private String type;
    private String description;
    private boolean used;
    private boolean hidden;
    private String owner; // game_id or player_id (username)
    private int level;

    public Tech(String name, int level) {
        this.name = name;
        this.level = level;
    }

    @Override
    public SheetName getSheetName() {
        switch (getLevel()) {
            case LEVEL_1:
                return SheetName.LEVEL_1_TECH;
            case LEVEL_2:
                return SheetName.LEVEL_1_TECH;
            case LEVEL_3:
                return SheetName.LEVEL_1_TECH;
            case LEVEL_4:
                return SheetName.LEVEL_1_TECH;
        }

        return SheetName.LEVEL_1_TECH;
    }

    @Override
    public String getType() {
        return null;
    }
}