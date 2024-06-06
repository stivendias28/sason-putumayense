package backend.sasonptumayense.repository;

import java.util.List;
import java.util.Optional;

//import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend.sasonptumayense.model.MenuElemento;

@Repository
public interface MenuElementoRepository extends JpaRepository<MenuElemento, Integer> {
    void deleteByMenusId(Integer menuId);
    void deleteByElementosMenuId(Integer elementosMenuId);

    Optional<MenuElemento> findByMenusIdAndElementosMenuId(Integer menusId, Integer elementosMenuId);

    Optional<List<MenuElemento>> findByMenusId(Integer menusId);

    Optional<List<MenuElemento>> findByElementosMenuId(Integer elementosMenuId);
}
