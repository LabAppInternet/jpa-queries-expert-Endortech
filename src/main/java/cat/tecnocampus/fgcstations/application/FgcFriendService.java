package cat.tecnocampus.fgcstations.application;


import cat.tecnocampus.fgcstations.application.DTOs.FriendUserDTO;
import cat.tecnocampus.fgcstations.application.DTOs.UserFriendsDTO;
import cat.tecnocampus.fgcstations.application.DTOs.UserTopFriend;
import cat.tecnocampus.fgcstations.application.exception.UserDoesNotExistsException;
import cat.tecnocampus.fgcstations.application.mapper.MapperHelper;
import cat.tecnocampus.fgcstations.domain.Friend;
import cat.tecnocampus.fgcstations.domain.User;
import cat.tecnocampus.fgcstations.persistence.FriendRepository;
import cat.tecnocampus.fgcstations.persistence.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FgcFriendService {
    private final FriendRepository friendRepository;
    private final FcgUserService fcgUserService;
    private final UserRepository userRepository;

    public FgcFriendService(FriendRepository friendRepository, FcgUserService fcgUserService, UserRepository userRepository) {
        this.friendRepository = friendRepository;
        this.fcgUserService = fcgUserService;
        this.userRepository = userRepository;
    }

    public UserFriendsDTO getUserFriends(String username) {
        //System.out.println("get user friends");
        if(userRepository.findByUsername(username) == null){
            throw new UserDoesNotExistsException(username);
        }
        // TODO 20: find all the friends of a user given her username. You can solve this exercise without any sql query DONE
        //feed the list with the friends of the user
        return MapperHelper.listOfAUserFriendsToUserFriendsDTO(friendRepository.findAllByUserUsername(username));
    }

    public List<UserFriendsDTO> getAllUserFriends() {
        // TODO 21: find all the friends (domain) of all users. You can solve this exercise without leaving this file
        //  note that domain objects are mapped to DTOs
        return MapperHelper.allUserFriendListToListUserFriendsDTO(friendRepository.findAll()); // replace the empty list with the list of all users
    }

    public void saveFriends(UserFriendsDTO userFriendsDTO) {
        User user = fcgUserService.getDomainUser(userFriendsDTO.getUsername());
        friendRepository.saveAll(MapperHelper.friendsDTOToUserListOfFriends(user, userFriendsDTO));
    }

    public List<UserTopFriend> getTop3UsersWithMostFriends() {
        // TODO 22: find the top 3 users with the most friends. DONE
        return friendRepository.findTopFriends();
    }

    // Find all users whose friends have a certain name
    public List<FriendUserDTO> getUsersByFriend(String friendName) {
        // TODO 23: find all users whose friends have a certain name.
        return friendRepository.findByIdUsername(friendName);
    }

}
