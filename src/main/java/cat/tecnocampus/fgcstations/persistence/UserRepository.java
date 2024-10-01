package cat.tecnocampus.fgcstations.persistence;

import cat.tecnocampus.fgcstations.application.DTOs.PopularDayOfWeek;
import cat.tecnocampus.fgcstations.application.DTOs.UserDTOInterface;
import cat.tecnocampus.fgcstations.application.DTOs.UserDTOnoFJ;
import cat.tecnocampus.fgcstations.application.DTOs.UserTopFavoriteJourney;
import cat.tecnocampus.fgcstations.domain.DayTimeStart;
import cat.tecnocampus.fgcstations.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);

    UserDTOnoFJ findUserDTOnoFJBy(String username);
    UserDTOInterface findUserDTOInterfaceByUsername(String username);
    List<UserDTOInterface> findAllUserByNameAndSecondName(String name, String secondName);

    @Query(value = "SELECT u.username, u.name, u.secondName, u.email, COUNT(fj.id) AS NumberOfFavoriteJourneys " +
            "FROM User u " +
            "LEFT JOIN FavoriteJourney fj ON u.username = fj.user.username " +
            "GROUP BY u.username " +
            "ORDER BY NumberOfFavoriteJourneys DESC " +
            "LIMIT 3")
    List<UserTopFavoriteJourney> findTop3UsersWithMostFavoriteJourneys();

    @Query(value = "SELECT d.dayOfWeek, COUNT(d) as days" +
    "FROM DayTimeStart d" +
    "GROUP BY d.dayOfWeek" +
    "ORDER BY days DESC" +
    "LIMIT 1")
    List<PopularDayOfWeek> popularDayTimeStarts();


}
