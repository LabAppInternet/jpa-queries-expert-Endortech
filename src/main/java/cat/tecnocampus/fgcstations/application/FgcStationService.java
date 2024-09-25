package cat.tecnocampus.fgcstations.application;

import cat.tecnocampus.fgcstations.application.DTOs.StationDTO;
import cat.tecnocampus.fgcstations.application.DTOs.StationTopFavoriteJourney;
import cat.tecnocampus.fgcstations.application.exception.StationDoesNotExistsException;
import cat.tecnocampus.fgcstations.application.mapper.MapperHelper;
import cat.tecnocampus.fgcstations.domain.Station;
import cat.tecnocampus.fgcstations.persistence.StationRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FgcStationService {
    private final StationRepository stationRepository;

    public FgcStationService(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    public List<StationDTO> getStationsDTO() {
         return stationRepository.findAllProjectedBy();
    }

    public List<Station> getStationsDomain() {
            return stationRepository.findAll();

    }

    public Station getStation(String name) {
        if (stationRepository.findByName(name) == null) {
            throw new StationDoesNotExistsException(name);
        }
        return stationRepository.findByName(name);
    }

    public StationDTO getStationDTO(String name) {
        if (stationRepository.findProjectedByName(name) == null) {
            throw new StationDoesNotExistsException(name);
        }
        return stationRepository.findProjectedByName(name);
    }

    public List<StationTopFavoriteJourney> getStationsOrderedByFavoriteJourneysAsEitherOriginOrDestination() {
        // TODO 5: this is an optional exercise because is quite tricky. You need to use a native query because is no possible to
        //  have a derived table (subquery).
        //  You first need to use a 'UNION' to get the stations either as origin or destination of a Journey. Then you need to group and count
        return null;
    }
}
