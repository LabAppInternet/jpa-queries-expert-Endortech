package cat.tecnocampus.fgcstations.persistence;

import cat.tecnocampus.fgcstations.application.DTOs.FriendUserDTO;
import cat.tecnocampus.fgcstations.application.DTOs.UserFriendsDTO;
import cat.tecnocampus.fgcstations.application.DTOs.UserTopFriend;
import cat.tecnocampus.fgcstations.domain.Friend;
import cat.tecnocampus.fgcstations.domain.FriendId;
import cat.tecnocampus.fgcstations.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FriendRepository extends JpaRepository<Friend, FriendId> {
    List<Friend> findAllByUserUsername(String username);

    List<FriendUserDTO> findByIdUsername(String username);

    @Query(value = "SELECT u.username, u.name, u.secondName, u.email, COUNT(f.id) as numberOfFriends " +
    "FROM User u " +
    "LEFT JOIN Friend f ON u.username = f.user.username " +
    "GROUP BY u.username, u.name, u.secondName, u.email " +
    "ORDER BY numberOfFriends DESC " +
    "LIMIT 3")
    List<UserTopFriend> findTopFriends();



}
