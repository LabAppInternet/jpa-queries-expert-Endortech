package cat.tecnocampus.fgcstations.application;

import cat.tecnocampus.fgcstations.application.DTOs.JourneyDTO;
import cat.tecnocampus.fgcstations.application.exception.JourneyDoesNotExistsException;
import cat.tecnocampus.fgcstations.domain.Journey;
import cat.tecnocampus.fgcstations.domain.JourneyId;
import cat.tecnocampus.fgcstations.persistence.JourneyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FgcJourneyService {
    private final JourneyRepository journeyRepository;

    public FgcJourneyService(JourneyRepository journeyRepository) {
        this.journeyRepository = journeyRepository;
    }

    public List<Journey> getAllJourneysDomain() {
        return journeyRepository.findAll();
    }

    public List<JourneyDTO> getAllJourneysDTO() {
        return journeyRepository.findAllDTOBy();
    }

    public Journey getJourneyDomain(String origin, String destination) {
        if (journeyRepository.findJourneyByOriginNameAndDestinationName(origin, destination) == null){
            throw new JourneyDoesNotExistsException(origin, destination);
        }
        return journeyRepository.findJourneyByOriginNameAndDestinationName(origin, destination);
    }

    public JourneyId getJourneyId(String origin, String destination) {
        if (journeyRepository.findJourneyIdByOriginNameAndDestinationName(origin, destination) == null){
            throw new JourneyDoesNotExistsException(origin, destination);
        }
        return journeyRepository.findJourneyIdByOriginNameAndDestinationName(origin, destination);
    }
}
