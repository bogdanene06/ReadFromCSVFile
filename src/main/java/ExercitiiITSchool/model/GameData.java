/*
Author: Ene Bogdan
Country: Romania
*/
package ExercitiiITSchool.model;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GameData {
    private String name;
    private double price;
    private double diskSpace;
    private String type;
    private String platform;

}
