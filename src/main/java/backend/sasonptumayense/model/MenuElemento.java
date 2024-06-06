package backend.sasonptumayense.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "menuElemento")
public class MenuElemento {
    
    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "menu_elemento_sequence")
	@SequenceGenerator(name = "menu_elemento_sequence", sequenceName = "menu_elemento_sequence_name", allocationSize = 1)
	private Integer id;

    //@Id
    @ManyToOne
    @JoinColumn(name = "menusId", updatable = false, nullable = false)
    private Menus menus;

    //@Id
    @ManyToOne
    @JoinColumn(name = "elementosMenuId", updatable = false, nullable = false)
    private ElementosMenu elementosMenu;
}
